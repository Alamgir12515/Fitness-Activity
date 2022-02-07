package com.example.fitnessactivity.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "exercises")
data class Exercise constructor(
    val name: String,
    val description: String,
    val image: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
) : Serializable {
    constructor() : this("unknown", "", "") {

    }
}