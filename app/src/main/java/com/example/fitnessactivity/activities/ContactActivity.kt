package com.example.fitnessactivity.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessactivity.contactMessenger
import com.example.fitnessactivity.databinding.ActivityContactBinding
import com.example.fitnessactivity.getWhatsappIntent
import com.example.fitnessactivity.setWhiteStatusBarColor

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setWhiteStatusBarColor()
        binding.whatsappCard.setOnClickListener {
            startActivity(getWhatsappIntent())
        }
        binding.messengerCard.setOnClickListener {
            contactMessenger()
        }
        binding.backIcon.setOnClickListener {
            onBackPressed()
        }
    }
}