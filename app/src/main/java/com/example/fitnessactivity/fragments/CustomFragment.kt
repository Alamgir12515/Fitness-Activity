package com.example.fitnessactivity.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessactivity.R
import com.example.fitnessactivity.activities.AddChallengeActivity
import com.example.fitnessactivity.adapters.ChallengesRVAdapter
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
    lateinit var challengeDao: ChallengeDao

    @Inject
    lateinit var localDataSource: LocalDataSource

    private var challengesLiveData: LiveData<List<CustomChallenge>>? = null
    private var challenges: ArrayList<CustomChallenge>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        challengesLiveData = challengeDao.getAll()
    }

    private var _binding: FragmentCustomBinding? = null
    private val binding get() = _binding!!
    private var rvAdapter: ChallengesRVAdapter? = null

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
        return binding.root
    }

    private fun addSwipeToDelete() {
        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                    //Remove swiped item from list and notify the RecyclerView
                    removeChallenge(viewHolder.adapterPosition)
                }
            }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun removeChallenge(position: Int) {
        localDataSource.removeChallenge(challenges!![position])
        challenges?.removeAt(position)
        rvAdapter?.notifyItemRemoved(position)
    }

    private fun addChallengesLiveDataObserver() {
        challengesLiveData?.observe(viewLifecycleOwner) {
            challenges = ArrayList(it)
            setupRecyclerView(it)
            addSwipeToDelete()
        }
    }

    private fun setupRecyclerView(list: List<CustomChallenge>) {
        rvAdapter = ChallengesRVAdapter(this@CustomFragment, list)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rvAdapter
        }
    }

}