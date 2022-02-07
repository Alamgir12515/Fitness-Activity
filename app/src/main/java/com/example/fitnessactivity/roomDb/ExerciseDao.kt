package com.example.fitnessactivity.roomDb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fitnessactivity.models.Exercise

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercises")
    fun getAll(): LiveData<List<Exercise>>

    @Query("SELECT EXISTS(SELECT * FROM exercises WHERE id = :id)")
    fun isAlreadyAdded(id: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exercise: Exercise): Long

    @Delete
    fun delete(exercise: Exercise): Int

    @Query("DELETE FROM exercises")
    fun deleteAll()
}
