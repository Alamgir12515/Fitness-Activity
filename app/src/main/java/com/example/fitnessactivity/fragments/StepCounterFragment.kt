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
import com.example.fitnessactivity.adapters.DaysRVAdapter
import com.example.fitnessactivity.databinding.FragmentStepCounterBinding
import com.example.fitnessactivity.getCurrentData
import com.example.fitnessactivity.printLog
import com.example.fitnessactivity.setWhiteStatusBarColor
import com.example.fitnessactivity.showToastLong
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class StepCounterFragment : Fragment(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var previousTotalSteps = 0f

    // Creating a variable which will give the running status
    // and initially given the boolean value as false
    private var running = false

    // Creating a variable which will counts total steps
    // and it has been given the value of 0 float
    private var totalSteps = 0f

    private var _binding: FragmentStepCounterBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Adding a context of SENSOR_SERVICE aas Sensor Manager
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStepCounterBinding.inflate(inflater, container, false)
        requireActivity().setWhiteStatusBarColor()
        binding.recyclerView.apply {
            adapter = DaysRVAdapter(this@StepCounterFragment, getDaysOfWeek())
        }
        return binding.root
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

}