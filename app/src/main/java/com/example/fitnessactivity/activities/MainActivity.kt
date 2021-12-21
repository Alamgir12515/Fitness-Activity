package com.example.fitnessactivity.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fitnessactivity.R
import com.example.fitnessactivity.databinding.ActivityMainBinding
import com.example.fitnessactivity.setWhiteStatusBarColor

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setWhiteStatusBarColor()
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)
        val destination = intent.getSerializableExtra("Destination") as? Destination
        navigateToRelatedFragment(destination)
    }

    private fun navigateToRelatedFragment(destination: Destination?) {
        destination?.let {
            when (it) {
                Destination.BMI_CALCULATOR -> {
                    return@let
                }
                Destination.STEP_COUNTER -> {
                    navController.navigate(R.id.nav_step_counter)
                }
                Destination.WORKOUT_SUGGESTION -> {
                    navController.navigate(R.id.nav_bmi)
                }
                Destination.VIDEO_SUGGESTION -> {
                    navController.navigate(R.id.nav_youtube)
                }
                Destination.DAILY_CHALLENGES -> {
                    navController.navigate(R.id.nav_nearest_gym)
                }
            }
        }
    }

}