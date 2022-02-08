package com.example.fitnessactivity.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.fitnessactivity.*
import com.example.fitnessactivity.adapters.ExerciseRVAdapter
import com.example.fitnessactivity.databinding.ActivityBodyPartBinding
import com.example.fitnessactivity.models.BodyPart
import com.example.fitnessactivity.models.CustomChallenge
import com.example.fitnessactivity.models.Exercise
import com.example.fitnessactivity.roomDb.LocalDataSource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BodyPartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBodyPartBinding
    private var bodyPart: BodyPart? = null

    @Inject
    lateinit var localDataSource: LocalDataSource

    private var database: FirebaseDatabase? = null
    private var challenge: CustomChallenge? = null
    private var completedLiveData = MutableLiveData<Boolean>()
    private var isComplete = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBodyPartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setWhiteStatusBarColor()
        database = FirebaseDatabase.getInstance()
        bodyPart = (intent.getSerializableExtra("THE_PART") as? BodyPart)
        challenge = intent.getSerializableExtra("MyChallenge") as? CustomChallenge
        setupChallengesLayout((challenge != null))
        if (challenge != null) {
            addStatusLiveDataObserver()
            binding.title.text = challenge!!.title
            val type = object : TypeToken<ArrayList<Exercise>>() {}.type
            val list: ArrayList<Exercise> = Gson().fromJson(challenge!!.exercisesListString, type)
            completedLiveData.value = challenge!!.isComplete
            binding.recyclerView.apply {
                adapter = ExerciseRVAdapter(this@BodyPartActivity, list)
            }
        } else {
            binding.title.text = "${bodyPart?.name} Exercises"
            populateData()
        }
    }

    private fun setupChallengesLayout(show: Boolean) {
        if (show) {
            binding.bottomLayout.makeVisible()
            binding.statusCard.makeVisible()
            binding.removeButton.setOnClickListener {
                localDataSource.removeChallenge(challenge!!)
                showToastShort("Deleted \"${challenge?.title}\"")
                finish()
            }
            binding.statusCard.setOnClickListener {
                showToastShort(if (isComplete) "Complete" else "In Progress")
            }
            binding.completedButton.setOnClickListener {
                localDataSource.update(!isComplete, challenge!!.id)
                completedLiveData.value = !isComplete
            }
        } else {
            binding.bottomLayout.makeGone()
            binding.statusCard.makeGone()
        }
    }

    private fun addStatusLiveDataObserver() {
        completedLiveData.observe(this) {
            isComplete = it
            binding.statusImage.setImageResource(if (isComplete) R.drawable.done2 else R.drawable.in_progress)
            val buttonText =
                if (isComplete) getString(R.string.keepInProgress) else getString(R.string.markAsComplete)
            binding.completedButton.text = buttonText
        }
    }

    private fun populateData() {
        when (bodyPart) {
            BodyPart.Leg -> {
                getDataList("Leg")
            }
            BodyPart.Chest -> {
                getDataList("Chest")
            }
            BodyPart.Shoulder -> {
                getDataList("Shoulder")
            }
            BodyPart.Back -> {
                getDataList("Back")
            }
            BodyPart.Arms -> {
                getDataList("Arms")
            }
            else -> {
                //Abs
                getDataList("Abs")
            }
        }
//        binding.recyclerView.apply {
//            adapter = ExerciseRVAdapter(this@BodyPartActivity, bodyPart, list)
//        }
    }

    private fun getDataList(refName: String): ArrayList<Exercise> {
        val list = arrayListOf<Exercise>()
        val ref = database?.getReference("Exercises")?.child(refName)
        ref?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    postSnapshot.getValue<Exercise>()?.let {
                        list.add(it)
                    }
                }
                binding.recyclerView.apply {
                    adapter = ExerciseRVAdapter(this@BodyPartActivity, list)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("loadPost:onCancelled", databaseError.toException().toString())
            }
        })
        return list
    }
}