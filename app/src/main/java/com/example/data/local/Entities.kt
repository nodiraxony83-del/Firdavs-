package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 1,
    val name: String = "Young Learner",
    val email: String? = null,
    val isGoogleAccount: Boolean = false,
    val avatarId: String = "avatar_fox",
    val languageCode: String = "en",
    val speechVolume: Float = 1.0f,
    val speechRate: Float = 0.95f,
    val soundEffects: Boolean = true,
    val totalWordsExplored: Int = 0,
    val totalSpeakingPractices: Int = 0,
    val totalStars: Int = 0,
    val totalPoints: Int = 150,
    val grammarExercisesCompleted: Int = 0,
    val quizzesCompleted: Int = 0,
    val historicalChaptersRead: Int = 0
)

@Entity(tableName = "learned_words")
data class LearnedWordEntity(
    @PrimaryKey val wordId: String,
    val word: String,
    val isFavorite: Boolean = false,
    val timesPracticed: Int = 0,
    val bestScore: Int = 0,
    val lastPracticed: Long = System.currentTimeMillis()
)

@Entity(tableName = "test_results")
data class TestResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val testTitle: String,
    val category: String,
    val correctCount: Int,
    val totalQuestions: Int,
    val scorePercentage: Int,
    val starsEarned: Int,
    val completedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "book_progress")
data class BookProgressEntity(
    @PrimaryKey val bookId: String,
    val completedChapters: Int = 0,
    val isFinished: Boolean = false,
    val lastReadAt: Long = System.currentTimeMillis()
)
