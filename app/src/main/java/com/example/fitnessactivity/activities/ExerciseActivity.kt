package com.example.fitnessactivity.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.fitnessactivity.data.LocalData
import com.example.fitnessactivity.databinding.ActivityExerciseBinding
import com.example.fitnessactivity.makeStatusBarTransparent
import com.example.fitnessactivity.models.BodyPart
import com.example.fitnessactivity.models.Exercise
import com.example.fitnessactivity.setWhiteStatusBarColor

class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding
    private lateinit var exercise: Exercise
    private lateinit var bodyPart: BodyPart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setWhiteStatusBarColor()
        makeStatusBarTransparent()
        bodyPart = (intent.getSerializableExtra("THE_PART") as? BodyPart) ?: BodyPart.Leg
        val name = intent.getStringExtra("EXERCISE_TITLE") ?: ""
        exercise = LocalData.getExerciseByName(name, bodyPart)
        Glide.with(this).load(exercise.image).into(binding.image)
        binding.title.text = exercise.name
        binding.description.text = exercise.description
    }
}