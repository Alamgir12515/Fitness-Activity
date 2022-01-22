package com.example.fitnessactivity.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessactivity.adapters.ExerciseRVAdapter
import com.example.fitnessactivity.data.LocalData
import com.example.fitnessactivity.databinding.ActivityBodyPartBinding
import com.example.fitnessactivity.models.BodyPart
import com.example.fitnessactivity.setWhiteStatusBarColor

class BodyPartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBodyPartBinding
    private lateinit var bodyPart: BodyPart

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBodyPartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setWhiteStatusBarColor()
        bodyPart = (intent.getSerializableExtra("THE_PART") as? BodyPart) ?: BodyPart.Leg
        binding.title.text = "${bodyPart.name} Exercises"
        populateData()
    }

    private fun populateData() {
        val list = when (bodyPart) {
            BodyPart.Leg -> {
                LocalData.getLegsExercises()
            }
            BodyPart.Chest -> {
                LocalData.getChestExercises()
            }
            BodyPart.Shoulder -> {
                LocalData.getShoulderExercises()
            }
            BodyPart.Back -> {
                LocalData.getBackExercises()
            }
            BodyPart.Arms -> {
                LocalData.getArmsExercises()
            }
            else -> {
                //Abs
                LocalData.getAbsExercise()
            }
        }
        binding.recyclerView.apply {
            adapter = ExerciseRVAdapter(this@BodyPartActivity, bodyPart, list)
        }
    }
}