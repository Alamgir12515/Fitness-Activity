package com.example.fitnessactivity.activities

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessactivity.addCardViewShadow
import com.example.fitnessactivity.data.LocalData
import com.example.fitnessactivity.databinding.ActivityDashboardBinding
import com.example.fitnessactivity.getBmiCategory
import com.example.fitnessactivity.misc.GlobalSingleton
import com.example.fitnessactivity.models.BmiCategory
import com.example.fitnessactivity.models.Exercise
import com.example.fitnessactivity.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import pub.devrel.easypermissions.EasyPermissions

enum class Destination {
    BMI_CALCULATOR, STEP_COUNTER, WORKOUT_SUGGESTION, VIDEO_SUGGESTION, DAILY_CHALLENGES, MORE
}

class DashboardActivity : AppCompatActivity() {
    private var PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    private var mAuth: FirebaseAuth? = null
    private var databaseRef: DatabaseReference? = null
    private var databaseRef2: DatabaseReference? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    private var PERMISSIONS_ABOVE_29 = arrayOf(
        Manifest.permission.ACTIVITY_RECOGNITION
    )
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().getReference("Users")
        databaseRef2 = FirebaseDatabase.getInstance().getReference("WeightLoss")
        addCardShadows()
        addUserObserver()
        addClickListeners()
        fetchUser()
//        uploadFirebaseData(LocalData.loss())
    }

//    private fun uploadFirebaseData(list: List<Exercise>) {
//        list.forEach {
//            databaseRef2?.child(it.name ?: "")?.setValue(it)
//            Log.e("dfddd${it.name}", "OK")
//        }
//    }

    private val permReqLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all { it.value }
            if (granted) {
                navigateToMainActivity(Destination.BMI_CALCULATOR)
            }
        }

    private val permReqLauncher2 =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all { it.value }
            if (granted) {
                navigateToMainActivity(Destination.STEP_COUNTER)
            }
        }

    private fun addClickListeners() {
        binding.bmiCard.setOnClickListener {
            if (EasyPermissions.hasPermissions(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                navigateToMainActivity(Destination.BMI_CALCULATOR)
            } else {
                permReqLauncher.launch(PERMISSIONS)
            }
        }
        binding.stepCounterCard.setOnClickListener {
            goToStepCounter()
        }
        binding.workoutSuggestionCard.setOnClickListener {
            startActivity(Intent(this, WorkoutSuggestionsActivity::class.java))
        }
        binding.videoSuggestionCard.setOnClickListener {
            navigateToMainActivity(Destination.VIDEO_SUGGESTION)
        }
        binding.dailyChallengesCard.setOnClickListener {
            var myCategory: BmiCategory? = null
            GlobalSingleton.userLiveData.value?.let {
                if (it.weight != null && it.height != null) {
                    val bmi =
                        GlobalSingleton.calculateBmi(it.weight!!.toFloat(), it.height!!.toFloat())
                    myCategory = bmi.getBmiCategory()
                }
            }
            val intent = Intent(this, DailyChallengesActivity::class.java).apply {
                putExtra("MyCategory", myCategory)
            }
            startActivity(intent)
        }
        binding.moreCard.setOnClickListener {
            navigateToMainActivity(Destination.MORE)
        }
    }

    private fun goToStepCounter() {
        if (SDK_INT >= Build.VERSION_CODES.Q) {
            if (EasyPermissions.hasPermissions(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                navigateToMainActivity(Destination.STEP_COUNTER)
            } else {
                permReqLauncher2.launch(PERMISSIONS_ABOVE_29)
            }
        } else {
            navigateToMainActivity(Destination.STEP_COUNTER)
        }
    }

    private fun navigateToMainActivity(destination: Destination) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("Destination", destination)
        }
        startActivity(intent)
    }

    private fun addCardShadows() {
        addCardViewShadow(binding.bmiCard)
        addCardViewShadow(binding.stepCounterCard)
        addCardViewShadow(binding.workoutSuggestionCard)
        addCardViewShadow(binding.videoSuggestionCard)
        addCardViewShadow(binding.dailyChallengesCard)
        addCardViewShadow(binding.moreCard)
    }

    private fun addUserObserver() {
        GlobalSingleton.getCurrentUserLiveData().observe(this) { user ->
            user?.let {
                val name = it.name.toString()
                binding.userName.text = name.replace(" ", "\n")
                Log.e("userDashboardActivity", name)
            }
        }
    }

    private fun fetchUser() {
        mAuth?.currentUser?.uid?.let {
            databaseRef?.child(it)?.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val user = snapshot.getValue<User>()
                    GlobalSingleton.userLiveData.postValue(user)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("EditProfileActivity", "Failed to read value.", error.toException())
                }

            })
        }
    }
}