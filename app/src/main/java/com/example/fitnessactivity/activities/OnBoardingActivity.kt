package com.example.fitnessactivity.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessactivity.R
import com.example.fitnessactivity.adapters.IntroViewPagerAdapter
import com.example.fitnessactivity.data.LocalData
import com.example.fitnessactivity.databinding.ActivityOnBoardingBinding
import com.example.fitnessactivity.makeInvisible
import com.example.fitnessactivity.makeVisible
import com.google.android.material.tabs.TabLayout

class OnBoardingActivity : AppCompatActivity() {
    lateinit var buttonAnimation: Animation
    var currentPosition = 0
    private lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (checkSeenState()) {
            val intent = Intent(this, AuthenticationActivity::class.java)
            startActivity(intent)
            finish()
        }
        initViews()
    }

    private fun initViews() {
        buttonAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.get_started_button_animation)
        val list = LocalData.getOnBoardingData()
        val adapter = IntroViewPagerAdapter(this, list)
        binding.introViewpager.adapter = adapter
        binding.tabIndicator.setupWithViewPager(binding.introViewpager)

        binding.introNextButton.setOnClickListener {
            currentPosition = binding.introViewpager.currentItem
            if (currentPosition < list.size) {
                currentPosition += 1
                binding.introViewpager.currentItem = currentPosition
            }
            if (currentPosition == list.size - 1) {
                loadLastScreen()
            }
        }

        binding.tabIndicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == list.size - 1) {
                    loadLastScreen()
                } else {
                    unloadLastScreen()
                }
            }
        })

        binding.getStartedButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            saveSeenState()
            finish()
        }
    }

    private fun loadLastScreen() {
        binding.apply {
            introNextButton.makeInvisible()
            tabIndicator.makeInvisible()
            getStartedButton.makeVisible()
            getStartedButton.animation = buttonAnimation
        }
    }

    fun unloadLastScreen() {
        binding.apply {
            introNextButton.makeVisible()
            tabIndicator.makeVisible()
            getStartedButton.makeInvisible()
        }
    }

    private fun saveSeenState() {
        val preferences: SharedPreferences =
            applicationContext.getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putBoolean("seen", true)
        editor.apply()
    }

    private fun checkSeenState(): Boolean {
        val preferences: SharedPreferences =
            applicationContext.getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        return preferences.getBoolean("seen", false)
    }
}