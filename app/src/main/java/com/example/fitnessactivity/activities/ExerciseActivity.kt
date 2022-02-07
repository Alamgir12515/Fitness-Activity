package com.example.fitnessactivity.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.fitnessactivity.databinding.ActivityExerciseBinding
import com.example.fitnessactivity.makeStatusBarTransparent
import com.example.fitnessactivity.models.Exercise
import com.example.fitnessactivity.setWhiteStatusBarColor

class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding
    private var exercise: Exercise? = null
//    private lateinit var bodyPart: BodyPart
//    private var databaseRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setWhiteStatusBarColor()
        makeStatusBarTransparent()
//        databaseRef = FirebaseDatabase.getInstance().getReference("Exercises")
//        bodyPart = (intent.getSerializableExtra("THE_PART") as? BodyPart) ?: BodyPart.Leg
        exercise = (intent.getSerializableExtra("THE_EXERCISE") as? Exercise)
        exercise?.let { setupExercises(it) }
//        val name = intent.getStringExtra("EXERCISE_TITLE") ?: ""
//        getExercise(name)
    }

    private fun setupExercises(exercise: Exercise) {
        Glide.with(this).load(exercise.image).into(binding.image)
        binding.title.text = exercise.name
        binding.description.text = exercise.description
    }
//
//    private fun getExercise(name: String) {
//        databaseRef?.child(bodyPart.name)?.child(name)?.get()?.addOnSuccessListener {
//            Log.i("firebase", "Got value ${it.value}")
//            exercise = it.getValue<Exercise>()
//            Glide.with(this).load(exercise?.image).into(binding.image)
//            binding.title.text = exercise?.name
//            binding.description.text = exercise?.description
//        }?.addOnFailureListener {
//            Log.e("firebase", "Error getting data", it)
//        }
//    }
}