package com.example.data.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class FdegoRepository(private val dao: FdegoDao) {

    val userProfile: Flow<UserProfileEntity?> = dao.getUserProfile()
    val learnedWords: Flow<List<LearnedWordEntity>> = dao.getAllLearnedWords()
    val favoriteWords: Flow<List<LearnedWordEntity>> = dao.getFavoriteWords()
    val testResults: Flow<List<TestResultEntity>> = dao.getAllTestResults()
    val bookProgress: Flow<List<BookProgressEntity>> = dao.getAllBookProgress()

    suspend fun saveProfile(profile: UserProfileEntity) {
        dao.saveUserProfile(profile)
    }

    suspend fun addPoints(amount: Int) {
        val current = userProfile.firstOrNull() ?: UserProfileEntity()
        val updated = current.copy(
            totalPoints = current.totalPoints + amount
        )
        dao.saveUserProfile(updated)
    }

    suspend fun recordGrammarCompletion(pointsAwarded: Int = 15) {
        val current = userProfile.firstOrNull() ?: UserProfileEntity()
        val updated = current.copy(
            totalPoints = current.totalPoints + pointsAwarded,
            grammarExercisesCompleted = current.grammarExercisesCompleted + 1
        )
        dao.saveUserProfile(updated)
    }

    suspend fun recordHistoricalChapterRead(bookId: String, chapter: Int, pointsAwarded: Int = 30) {
        val current = userProfile.firstOrNull() ?: UserProfileEntity()
        val updated = current.copy(
            totalPoints = current.totalPoints + pointsAwarded,
            historicalChaptersRead = current.historicalChaptersRead + 1
        )
        dao.saveUserProfile(updated)

        val progress = BookProgressEntity(
            bookId = bookId,
            completedChapters = chapter,
            isFinished = false,
            lastReadAt = System.currentTimeMillis()
        )
        dao.saveBookProgress(progress)
    }

    suspend fun recordWordPractice(wordId: String, wordText: String, score: Int) {
        val current = userProfile.firstOrNull() ?: UserProfileEntity()
        val pointsBonus = when {
            score >= 90 -> 20
            score >= 70 -> 15
            else -> 10
        }
        val starBonus = if (score >= 80) 1 else 0

        val updated = current.copy(
            totalPoints = current.totalPoints + pointsBonus,
            totalSpeakingPractices = current.totalSpeakingPractices + 1,
            totalStars = current.totalStars + starBonus
        )
        dao.saveUserProfile(updated)

        val learned = LearnedWordEntity(
            wordId = wordId,
            word = wordText,
            timesPracticed = 1,
            bestScore = score,
            lastPracticed = System.currentTimeMillis()
        )
        dao.upsertLearnedWord(learned)
    }

    suspend fun toggleFavorite(wordId: String, wordText: String, isFav: Boolean) {
        val learned = LearnedWordEntity(
            wordId = wordId,
            word = wordText,
            isFavorite = isFav,
            lastPracticed = System.currentTimeMillis()
        )
        dao.upsertLearnedWord(learned)
    }

    suspend fun saveTestResult(
        title: String,
        category: String,
        correct: Int,
        total: Int,
        stars: Int
    ): Long {
        val percentage = if (total > 0) (correct * 100) / total else 0
        val pointsAwarded = (correct * 10) + (stars * 5)

        val current = userProfile.firstOrNull() ?: UserProfileEntity()
        val updated = current.copy(
            totalPoints = current.totalPoints + pointsAwarded,
            totalStars = current.totalStars + stars,
            quizzesCompleted = current.quizzesCompleted + 1
        )
        dao.saveUserProfile(updated)

        val entity = TestResultEntity(
            testTitle = title,
            category = category,
            correctCount = correct,
            totalQuestions = total,
            scorePercentage = percentage,
            starsEarned = stars,
            completedAt = System.currentTimeMillis()
        )
        return dao.insertTestResult(entity)
    }
}
