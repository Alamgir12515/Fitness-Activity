package com.example.fitnessactivity.activities

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessactivity.addCardViewShadow
import com.example.fitnessactivity.databinding.ActivityDashboardBinding
import com.example.fitnessactivity.setWhiteStatusBarColor
import pub.devrel.easypermissions.EasyPermissions

private const val REQUEST_CODE_PERMISSIONS = 10

enum class Destination {
    BMI_CALCULATOR, STEP_COUNTER, WORKOUT_SUGGESTION, VIDEO_SUGGESTION, DAILY_CHALLENGES, MORE
}

class DashboardActivity : AppCompatActivity() {
    private var PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    @RequiresApi(Build.VERSION_CODES.Q)
    private var PERMISSIONS_ABOVE_29 = arrayOf(
        Manifest.permission.ACTIVITY_RECOGNITION
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

    private val permReqLauncher2 =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all { it.value }
            if (granted) {
                navigateToMainActivity(Destination.STEP_COUNTER)
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
            goToStepCounter()
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
        binding.moreCard.setOnClickListener {
            navigateToMainActivity(Destination.MORE)
        }
    }

    private fun goToStepCounter() {
        if (SDK_INT >= Build.VERSION_CODES.Q) {
            if (EasyPermissions.hasPermissions(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                navigateToMainActivity(Destination.STEP_COUNTER)
            } else {
                permReqLauncher2.launch(PERMISSIONS_ABOVE_29)
            }
        } else {
            navigateToMainActivity(Destination.STEP_COUNTER)
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
        addCardViewShadow(binding.moreCard)
    }
}