package com.example.fitnessactivity.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessactivity.*
import com.example.fitnessactivity.adapters.OrderExpandableListAdapter
import com.example.fitnessactivity.databinding.ActivityAddChallengeBinding
import com.example.fitnessactivity.misc.GlobalSingleton
import com.example.fitnessactivity.models.CustomChallenge
import com.example.fitnessactivity.models.Exercise
import com.example.fitnessactivity.roomDb.LocalDataSource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class AddChallengeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddChallengeBinding

    @Inject
    lateinit var localDataSource: LocalDataSource

    private lateinit var headerList: ArrayList<String>
    var selectedExercises: ArrayList<Exercise>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddChallengeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        selectedExercises = ArrayList()
        headerList = arrayListOf("Leg", "Chest", "Shoulder", "Abs", "Back", "Arms")
        fetchData()
        updateCount()
        setupList()
        addButtonClickListener()
    }

    private fun addButtonClickListener() {
        binding.saveButton.setOnClickListener {
            if (validate()) {
                addChallengeToDatabase()
            }
        }
    }

    private fun validate(): Boolean {
        var result = true
        val title = binding.title.text.toString()
        if (title.isEmpty()) {
            binding.title.error = "Please enter valid title!"
            return false
        }
        if (selectedExercises == null || selectedExercises!!.size < 3) {
            result = false
            showToastShort("Please select at least 3 exercises!")
        }
        return result
    }

    private fun addChallengeToDatabase() {
        val title = binding.title.text.toString()
        val listString = Gson().toJson(selectedExercises)
        val challenge =
            CustomChallenge(
                id = UUID.randomUUID().toString(),
                title = title,
                exercisesListString = listString,
                false
            )
        localDataSource.addChallenge(challenge)
        finish()
    }

    @SuppressLint("SetTextI18n")
    fun updateCount() {
        selectedExercises?.let {
            if (it.isEmpty()) {
                binding.totalCount.text = getString(R.string.empty)
            } else {
                binding.totalCount.text = "Total (${it.size})"
            }
        }
    }

    private fun fetchData() {
        getDataByBodyPart(0)
    }

    private fun getDataByBodyPart(index: Int) {
        val ref = FirebaseDatabase.getInstance().getReference("Exercises").child(headerList[index])
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<Exercise>()
                for (postSnapshot in snapshot.children) {
                    postSnapshot.getValue<Exercise>()?.let { list.add(it) }
                }
                GlobalSingleton.expandableListChildData[headerList[index]] = list
                "Calllled".printLog(GlobalSingleton.expandableListChildData)
                setupList()
                if (index < headerList.size - 1) {
                    getDataByBodyPart(index + 1)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("loadPost:onCancelled", error.toException().toString())
                binding.progressBar2.makeGone()
            }

        })
    }

    private fun setupList() {
        if (GlobalSingleton.expandableListChildData.size != 6) {
            binding.progressBar2.makeVisible()
            binding.expandableListView.makeGone()
        } else {
            binding.progressBar2.makeGone()
            binding.expandableListView.makeVisible()
            val adapter = OrderExpandableListAdapter(
                this,
                ArrayList(GlobalSingleton.expandableListChildData.map { it.key }),
                GlobalSingleton.expandableListChildData
            )
            binding.expandableListView.setAdapter(adapter)
        }
    }

}