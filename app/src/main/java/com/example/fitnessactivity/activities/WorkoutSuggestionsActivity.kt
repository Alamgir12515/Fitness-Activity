package com.example.fitnessactivity.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessactivity.R
import com.example.fitnessactivity.databinding.ActivityWorkoutSuggestionsBinding
import com.example.fitnessactivity.misc.GlobalSingleton
import com.example.fitnessactivity.models.BodyPart
import com.example.fitnessactivity.setDarkStatusBarColor

class WorkoutSuggestionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkoutSuggestionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutSuggestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setDarkStatusBarColor(R.color.black)
        addClickListeners()
        addUserObserver()
    }


    private fun addUserObserver() {
        GlobalSingleton.getCurrentUserLiveData().observe(this) { user ->
            binding.userName.text = user?.name?.replace(" ", "\n")
        }
    }

    private fun addClickListeners() {
        binding.legCard.setOnClickListener {
            openExercises(BodyPart.Leg)
        }
        binding.chestCard.setOnClickListener {
            openExercises(BodyPart.Chest)
        }
        binding.shoulderCard.setOnClickListener {
            openExercises(BodyPart.Shoulder)
        }
        binding.backCard.setOnClickListener {
            openExercises(BodyPart.Back)
        }
        binding.armsCard.setOnClickListener {
            openExercises(BodyPart.Arms)
        }
        binding.absCard.setOnClickListener {
            openExercises(BodyPart.Abs)
        }
    }

    private fun openExercises(part: BodyPart) {
        startActivity(Intent(this, BodyPartActivity::class.java).apply {
            putExtra("THE_PART", part)
        })
    }
}