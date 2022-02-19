package com.example.fitnessactivity.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessactivity.adapters.AboutUsRVAdapter
import com.example.fitnessactivity.data.LocalData
import com.example.fitnessactivity.databinding.ActivityAboutUsBinding

class AboutUsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@AboutUsActivity)
            adapter = AboutUsRVAdapter(this@AboutUsActivity, LocalData.getOnBoardingData())
        }
    }
}