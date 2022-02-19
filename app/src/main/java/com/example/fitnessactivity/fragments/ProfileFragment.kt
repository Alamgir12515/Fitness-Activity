package com.example.fitnessactivity.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessactivity.R
import com.example.fitnessactivity.activities.AboutUsActivity
import com.example.fitnessactivity.activities.ContactActivity
import com.example.fitnessactivity.activities.EditProfileActivity
import com.example.fitnessactivity.activities.LoginActivity
import com.example.fitnessactivity.databinding.FragmentProfileBinding
import com.example.fitnessactivity.misc.GlobalSingleton
import com.example.fitnessactivity.setDarkStatusBarColor
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
    }

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        requireActivity().setDarkStatusBarColor(R.color.bmiBackgroundColor)
        addUserObserver()
        addButtonClickListeners()
        return binding.root
    }

    private fun addButtonClickListeners() {
        binding.logoutCard.setOnClickListener {
            signOut()
        }
        binding.editProfileCard.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }
        binding.contactUsCard.setOnClickListener {
            val intent = Intent(requireContext(), ContactActivity::class.java)
            startActivity(intent)
        }
        binding.aboutUsCard.setOnClickListener {
            val intent = Intent(requireContext(), AboutUsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addUserObserver() {
        GlobalSingleton.getCurrentUserLiveData().observe(viewLifecycleOwner) { user ->
            user?.let {
                val name = it.name.toString()
                binding.userName.text = name
            }
        }
    }

    private fun signOut() {
        mAuth?.signOut()
        startActivity(Intent(requireContext(), LoginActivity::class.java))
        requireActivity().finish()
    }
}