package com.example.fitnessactivity.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessactivity.R
import com.example.fitnessactivity.databinding.ActivitySignUpBinding
import com.example.fitnessactivity.makeGone
import com.example.fitnessactivity.makeVisible
import com.example.fitnessactivity.models.User
import com.example.fitnessactivity.showToastShort
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    private var mAuth: FirebaseAuth? = null
    private var databaseRef: DatabaseReference? = null
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseRef = FirebaseDatabase.getInstance().getReference("Users")
        mAuth = FirebaseAuth.getInstance()
        binding.signUpButton.setOnClickListener(this)
    }

    private fun signUp(email: String, password: String) {
        showLoading()
        mAuth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(this) { task ->
                hideLoading()
                if (task.isSuccessful) {
                    Log.e("TAG", "createUserWithEmail:success")
                    val user = User(
                        binding.nameEditText.text.toString(),
                        binding.ageEditText.text.toString().toInt(),
                        binding.heightEditText.text.toString().toInt(),
                        binding.weightEditText.text.toString().toInt(),
                        binding.phoneEditText.text.toString(),
                        email
                    )
                    databaseRef?.child(mAuth?.currentUser?.uid.toString())?.setValue(user)
                        ?.addOnCompleteListener { taskAdd ->
                            if (taskAdd.isSuccessful) {
                                updateUI(mAuth?.currentUser)
                            } else {
                                Log.e("UserSignUp", taskAdd.exception.toString())
                                showToastShort("User signUp add failed!")
                            }
                        }
                } else {
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        this@SignUpActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }


    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        } else {
            showToastShort("User is null!")
        }
    }

    private fun showLoading() {
        binding.progressBar.makeVisible()
        binding.signUpButton.isEnabled = false
    }

    private fun hideLoading() {
        binding.progressBar.makeGone()
        binding.signUpButton.isEnabled = true
    }

    private fun validateFields(): Boolean {
        UIUtil.hideKeyboard(this)
        val isAgeEntered =
            binding.ageEditText.text.isNotBlank() && (!binding.ageEditText.text.startsWith("0"))
        val isHeightValid =
            binding.heightEditText.text.isNotBlank() && binding.heightEditText.text.toString()
                .toInt() > 0
        val isWeightValid =
            binding.weightEditText.text.isNotBlank() && binding.weightEditText.text.toString()
                .toInt() > 0
        var result = true
        val isEmailValid =
            android.util.Patterns.EMAIL_ADDRESS.matcher(binding.emailEditText.text.toString())
                .matches()
        if (isAgeEntered) {
            val age = binding.ageEditText.text.toString().toInt()
            result = if (age > 0) {
                true
            } else {
                binding.ageEditText.error = "Please enter valid age!"
                false
            }
        } else {
            binding.ageEditText.error = "Please enter valid age!"
        }
        if (!isHeightValid) {
            binding.heightEditText.error = "Please enter valid height!"
            result = false
        }
        if (!isWeightValid) {
            binding.weightEditText.error = "Please enter valid weight!"
            result = false
        }
        if (!isEmailValid) {
            binding.emailEditText.error = "Please enter valid email!"
            result = false
        }
        if (binding.phoneEditText.text.toString() == "") {
            binding.phoneEditText.error = "Please enter phone number!"
            result = false
        }
        if (binding.nameEditText.text.toString() == "") {
            binding.nameEditText.error = "Please enter valid name!"
            result = false
        }
        if (binding.passwordEditText.text.toString() == "") {
            binding.passwordEditText.error = "Please enter valid password!"
            result = false
        }
        return result
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.signUpButton -> {
                if (validateFields()) {
                    signUp(
                        binding.emailEditText.text.toString(),
                        binding.passwordEditText.text.toString()
                    )
                }
            }
        }
    }
}