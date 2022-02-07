package com.example.fitnessactivity.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.fitnessactivity.adapters.TabLayoutAdapter
import com.example.fitnessactivity.databinding.ActivityDailyChallengesBinding
import com.example.fitnessactivity.fragments.CustomFragment
import com.example.fitnessactivity.fragments.RecommendedFragment
import com.example.fitnessactivity.models.BmiCategory
import com.google.firebase.database.DatabaseReference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyChallengesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDailyChallengesBinding
    private var tabLayoutAdapter: TabLayoutAdapter? = null

    private var databaseRef: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyChallengesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val category = intent.getSerializableExtra("MyCategory") as? BmiCategory
        Log.e("category", category?.name.toString())
        setupViewPager(category)
//        category?.let { setupByCategory(it) }
    }

    private fun setupViewPager(category: BmiCategory?) {
        val recommendedFragment = RecommendedFragment.newInstance()
        category?.let {
            recommendedFragment.arguments = bundleOf("MyCategory" to it)
        }
        val customFragment = CustomFragment.newInstance()
        tabLayoutAdapter = TabLayoutAdapter(supportFragmentManager, listOf("Recommended", "Custom"))
        tabLayoutAdapter?.addFragment(recommendedFragment)
        tabLayoutAdapter?.addFragment(customFragment)
        binding.viewpager.adapter = tabLayoutAdapter
        binding.viewpager.offscreenPageLimit = tabLayoutAdapter!!.count - 1
        binding.tabLayout.setupWithViewPager(binding.viewpager)
    }
}