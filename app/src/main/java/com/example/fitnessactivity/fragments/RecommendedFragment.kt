package com.example.fitnessactivity.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessactivity.R
import com.example.fitnessactivity.adapters.ExerciseRVAdapter
import com.example.fitnessactivity.databinding.FragmentRecommendedBinding
import com.example.fitnessactivity.models.BmiCategory
import com.example.fitnessactivity.models.Exercise
import com.example.fitnessactivity.setDarkStatusBarColor
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class RecommendedFragment : Fragment() {
    companion object {
        fun newInstance() = RecommendedFragment()
    }

    private var category: BmiCategory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = arguments?.getSerializable("MyCategory") as? BmiCategory
    }


    private var _binding: FragmentRecommendedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecommendedBinding.inflate(inflater, container, false)
        activity?.setDarkStatusBarColor(R.color.bmiBackgroundColor)
        category?.let { setupByCategory(it) }
        return binding.root
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