package com.example.fitnessactivity.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.example.fitnessactivity.R
import com.example.fitnessactivity.activities.AddChallengeActivity
import com.example.fitnessactivity.adapters.OrderExpandableListAdapter
import com.example.fitnessactivity.databinding.FragmentCustomBinding
import com.example.fitnessactivity.models.CustomChallenge
import com.example.fitnessactivity.roomDb.ChallengeDao
import com.example.fitnessactivity.roomDb.LocalDataSource
import com.example.fitnessactivity.setDarkStatusBarColor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CustomFragment : Fragment() {
    companion object {
        fun newInstance() = CustomFragment()
    }

    @Inject
    lateinit var localDataSource: LocalDataSource

    @Inject
    lateinit var challengeDao: ChallengeDao

    private var challengesLiveData: LiveData<List<CustomChallenge>>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        challengesLiveData = challengeDao.getAll()
    }

    private var _binding: FragmentCustomBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomBinding.inflate(inflater, container, false)
        activity?.setDarkStatusBarColor(R.color.bmiBackgroundColor)
        addChallengesLiveDataObserver()
        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(requireActivity(), AddChallengeActivity::class.java))
        }
//        val type = object : TypeToken<List<Exercise>>() {}.type
        return binding.root
    }

    private fun addChallengesLiveDataObserver() {
        challengesLiveData?.observe(viewLifecycleOwner) {
            Log.e("waloo", it.size.toString())
        }
    }

}