package com.example.fitnessactivity.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenges")
data class CustomChallenge(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val exercisesListString: String
) {
}