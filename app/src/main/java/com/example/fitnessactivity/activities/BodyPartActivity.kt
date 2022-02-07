package com.example.fitnessactivity.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessactivity.adapters.ExerciseRVAdapter
import com.example.fitnessactivity.databinding.ActivityBodyPartBinding
import com.example.fitnessactivity.models.BmiCategory
import com.example.fitnessactivity.models.BodyPart
import com.example.fitnessactivity.models.Exercise
import com.example.fitnessactivity.setWhiteStatusBarColor
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class BodyPartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBodyPartBinding
    private var bodyPart: BodyPart? = null

    private var database: FirebaseDatabase? = null
    private var category: BmiCategory? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBodyPartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setWhiteStatusBarColor()
        database = FirebaseDatabase.getInstance()
        bodyPart = (intent.getSerializableExtra("THE_PART") as? BodyPart)
        category = intent.getSerializableExtra("MyCategory") as? BmiCategory
        if (category != null) {
            binding.title.text = "Recommended Exercises"
            getRecommendedExercises(category!!)
        } else {
            binding.title.text = "${bodyPart?.name} Exercises"
            populateData()
        }
    }

    private fun getRecommendedExercises(category: BmiCategory) {

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