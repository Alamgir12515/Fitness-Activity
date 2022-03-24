package com.example.fitnessactivity.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessactivity.databinding.ActivityEditProfileBinding
import com.example.fitnessactivity.misc.GlobalSingleton
import com.example.fitnessactivity.models.User
import com.example.fitnessactivity.setWhiteStatusBarColor
import com.example.fitnessactivity.showToastShort
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    private var mAuth: FirebaseAuth? = null
    private var databaseRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setWhiteStatusBarColor()
        mAuth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().getReference("Users")
        fetchUser()
        addUserObserver()
        addButtonClickListener()
    }

    private fun addButtonClickListener() {
        binding.backIcon.setOnClickListener {
            onBackPressed()
        }
        binding.saveChangesButton.setOnClickListener {
            if (validateFields()) {
                val user = User(
                    binding.nameField.text.toString(),
                    binding.ageField.text.toString().toInt(),
                    binding.heightField.text.toString().toInt(),
                    binding.weightField.text.toString().toInt(),
                    binding.phoneNumberField.text.toString(),
                    binding.emailField.text.toString(),
                )
                if (user != GlobalSingleton.userLiveData.value) {
                    saveChanges(user)
                } else {
                    showToastShort("No changes detected!")
                }
            }
        }
    }

    private fun saveChanges(user: User) {
        mAuth?.currentUser?.uid?.let { uid ->
            databaseRef?.child(uid)?.setValue(user)?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToastShort("Saved changes successfully!")
                } else {
                    showToastShort("Can't connect to server, try again later!")
                }
            }
        }
    }

    private fun validateFields(): Boolean {
        UIUtil.hideKeyboard(this)
        val isAgeEntered =
            binding.ageField.text.isNotBlank() && (!binding.ageField.text.startsWith("0"))
        val isHeightValid =
            binding.heightField.text.isNotBlank() && binding.heightField.text.toString().toInt() > 0
        val isWeightValid =
            binding.weightField.text.isNotBlank() && binding.weightField.text.toString()
                .toInt() > 0
        var result = true
        val isEmailValid =
            android.util.Patterns.EMAIL_ADDRESS.matcher(binding.emailField.text.toString())
                .matches()
        if (isAgeEntered) {
            val age = binding.ageField.text.toString().toInt()
            result = if (age > 0) {
                true
            } else {
                binding.ageField.error = "Please enter valid age!"
                false
            }
        } else {
            binding.ageField.error = "Please enter valid age!"
        }
        if (!isHeightValid) {
            binding.heightField.error = "Please enter valid height!"
            result = false
        }
        if (!isWeightValid) {
            binding.weightField.error = "Please enter valid weight!"
            result = false
        }
        if (!isEmailValid) {
            binding.emailField.error = "Please enter valid email!"
            result = false
        }
        if (binding.phoneNumberField.text.toString() == "") {
            binding.phoneNumberField.error = "Please enter phone number!"
            result = false
        }
        if (binding.nameField.text.toString() == "") {
            binding.nameField.error = "Please enter valid name!"
            result = false
        }
        return result
    }

    private fun addUserObserver() {
        GlobalSingleton.getCurrentUserLiveData().observe(this) { user ->
            user?.let { setUserDetails(it) }
        }
    }

    private fun fetchUser() {
        mAuth?.currentUser?.uid?.let { uid ->
            databaseRef?.child(uid)?.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val user = snapshot.getValue<User>()
                    GlobalSingleton.userLiveData.postValue(user)
                    Log.e("userEditProfileActivity", user?.name.toString())
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("EditProfileActivity", "Failed to read value.", error.toException())
                }

            })
        }
    }

    private fun setUserDetails(myUser: User) {
        binding.nameField.setText(myUser.name)
        binding.emailField.setText(myUser.email)
        binding.ageField.setText(myUser.age.toString())
        binding.heightField.setText(myUser.height.toString())
        binding.weightField.setText(myUser.weight.toString())
        binding.phoneNumberField.setText(myUser.phone)
    }
}