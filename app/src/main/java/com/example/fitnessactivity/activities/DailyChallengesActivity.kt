package com.example.fitnessactivity.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessactivity.adapters.TabLayoutAdapter
import com.example.fitnessactivity.databinding.ActivityDailyChallengesBinding
import com.example.fitnessactivity.fragments.CustomFragment
import com.example.fitnessactivity.fragments.RecommendedFragment
import com.example.fitnessactivity.setWhiteStatusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyChallengesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDailyChallengesBinding
    private var tabLayoutAdapter: TabLayoutAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyChallengesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setWhiteStatusBarColor()
        setupViewPager()
    }

    private fun setupViewPager() {
        val recommendedFragment = RecommendedFragment.newInstance()
        val customFragment = CustomFragment.newInstance()
        tabLayoutAdapter = TabLayoutAdapter(supportFragmentManager, listOf("Recommended", "Custom"))
        tabLayoutAdapter?.addFragment(recommendedFragment)
        tabLayoutAdapter?.addFragment(customFragment)
        binding.viewpager.adapter = tabLayoutAdapter
        binding.viewpager.offscreenPageLimit = tabLayoutAdapter!!.count - 1
        binding.tabLayout.setupWithViewPager(binding.viewpager)
    }
}