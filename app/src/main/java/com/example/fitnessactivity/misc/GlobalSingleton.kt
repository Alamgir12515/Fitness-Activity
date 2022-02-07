package com.example.fitnessactivity.misc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fitnessactivity.models.Exercise
import com.example.fitnessactivity.models.User

object GlobalSingleton {
    val userLiveData = MutableLiveData<User?>()
    val childData: HashMap<String, ArrayList<Exercise>> = HashMap()

    fun getCurrentUserLiveData(): LiveData<User?> {
        return userLiveData
    }

    fun calculateBmi(weight: Float?, height: Float?): Float {
        if (weight == null || height == null) return 0f
        return (weight / ((height * height) / 10000))
    }
}