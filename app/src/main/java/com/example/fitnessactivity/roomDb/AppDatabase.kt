package com.example.fitnessactivity.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fitnessactivity.models.CustomChallenge
import com.example.fitnessactivity.models.Exercise

/**
 * SQLite Database for storing the logs.
 */
@Database(
    entities = [Exercise::class, CustomChallenge::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun challengeDao(): ChallengeDao
}
