package com.example.fitnessactivity.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessactivity.R
import com.example.fitnessactivity.databinding.FragmentBmiBinding
import com.example.fitnessactivity.setDarkStatusBarColor
import com.example.fitnessactivity.showToastShort
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil
import java.text.DecimalFormat

class BmiFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private var _binding: FragmentBmiBinding? = null
    private var height = 0f
    private var weight = 0f
    private var age = 0f

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBmiBinding.inflate(inflater, container, false)
        requireActivity().setDarkStatusBarColor(R.color.bmiBackgroundColor)
        binding.calculateBmiButton.setOnClickListener {
            UIUtil.hideKeyboard(requireActivity())
            calculateBmi()
        }
        return binding.root
    }

    private fun calculateBmi() {
        val weightString = binding.editTextWeight.text.toString()
        val heightString = binding.editTextHeight.text.toString()
        val ageString = binding.editTextAge.text.toString()
        weight = if (weightString.isEmpty()) 0f else weightString.toFloat()
        height = if (heightString.isEmpty()) 0f else heightString.toFloat()
        age = if (ageString.isEmpty()) 0f else ageString.toFloat()
        if (weight == 0f || height == 0f || age == 0f) {
            showToastShort("Please enter details first!")
            return
        }
        val bmi = (weight / ((height * height) / 10000))
        val df = DecimalFormat("#.#")
        binding.bmiResultValue.text = df.format(bmi)
//        binding.bmiResultCategory.text = getCategory(bmi)
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