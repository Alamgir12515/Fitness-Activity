package com.example.fitnessactivity.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessactivity.databinding.ActivityDailyChallengesBinding
import com.example.fitnessactivity.models.BmiCategory

class DailyChallengesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDailyChallengesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyChallengesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val category = intent.getSerializableExtra("MyCategory") as? BmiCategory
        Log.e("category", category?.name.toString())
    }
}