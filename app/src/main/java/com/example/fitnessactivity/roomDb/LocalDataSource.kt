package com.example.fitnessactivity.roomDb

import android.os.Handler
import android.os.Looper
import com.example.fitnessactivity.models.CustomChallenge
import com.example.fitnessactivity.models.Exercise
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val exerciseDao: ExerciseDao,
    private val challengeDao: ChallengeDao,
) {
    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)
    private val mainThreadHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    fun exerciseExists(id: Int, callback: (Boolean) -> Unit) {
        executorService.execute {
            val isAlreadyAdded = exerciseDao.isAlreadyAdded(id)
            mainThreadHandler.post { callback(isAlreadyAdded) }
        }
    }

    fun challengeExists(id: Int, callback: (Boolean) -> Unit) {
        executorService.execute {
            val isAlreadyAdded = challengeDao.isAlreadyAdded(id)
            mainThreadHandler.post { callback(isAlreadyAdded) }
        }
    }

    fun addExercise(exercise: Exercise) {
        executorService.execute {
            exerciseDao.insert(exercise)
        }
    }

    fun removeExercise(exercise: Exercise) {
        executorService.execute {
            exerciseDao.delete(exercise)
        }
    }

    fun addChallenge(challenge: CustomChallenge) {
        executorService.execute {
            challengeDao.insert(challenge)
        }
    }

    fun update(isComplete: Boolean, id: String) {
        executorService.execute {
            challengeDao.update(isComplete, id)
        }
    }

    fun removeChallenge(challenge: CustomChallenge) {
        executorService.execute {
            challengeDao.delete(challenge)
        }
    }
}