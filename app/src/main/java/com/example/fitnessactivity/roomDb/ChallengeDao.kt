package com.example.fitnessactivity.roomDb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fitnessactivity.models.CustomChallenge

@Dao
interface ChallengeDao {

    @Query("SELECT * FROM challenges")
    fun getAll(): LiveData<List<CustomChallenge>>

    @Query("SELECT EXISTS(SELECT * FROM challenges WHERE id = :id)")
    fun isAlreadyAdded(id: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(challenge: CustomChallenge): Long

    @Delete
    fun delete(challenge: CustomChallenge): Int

    @Query("DELETE FROM challenges")
    fun deleteAll()
}