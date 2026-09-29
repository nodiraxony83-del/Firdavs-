package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FdegoDao {

    @Query("SELECT * FROM user_profile WHERE id = 1")
    fun getUserProfile(): Flow<UserProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserProfile(profile: UserProfileEntity)

    @Query("SELECT * FROM learned_words ORDER BY lastPracticed DESC")
    fun getAllLearnedWords(): Flow<List<LearnedWordEntity>>

    @Query("SELECT * FROM learned_words WHERE isFavorite = 1")
    fun getFavoriteWords(): Flow<List<LearnedWordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertLearnedWord(word: LearnedWordEntity)

    @Query("SELECT * FROM test_results ORDER BY completedAt DESC")
    fun getAllTestResults(): Flow<List<TestResultEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTestResult(result: TestResultEntity): Long

    @Query("SELECT * FROM book_progress")
    fun getAllBookProgress(): Flow<List<BookProgressEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBookProgress(progress: BookProgressEntity)
}
