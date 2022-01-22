package com.example.fitnessactivity.fragments

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessactivity.adapters.VideosRVAdapter
import com.example.fitnessactivity.data.LocalData
import com.example.fitnessactivity.databinding.FragmentVideosBinding
import com.example.fitnessactivity.setWhiteStatusBarColor
import com.example.fitnessactivity.smoothScrollTo
import com.google.android.material.tabs.TabLayout


class VideosFragment : Fragment() {

    private var _binding: FragmentVideosBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().setWhiteStatusBarColor()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentVideosBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        setupRecyclerView()
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        binding.nestedScrollView.smoothScrollTo(binding.bodyBuildingHeading)
                    }
                    1 -> {
                        binding.nestedScrollView.smoothScrollTo(binding.weightLossHeading)
                    }
                    else -> {
                        binding.nestedScrollView.smoothScrollTo(binding.weightGainHeading)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //onTabUnselected
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        binding.nestedScrollView.smoothScrollTo(binding.bodyBuildingHeading)
                    }
                    1 -> {
                        binding.nestedScrollView.smoothScrollTo(binding.weightLossHeading)
                    }
                    else -> {
                        binding.nestedScrollView.smoothScrollTo(binding.weightGainHeading)
                    }
                }
            }

        })
    }

    private fun setupRecyclerView() {
        binding.bodyBuildingRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = VideosRVAdapter(this@VideosFragment, LocalData.getBodyBuildingDataList())
        }
        binding.weightLossRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = VideosRVAdapter(this@VideosFragment, LocalData.getWeightLossDataList())
        }
        binding.weightGainRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = VideosRVAdapter(this@VideosFragment, LocalData.getWeightGainDataList())
        }
    }
//
//    private fun isViewVisible(view: View): Boolean {
//        val scrollBounds = Rect()
//        binding.nestedScrollView.getDrawingRect(scrollBounds)
//        val top = view.y
//        val bottom = top + view.height
//        return scrollBounds.top < top && scrollBounds.bottom > bottom
//    }
}