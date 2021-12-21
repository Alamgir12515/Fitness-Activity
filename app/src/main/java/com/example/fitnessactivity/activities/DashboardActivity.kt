package com.example.fitnessactivity.activities

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessactivity.addCardViewShadow
import com.example.fitnessactivity.databinding.ActivityDashboardBinding
import com.example.fitnessactivity.setWhiteStatusBarColor
import pub.devrel.easypermissions.EasyPermissions

private const val REQUEST_CODE_PERMISSIONS = 10

enum class Destination {
    BMI_CALCULATOR, STEP_COUNTER, WORKOUT_SUGGESTION, VIDEO_SUGGESTION, DAILY_CHALLENGES
}

class DashboardActivity : AppCompatActivity() {
    private var PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setWhiteStatusBarColor()
        addCardShadows()
        addClickListeners()
    }

    private val permReqLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all { it.value }
            if (granted) {
                navigateToMainActivity(Destination.BMI_CALCULATOR)
            }
        }

    private fun addClickListeners() {
        binding.bmiCard.setOnClickListener {
            if (EasyPermissions.hasPermissions(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                navigateToMainActivity(Destination.BMI_CALCULATOR)
            } else {
                permReqLauncher.launch(PERMISSIONS)
            }
        }
        binding.stepCounterCard.setOnClickListener {
            navigateToMainActivity(Destination.STEP_COUNTER)
        }
        binding.workoutSuggestionCard.setOnClickListener {
            navigateToMainActivity(Destination.WORKOUT_SUGGESTION)
        }
        binding.videoSuggestionCard.setOnClickListener {
            navigateToMainActivity(Destination.VIDEO_SUGGESTION)
        }
        binding.dailyChallengesCard.setOnClickListener {
            navigateToMainActivity(Destination.DAILY_CHALLENGES)
        }
    }

    private fun navigateToMainActivity(destination: Destination) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("Destination", destination)
        }
        startActivity(intent)
    }

    private fun addCardShadows() {
        addCardViewShadow(binding.bmiCard)
        addCardViewShadow(binding.stepCounterCard)
        addCardViewShadow(binding.workoutSuggestionCard)
        addCardViewShadow(binding.videoSuggestionCard)
        addCardViewShadow(binding.dailyChallengesCard)
        addCardViewShadow(binding.logoutCard)
    }
}