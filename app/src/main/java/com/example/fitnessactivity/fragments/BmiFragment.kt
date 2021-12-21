package com.example.fitnessactivity.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessactivity.databinding.FragmentBmiBinding
import com.example.fitnessactivity.makeGone
import com.example.fitnessactivity.makeVisible
import java.text.DecimalFormat

class BmiFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private var _binding: FragmentBmiBinding? = null
    private var height = 50f
    private var weight = 20f

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBmiBinding.inflate(inflater, container, false)
        binding.heightSlider.addOnChangeListener { slider, value, fromUser ->
            height = value
            binding.heightText.text = "${value.toInt()} cm"
        }
        binding.weightSlider.addOnChangeListener { slider, value, fromUser ->
            weight = value
            binding.weightText.text = "${value.toInt()} kg"
        }
        binding.calculateBmiButton.setOnClickListener {
            calculateBmi()
            binding.contentLayout.makeGone()
        }
        binding.recalculateBmiButton.setOnClickListener {
            binding.contentLayout.makeVisible()
        }
        return binding.root
    }

    private fun calculateBmi() {
        val bmi = (weight / ((height * height) / 10000))
        val df = DecimalFormat("#.#")
        binding.bmiResultValue.text = df.format(bmi)
        binding.bmiResultCategory.text = getCategory(bmi)
    }

    private fun getCategory(bmi: Float): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi in 18.5..24.9 -> "Normal"
            bmi in 25.0..29.9 -> "Overweight"
            else -> "Obese"
        }
    }
}