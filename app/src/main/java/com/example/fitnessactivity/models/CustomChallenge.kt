package com.example.fitnessactivity.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "challenges")
data class CustomChallenge(
    @PrimaryKey
    val id: String,
    val title: String,
    val exercisesListString: String,
    val isComplete: Boolean
) : Serializable {
}