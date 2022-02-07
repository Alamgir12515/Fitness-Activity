package com.example.fitnessactivity.roomDb

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "localPersistence.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideFavoritesDao(database: AppDatabase): ExerciseDao {
        return database.exerciseDao()
    }

    @Provides
    fun provideCartDao(database: AppDatabase): ChallengeDao {
        return database.challengeDao()
    }
}