package com.example.fitnessactivity.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessactivity.*
import com.example.fitnessactivity.adapters.ExerciseRVAdapter
import com.example.fitnessactivity.databinding.FragmentRecommendedBinding
import com.example.fitnessactivity.misc.GlobalSingleton
import com.example.fitnessactivity.models.BmiCategory
import com.example.fitnessactivity.models.Exercise
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class RecommendedFragment : Fragment() {
    companion object {
        fun newInstance() = RecommendedFragment()
    }

    private var isFromBMI = false
    private var myCategory = BmiCategory.Normal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isFromBMI = arguments?.getBoolean("isFromBMI") ?: false
        myCategory =
            (arguments?.getSerializable("myCategory") as? BmiCategory) ?: BmiCategory.Normal
    }


    private var _binding: FragmentRecommendedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecommendedBinding.inflate(inflater, container, false)
        activity?.setDarkStatusBarColor(R.color.bmiBackgroundColor)
        if (isFromBMI) {
            setupByCategory(myCategory)
        } else {
            fetchData()
        }
        return binding.root
    }

    private fun fetchData() {
        showLoading()
        GlobalSingleton.userLiveData.observe(viewLifecycleOwner) { value ->
            "userY".printLog(value != null)
            value?.let {
                if (it.weight != null && it.height != null) {
                    val bmi =
                        GlobalSingleton.calculateBmi(it.weight!!.toFloat(), it.height!!.toFloat())
                    val myCategory = bmi.getBmiCategory()
                    setupByCategory(myCategory)
                }
            }
        }
    }

    private fun showLoading() {
        binding.progressBar3.makeVisible()
        binding.recyclerView.makeGone()
    }

    private fun hideLoading() {
        binding.progressBar3.makeGone()
        binding.recyclerView.makeVisible()
    }

    private fun setupByCategory(category: BmiCategory) {
        val databaseRef = when (category) {
            BmiCategory.Underweight -> {
                FirebaseDatabase.getInstance().getReference("WeightGain")
            }
            BmiCategory.Normal -> {
                FirebaseDatabase.getInstance().getReference("WeightGain")
            }
            BmiCategory.Overweight -> {
                FirebaseDatabase.getInstance().getReference("WeightLoss")
            }
            BmiCategory.Obese -> {
                FirebaseDatabase.getInstance().getReference("WeightLoss")
            }
        }
        val list = arrayListOf<Exercise>()
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    postSnapshot.getValue<Exercise>()?.let {
                        list.add(it)
                    }
                }
                hideLoading()
                Log.e("Print${category.name}", list.size.toString())
                binding.recyclerView.apply {
                    adapter = ExerciseRVAdapter(requireActivity(), list)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("Category:onCancelled", databaseError.toException().toString())
            }
        })
    }

}