package com.example.fitnessactivity.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessactivity.adapters.ExerciseRVAdapter
import com.example.fitnessactivity.databinding.ActivityBodyPartBinding
import com.example.fitnessactivity.models.BodyPart
import com.example.fitnessactivity.models.CustomChallenge
import com.example.fitnessactivity.models.Exercise
import com.example.fitnessactivity.setWhiteStatusBarColor
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BodyPartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBodyPartBinding
    private var bodyPart: BodyPart? = null

    private var database: FirebaseDatabase? = null
    private var challenge: CustomChallenge? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBodyPartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setWhiteStatusBarColor()
        database = FirebaseDatabase.getInstance()
        bodyPart = (intent.getSerializableExtra("THE_PART") as? BodyPart)
        challenge = intent.getSerializableExtra("MyChallenge") as? CustomChallenge
        if (challenge != null) {
            binding.title.text = challenge!!.title
            val type = object : TypeToken<ArrayList<Exercise>>() {}.type
            val list: ArrayList<Exercise> = Gson().fromJson(challenge!!.exercisesListString, type)
            binding.recyclerView.apply {
                adapter = ExerciseRVAdapter(this@BodyPartActivity, list)
            }
        } else {
            binding.title.text = "${bodyPart?.name} Exercises"
            populateData()
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