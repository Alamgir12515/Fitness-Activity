package com.example.fitnessactivity.fragments

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitnessactivity.*
import com.example.fitnessactivity.adapters.DaysRVAdapter
import com.example.fitnessactivity.databinding.FragmentStepCounterBinding
import com.example.fitnessactivity.misc.GlobalSingleton
import com.example.fitnessactivity.models.Day
import com.example.fitnessactivity.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class StepCounterFragment : Fragment(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var databaseRef: DatabaseReference? = null
    private var mAuth: FirebaseAuth? = null

    // Creating a variable which will give the running status
    // and initially given the boolean value as false
    private var running = false

    // Creating a variable which will counts total steps
    // and it has been given the value of 0 float
    private var totalSteps = 0f
    private var previousStepCount = 0f

    private var user: User? = null

    private var _binding: FragmentStepCounterBinding? = null
    private val binding get() = _binding!!
    var selectedDayPosition = 0
    private var currentDayPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Adding a context of SENSOR_SERVICE aas Sensor Manager
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAuth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().getReference("Users")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStepCounterBinding.inflate(inflater, container, false)
        requireActivity().setWhiteStatusBarColor()
        setupDays()
        binding.resetButton.setOnClickListener {
            saveChanges()
        }
        getAllDaysStepCount { daysSteps ->
            setupStepCount(daysSteps, currentDate())
        }
        fetchUser()
        return binding.root
    }

    private fun setupDays() {
        getDaysOfWeek().forEachIndexed { index, s ->
            if (s.isCurrentDate()) {
                selectedDayPosition = index
                currentDayPosition = index
            }
        }
        setupRecyclerView(currentDayPosition)
    }

    private fun setupRecyclerView(selectedPosition: Int) {
        binding.recyclerView.apply {
            adapter = DaysRVAdapter(this@StepCounterFragment, getDaysOfWeek(), selectedPosition)
        }
    }

    private fun getAllDaysStepCount(callback: (ArrayList<Day>) -> Unit) {
        mAuth?.currentUser?.uid?.let { uid ->
            databaseRef?.child(uid)?.child("Daily Steps")
                ?.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val daysSteps = ArrayList<Day>()
                        snapshot.children.forEach { snap ->
                            snap.getValue<Day>()?.let {
                                daysSteps.add(it)
                            }
                        }
                        callback(daysSteps)
//                        setupStepCount(daysSteps)
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }

                })

        }
    }

    private fun fetchUser() {
        GlobalSingleton.getCurrentUserLiveData().observe(viewLifecycleOwner) { user ->
            this.user = user
        }
    }

    private fun setupStepCount(daysSteps: ArrayList<Day>, selectedDate: String) {
        val currentDateSteps = daysSteps.filter { it.date == selectedDate }
        if (currentDateSteps.isNotEmpty()) {
            previousStepCount = currentDateSteps[0].steps?.toFloat() ?: 0f
            binding.stepCount.text = currentDateSteps[0].steps.toString()
            calculateCalories()
        }
    }

    private fun saveChanges() {
        mAuth?.currentUser?.uid?.let { uid ->
            val newStepCount = totalSteps.toInt() + previousStepCount.toInt()
            val map = mapOf(currentDate() to Day(currentDate(), newStepCount))
            databaseRef?.child(uid)?.child("Daily Steps")?.updateChildren(map)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        binding.counter.text = "0"
                        totalSteps = 0f
                    } else {
                        showToastShort("Can't connect to server, try again later!")
                    }
                }
        }
    }

    private fun calculateCalories() {
        user?.weight?.let {
            val caloriesPerStep = ((0.5) * (it * 2.205)) / 1400
            val totalCalories = caloriesPerStep * previousStepCount
            binding.caloriesCount.text = totalCalories.toInt().toString()
        }
        user?.age?.let {
            val bpm = 220 - it
            binding.bpmCount.text = bpm.toString()
        }
    }

    private fun currentDate(): String {
        return SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
    }

    private fun getDaysOfWeek(): ArrayList<String> {
        val format: DateFormat = SimpleDateFormat("E-dd", Locale.getDefault())
        val calendar: Calendar = Calendar.getInstance()
        calendar.firstDayOfWeek = Calendar.MONDAY
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        val days = ArrayList<String>()
        for (i in 0..6) {
            days.add(format.format(calendar.time))
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        "days".printLog(days)
        return days
    }

    override fun onResume() {
        super.onResume()
        running = true
        // Returns the number of steps taken by the user since the last reboot while activated
        // This sensor requires permission android.permission.ACTIVITY_RECOGNITION.
        // So don't forget to add the following permission in AndroidManifest.xml present in manifest folder of the app.
        val stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)

        val hasIt =
            (requireActivity().packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_DETECTOR))
        if (hasIt) {
            "Step counter has started.!".showToastLong(requireContext())
        } else "It doesn't have nay!".showToastLong(requireContext())

        if (stepSensor == null) {
            // This will give a toast message to the user if there is no sensor in the device
            "No sensor detected on this device".showToastLong(requireContext())
        } else {
            "AddedZ".printLog("cvzcvz")
            // Rate suitable for the user interface
            sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_FASTEST)
        }
        val currentPosition = getDaysOfWeek().indexOf(getCurrentData())
        "currentPosition".printLog(currentPosition)
        binding.recyclerView.smoothScrollToPosition(currentPosition)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        "running".printLog("true")
        if (running) {
            totalSteps += event!!.values[0]
            "totalSteps".printLog(totalSteps)
            // Current steps are calculated by taking the difference of total steps
            // and previous steps
            val currentSteps = totalSteps.toInt() //- previousTotalSteps.toInt()

            // It will show the current steps to the user
            binding.counter.text = "$totalSteps"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    fun isSelectable(position: Int): Boolean {
        return position <= getDaysOfWeek().indexOf(getCurrentData())
    }

    fun selectDay(dateString: String, position: Int) {
        if (selectedDayPosition == currentDayPosition) saveChanges()
        getAllDaysStepCount { days ->
            var found = false
            days.forEach { day ->
                day.date?.let { date ->
                    val parsedDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).parse(date)
                    val comparableDateString =
                        SimpleDateFormat("E-dd", Locale.getDefault()).format(parsedDate ?: Date())
                    if (comparableDateString == dateString) {
                        found = true
                        setupStepCount(days, date)
                    }
                }
            }
            if (!found) {
                binding.stepCount.text = "N/A"
                binding.caloriesCount.text = "N/A"
                binding.bpmCount.text = "N/A"
            }
            selectedDayPosition = position
            setupRecyclerView(selectedDayPosition)
        }
    }

}