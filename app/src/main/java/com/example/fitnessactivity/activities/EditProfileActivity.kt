package com.example.fitnessactivity.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessactivity.databinding.ActivityEditProfileBinding
import com.example.fitnessactivity.setWhiteStatusBarColor

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setWhiteStatusBarColor()
    }
}