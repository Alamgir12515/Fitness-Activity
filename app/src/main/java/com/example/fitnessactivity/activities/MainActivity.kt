package com.example.fitnessactivity.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fitnessactivity.R
import com.example.fitnessactivity.databinding.ActivityMainBinding
import com.example.fitnessactivity.misc.GlobalSingleton
import com.example.fitnessactivity.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    private var mAuth: FirebaseAuth? = null
    private var databaseRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().getReference("Users")
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)
        val destination = intent.getSerializableExtra("Destination") as? Destination
        navigateToRelatedFragment(destination)
        fetchUser()
    }

    override fun onBackPressed() {
        finish()
    }

    private fun navigateToRelatedFragment(destination: Destination?) {
        destination?.let {
            when (it) {
                Destination.BMI_CALCULATOR -> {
                    return@let
                }
                Destination.STEP_COUNTER -> {
                    navController.navigate(R.id.nav_step_counter)
                }
                Destination.WORKOUT_SUGGESTION -> {
                    navController.navigate(R.id.nav_bmi)
                }
                Destination.VIDEO_SUGGESTION -> {
                    navController.navigate(R.id.nav_youtube)
                }
                Destination.DAILY_CHALLENGES -> {
                    navController.navigate(R.id.nav_nearest_gym)
                }
                Destination.MORE -> {
                    navController.navigate(R.id.nav_profile)
                }
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
                    Log.e("userMainActivity", user?.name.toString())
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("EditProfileActivity", "Failed to read value.", error.toException())
                }

            })
        }
    }

}