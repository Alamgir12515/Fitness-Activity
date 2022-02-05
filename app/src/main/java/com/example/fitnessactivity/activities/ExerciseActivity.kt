package com.example.fitnessactivity.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.fitnessactivity.databinding.ActivityExerciseBinding
import com.example.fitnessactivity.makeStatusBarTransparent
import com.example.fitnessactivity.models.BodyPart
import com.example.fitnessactivity.models.Exercise
import com.example.fitnessactivity.setWhiteStatusBarColor
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue

class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding
    private var exercise: Exercise? = null
    private lateinit var bodyPart: BodyPart
    private var databaseRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setWhiteStatusBarColor()
        databaseRef = FirebaseDatabase.getInstance().getReference("Exercises")
        makeStatusBarTransparent()
        bodyPart = (intent.getSerializableExtra("THE_PART") as? BodyPart) ?: BodyPart.Leg
        val name = intent.getStringExtra("EXERCISE_TITLE") ?: ""
        getExercise(name)
    }

    private fun getExercise(name: String) {
        databaseRef?.child(bodyPart.name)?.child(name)?.get()?.addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
            exercise = it.getValue<Exercise>()
            Glide.with(this).load(exercise?.image).into(binding.image)
            binding.title.text = exercise?.name
            binding.description.text = exercise?.description
        }?.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
    }
}