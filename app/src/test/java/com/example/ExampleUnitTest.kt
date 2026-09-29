package com.example

import com.example.data.dictionary.BooksData
import com.example.data.dictionary.GrammarData
import com.example.data.dictionary.QuizData
import com.example.data.dictionary.WordsRepository
import com.example.data.model.AppLanguage
import com.example.data.model.LearningLevelCalculator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun testVocabularyWordLookup() {
        val wordItem = WordsRepository.lookupWord("Vocabulary")
        assertNotNull(wordItem)
        assertEquals("Vocabulary", wordItem.word)
        assertEquals("vo · cab · u · lar · y", wordItem.syllables)
        assertEquals("/vəˈkæb.jə.lər.i/", wordItem.phoneticIpa)
        assertEquals("Lug'at boyligi", wordItem.getTranslation(AppLanguage.UZ))
        assertEquals("Словарный запас", wordItem.getTranslation(AppLanguage.RU))
        assertEquals("المفردات اللغوية", wordItem.getTranslation(AppLanguage.AR))
    }

    @Test
    fun testWordSearchAcross60KCorpus() {
        val results = WordsRepository.searchWords("Algorithm")
        assertTrue("Search should return Algorithm", results.any { it.word.equals("Algorithm", ignoreCase = true) })
    }

    @Test
    fun testDynamicWordItemGeneration() {
        val dynamicWord = WordsRepository.lookupWord("Astronaut")
        assertNotNull(dynamicWord)
        assertTrue(dynamicWord.syllables.isNotEmpty())
        assertTrue(dynamicWord.phoneticIpa.isNotEmpty())
    }

    @Test
    fun testHistoricalFigureBooks() {
        val books = BooksData.historicalBooks
        assertTrue("Historical books collection should not be empty", books.isNotEmpty())
        val ibnSina = books.firstOrNull { it.id == "book_ibn_sina" }
        assertNotNull(ibnSina)
        assertTrue("Ibn Sina book should have chapters", ibnSina!!.chapters.isNotEmpty())
        assertEquals("Near Bukhara in Central Asia", ibnSina.chapters[0].choices[ibnSina.chapters[0].correctChoice])
    }

    @Test
    fun testGrammarCurriculum() {
        val topics = GrammarData.topics
        assertTrue("Grammar curriculum should have lessons", topics.isNotEmpty())
        val firstTopic = topics.first()
        assertTrue("Topic should have exercises", firstTopic.exercises.isNotEmpty())
    }

    @Test
    fun testQuizSuites() {
        val suites = QuizData.testSuites
        assertTrue("Test suites should be available", suites.isNotEmpty())
        suites.forEach { suite ->
            assertTrue("Suite ${suite.title} should have questions", suite.questions.isNotEmpty())
        }
    }

    @Test
    fun testLearningLevelCalculator() {
        val level1 = LearningLevelCalculator.calculate(45)
        assertEquals(1, level1.levelNumber)
        assertEquals("Sprout Explorer", level1.title)

        val level2 = LearningLevelCalculator.calculate(150)
        assertEquals(2, level2.levelNumber)
        assertEquals("Word Discoverer", level2.title)

        val level3 = LearningLevelCalculator.calculate(300)
        assertEquals(3, level3.levelNumber)
        assertEquals("Grammar Cadet", level3.title)

        val level6 = LearningLevelCalculator.calculate(1100)
        assertEquals(6, level6.levelNumber)
        assertEquals("History Scholar", level6.title)

        assertTrue(level2.progressPercent in 0f..1f)
        assertTrue(level2.pointsNeededForNext > 0)
    }

    @Test
    fun testScreenNavigationItems() {
        val items = com.example.ui.navigation.Screen.bottomNavItems
        assertEquals(7, items.size)
        items.forEach { screen ->
            assertNotNull("Screen in bottomNavItems must not be null", screen)
            assertTrue("testTag must not be blank", screen.testTag.isNotBlank())
            assertTrue("title must not be blank", screen.title.isNotBlank())
        }
    }

    @Test
    fun testVisualMilestoneBadges() {
        val level = LearningLevelCalculator.calculate(150)
        assertEquals(8, level.badges.size)
        
        // At 150 points:
        // Badge 1 (0 pts) -> Unlocked
        // Badge 2 (100 pts) -> Unlocked
        // Badge 3 (250 pts) -> Locked
        val badge1 = level.badges.find { it.targetLevel == 1 }
        assertNotNull(badge1)
        assertTrue("Level 1 badge should be unlocked", badge1!!.isUnlocked)

        val badge2 = level.badges.find { it.targetLevel == 2 }
        assertNotNull(badge2)
        assertTrue("Level 2 badge should be unlocked at 150 XP", badge2!!.isUnlocked)

        val badge3 = level.badges.find { it.targetLevel == 3 }
        assertNotNull(badge3)
        assertTrue("Level 3 badge should be locked at 150 XP", !badge3!!.isUnlocked)
        assertTrue("Progress should be between 0 and 1", badge3.progress in 0f..1f)
    }
}
