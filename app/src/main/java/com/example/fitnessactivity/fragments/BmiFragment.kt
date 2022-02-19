package com.example.fitnessactivity.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessactivity.R
import com.example.fitnessactivity.activities.DailyChallengesActivity
import com.example.fitnessactivity.databinding.FragmentBmiBinding
import com.example.fitnessactivity.getBmiCategory
import com.example.fitnessactivity.misc.GlobalSingleton
import com.example.fitnessactivity.setDarkStatusBarColor
import com.example.fitnessactivity.showToastShort
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
    private var myBmi: Float? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBmiBinding.inflate(inflater, container, false)
        activity?.setDarkStatusBarColor(R.color.bmiBackgroundColor)
        addCalculateButtonClickListener()
        addUserObserver()
        return binding.root
    }

    private fun addUserObserver() {
        GlobalSingleton.userLiveData.value?.let {
            binding.editTextWeight.setText((it.weight ?: 0).toString())
            binding.editTextHeight.setText((it.height ?: 1).toString())
            binding.editTextAge.setText((it.age ?: 0).toString())
            binding.calculateBmiButton.performClick()
        }
    }

    private fun addCalculateButtonClickListener() {
        binding.calculateBmiButton.setOnClickListener {
            if (binding.calculateBmiButton.tag == getString(R.string.recalculate)) {
                binding.calculateBmiButton.tag = getString(R.string.calculate)
                resetBmiDetails()
            } else {
                binding.calculateBmiButton.tag = getString(R.string.recalculate)
                UIUtil.hideKeyboard(requireActivity())
                calculateBmi()
            }
        }
        binding.bmiResultValue.setOnClickListener {
            myBmi?.let {
                showToastShort(it.getBmiCategory().name)
            }
        }
    }

    private fun showAlertBox(bmi: Float) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.dialog_title))
            .setMessage(resources.getString(R.string.supporting_text))
            .setNegativeButton(resources.getString(R.string.noThanks)) { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.go)) { dialog, which ->
                val intent = Intent(requireContext(), DailyChallengesActivity::class.java).apply {
                    putExtra("isFromBMI", true)
                    putExtra("myCategory", bmi.getBmiCategory())
                }
                startActivity(intent)
            }
            .show()
    }

    private fun resetBmiDetails() {
        binding.editTextWeight.setText("")
        binding.editTextHeight.setText("")
        binding.editTextAge.setText("")
        binding.bmiResultValue.text = "??.?"
        binding.calculateBmiButton.text = getString(R.string.calculateBmi)
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
        val bmi = GlobalSingleton.calculateBmi(weight, height)
        myBmi = bmi
        binding.bmiResultValue.text = DecimalFormat("#.#").format(bmi)
        binding.calculateBmiButton.tag = getString(R.string.recalculate)
        binding.calculateBmiButton.text = getString(R.string.recalculateBmi)
        showAlertBox(bmi)
//        binding.bmiResultCategory.text = getCategory(bmi)
    }
}