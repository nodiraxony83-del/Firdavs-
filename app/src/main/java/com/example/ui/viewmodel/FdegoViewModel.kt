package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.SpeechPracticeManager
import com.example.audio.SpeechPracticeState
import com.example.audio.TtsManager
import com.example.data.ai.SonyIntellectService
import com.example.data.ai.SonyMessage
import com.example.data.dictionary.BooksData
import com.example.data.dictionary.GrammarData
import com.example.data.dictionary.QuizData
import com.example.data.dictionary.WordsRepository
import com.example.data.local.AppDatabase
import com.example.data.local.FdegoRepository
import com.example.data.local.LearnedWordEntity
import com.example.data.local.TestResultEntity
import com.example.data.local.UserProfileEntity
import com.example.data.model.AppLanguage
import com.example.data.model.BookChapter
import com.example.data.model.GrammarTopic
import com.example.data.model.HistoricalFigureBook
import com.example.data.model.LearningLevel
import com.example.data.model.LearningLevelCalculator
import com.example.data.model.WordItem
import com.example.ui.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

class FdegoViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getDatabase(application)
    private val repository = FdegoRepository(database.fdegoDao())
    val ttsManager = TtsManager(application)
    val speechManager = SpeechPracticeManager(application)
    private val sonyService = SonyIntellectService()

    // Navigation
    private val _currentScreen = MutableStateFlow<Screen>(Screen.Words)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
    }

    // Settings & User Profile
    val userProfile: StateFlow<UserProfileEntity?> = repository.userProfile
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val learningLevel: StateFlow<LearningLevel> = repository.userProfile
        .map { profile ->
            LearningLevelCalculator.calculate(profile?.totalPoints ?: 150)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            LearningLevelCalculator.calculate(150)
        )

    private val _rewardNotification = MutableStateFlow<String?>(null)
    val rewardNotification: StateFlow<String?> = _rewardNotification.asStateFlow()

    fun dismissRewardNotification() {
        _rewardNotification.value = null
    }

    fun triggerReward(message: String) {
        _rewardNotification.value = message
        viewModelScope.launch {
            delay(3500)
            if (_rewardNotification.value == message) {
                _rewardNotification.value = null
            }
        }
    }

    private val _selectedLanguage = MutableStateFlow(AppLanguage.EN)
    val selectedLanguage: StateFlow<AppLanguage> = _selectedLanguage.asStateFlow()

    private val _speechVolume = MutableStateFlow(1.0f)
    val speechVolume: StateFlow<Float> = _speechVolume.asStateFlow()

    private val _speechRate = MutableStateFlow(0.95f)
    val speechRate: StateFlow<Float> = _speechRate.asStateFlow()

    private val _soundEffects = MutableStateFlow(true)
    val soundEffects: StateFlow<Boolean> = _soundEffects.asStateFlow()

    // Words Section
    private val _wordSearchQuery = MutableStateFlow("")
    val wordSearchQuery: StateFlow<String> = _wordSearchQuery.asStateFlow()

    private val _selectedWordCategory = MutableStateFlow("All Words (60,000)")
    val selectedWordCategory: StateFlow<String> = _selectedWordCategory.asStateFlow()

    private val _displayedWords = MutableStateFlow(WordsRepository.searchWords("", "All Words (60,000)"))
    val displayedWords: StateFlow<List<WordItem>> = _displayedWords.asStateFlow()

    private val _activeWordDetail = MutableStateFlow<WordItem?>(WordsRepository.curatedWords.first())
    val activeWordDetail: StateFlow<WordItem?> = _activeWordDetail.asStateFlow()

    val favoriteWords: StateFlow<List<LearnedWordEntity>> = repository.favoriteWords
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val learnedWords: StateFlow<List<LearnedWordEntity>> = repository.learnedWords
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Speaking Section
    private val _speakingTargetWord = MutableStateFlow(WordsRepository.curatedWords[0])
    val speakingTargetWord: StateFlow<WordItem> = _speakingTargetWord.asStateFlow()

    val speechPracticeState: StateFlow<SpeechPracticeState> = speechManager.state

    // Grammar Section
    val grammarTopics: List<GrammarTopic> = GrammarData.topics
    private val _activeGrammarTopic = MutableStateFlow(grammarTopics.first())
    val activeGrammarTopic: StateFlow<GrammarTopic> = _activeGrammarTopic.asStateFlow()

    private val _currentGrammarExerciseIndex = MutableStateFlow(0)
    val currentGrammarExerciseIndex: StateFlow<Int> = _currentGrammarExerciseIndex.asStateFlow()

    private val _selectedGrammarOption = MutableStateFlow<Int?>(null)
    val selectedGrammarOption: StateFlow<Int?> = _selectedGrammarOption.asStateFlow()

    private val _isGrammarAnswerChecked = MutableStateFlow(false)
    val isGrammarAnswerChecked: StateFlow<Boolean> = _isGrammarAnswerChecked.asStateFlow()

    // Tests Section
    val testSuites = QuizData.testSuites
    private val _activeTestSuite = MutableStateFlow(testSuites.first())
    val activeTestSuite: StateFlow<QuizData.TestSuite> = _activeTestSuite.asStateFlow()

    private val _isTestRunning = MutableStateFlow(false)
    val isTestRunning: StateFlow<Boolean> = _isTestRunning.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _selectedTestChoice = MutableStateFlow<Int?>(null)
    val selectedTestChoice: StateFlow<Int?> = _selectedTestChoice.asStateFlow()

    private val _isChoiceSubmitted = MutableStateFlow(false)
    val isChoiceSubmitted: StateFlow<Boolean> = _isChoiceSubmitted.asStateFlow()

    private val _testScoreCount = MutableStateFlow(0)
    val testScoreCount: StateFlow<Int> = _testScoreCount.asStateFlow()

    private val _isTestCompleted = MutableStateFlow(false)
    val isTestCompleted: StateFlow<Boolean> = _isTestCompleted.asStateFlow()

    val testResultsHistory: StateFlow<List<TestResultEntity>> = repository.testResults
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Books Section
    val booksList = BooksData.historicalBooks
    private val _activeBook = MutableStateFlow<HistoricalFigureBook?>(null)
    val activeBook: StateFlow<HistoricalFigureBook?> = _activeBook.asStateFlow()

    private val _activeBookChapter = MutableStateFlow<BookChapter?>(null)
    val activeBookChapter: StateFlow<BookChapter?> = _activeBookChapter.asStateFlow()

    private val _bookQuizChoice = MutableStateFlow<Int?>(null)
    val bookQuizChoice: StateFlow<Int?> = _bookQuizChoice.asStateFlow()

    private val _isBookQuizChecked = MutableStateFlow(false)
    val isBookQuizChecked: StateFlow<Boolean> = _isBookQuizChecked.asStateFlow()

    // Sony Intellect AI Section
    private val _sonyChatMessages = MutableStateFlow<List<SonyMessage>>(
        listOf(
            SonyMessage(
                id = UUID.randomUUID().toString(),
                text = "Hello young explorer! 👋 I am **Sony Intellect**, your AI guide! You can ask me how to pronounce any word, ask for kid-friendly definitions, request exciting stories, or practice grammar. What would you like to discover today?",
                isUser = false,
                pronunciationWord = "Vocabulary"
            )
        )
    )
    val sonyChatMessages: StateFlow<List<SonyMessage>> = _sonyChatMessages.asStateFlow()

    private val _isSonyThinking = MutableStateFlow(false)
    val isSonyThinking: StateFlow<Boolean> = _isSonyThinking.asStateFlow()

    init {
        viewModelScope.launch {
            userProfile.collect { profile ->
                if (profile != null) {
                    _speechVolume.value = profile.speechVolume
                    _speechRate.value = profile.speechRate
                    _soundEffects.value = profile.soundEffects
                    val lang = AppLanguage.entries.firstOrNull { it.code == profile.languageCode }
                    if (lang != null) _selectedLanguage.value = lang
                } else {
                    repository.saveProfile(UserProfileEntity())
                }
            }
        }
    }

    // Audio & Pronunciation
    fun pronounceWord(word: String, slowMode: Boolean = false) {
        ttsManager.speak(word, _speechRate.value, _speechVolume.value, slowMode)
    }

    fun stopSpeaking() {
        ttsManager.stop()
    }

    // Words Operations
    fun onSearchQueryChanged(query: String) {
        _wordSearchQuery.value = query
        _displayedWords.value = WordsRepository.searchWords(query, _selectedWordCategory.value)
    }

    fun onCategorySelected(category: String) {
        _selectedWordCategory.value = category
        _displayedWords.value = WordsRepository.searchWords(_wordSearchQuery.value, category)
    }

    fun selectWordDetail(word: WordItem) {
        _activeWordDetail.value = word
        pronounceWord(word.word)
    }

    fun toggleFavorite(word: WordItem, isFav: Boolean) {
        viewModelScope.launch {
            repository.toggleFavorite(word.id, word.word, isFav)
        }
    }

    // Speaking Practice Operations
    fun setSpeakingTarget(word: WordItem) {
        _speakingTargetWord.value = word
        _currentScreen.value = Screen.Speaking
        pronounceWord(word.word)
    }

    fun startListeningForTarget() {
        speechManager.startListening(_speakingTargetWord.value.word)
    }

    fun stopListening() {
        speechManager.stopListening()
    }

    fun recordSpeakingScore(score: Int) {
        viewModelScope.launch {
            val target = _speakingTargetWord.value
            repository.recordWordPractice(target.id, target.word, score)
            val pts = if (score >= 90) 20 else if (score >= 70) 15 else 10
            triggerReward("🗣️ +$pts XP! Pronunciation Practice!")
        }
    }

    // Grammar Operations
    fun selectGrammarTopic(topic: GrammarTopic) {
        _activeGrammarTopic.value = topic
        _currentGrammarExerciseIndex.value = 0
        _selectedGrammarOption.value = null
        _isGrammarAnswerChecked.value = false
    }

    fun selectGrammarOption(index: Int) {
        if (!_isGrammarAnswerChecked.value) {
            _selectedGrammarOption.value = index
        }
    }

    fun checkGrammarAnswer() {
        if (_isGrammarAnswerChecked.value) return
        _isGrammarAnswerChecked.value = true
        val currentEx = _activeGrammarTopic.value.exercises.getOrNull(_currentGrammarExerciseIndex.value)
        if (currentEx != null && _selectedGrammarOption.value == currentEx.correctIndex) {
            viewModelScope.launch {
                repository.recordGrammarCompletion(15)
                triggerReward("🌟 +15 XP! Grammar Exercise Mastered!")
            }
        }
    }

    fun nextGrammarExercise() {
        val topic = _activeGrammarTopic.value
        if (_currentGrammarExerciseIndex.value < topic.exercises.size - 1) {
            _currentGrammarExerciseIndex.value += 1
            _selectedGrammarOption.value = null
            _isGrammarAnswerChecked.value = false
        }
    }

    // Tests Operations
    fun startTest(suite: QuizData.TestSuite) {
        _activeTestSuite.value = suite
        _isTestRunning.value = true
        _currentQuestionIndex.value = 0
        _selectedTestChoice.value = null
        _isChoiceSubmitted.value = false
        _testScoreCount.value = 0
        _isTestCompleted.value = false
    }

    fun selectTestChoice(index: Int) {
        if (!_isChoiceSubmitted.value) {
            _selectedTestChoice.value = index
        }
    }

    fun submitTestChoice() {
        if (_isChoiceSubmitted.value) return
        val currentQ = _activeTestSuite.value.questions[_currentQuestionIndex.value]
        _isChoiceSubmitted.value = true
        if (_selectedTestChoice.value == currentQ.correctIndex) {
            _testScoreCount.value += 1
        }
    }

    fun nextTestQuestion() {
        val suite = _activeTestSuite.value
        if (_currentQuestionIndex.value < suite.questions.size - 1) {
            _currentQuestionIndex.value += 1
            _selectedTestChoice.value = null
            _isChoiceSubmitted.value = false
        } else {
            // Test finished
            _isTestCompleted.value = true
            val total = suite.questions.size
            val correct = _testScoreCount.value
            val pct = if (total > 0) (correct * 100) / total else 0
            val stars = when {
                pct >= 85 -> 3
                pct >= 60 -> 2
                else -> 1
            }
            val pointsEarned = (correct * 10) + (stars * 5)
            viewModelScope.launch {
                repository.saveTestResult(suite.title, suite.category, correct, total, stars)
                triggerReward("🏆 +$pointsEarned XP! Test Completed with $stars Stars!")
            }
        }
    }

    fun exitTest() {
        _isTestRunning.value = false
        _isTestCompleted.value = false
    }

    // Books Operations
    fun openBook(book: HistoricalFigureBook) {
        _activeBook.value = book
        _activeBookChapter.value = book.chapters.firstOrNull()
        _bookQuizChoice.value = null
        _isBookQuizChecked.value = false
    }

    fun selectBookChapter(chapter: BookChapter) {
        _activeBookChapter.value = chapter
        _bookQuizChoice.value = null
        _isBookQuizChecked.value = false
    }

    fun closeBook() {
        _activeBook.value = null
        _activeBookChapter.value = null
        stopSpeaking()
    }

    fun selectBookQuizChoice(index: Int) {
        _bookQuizChoice.value = index
    }

    fun checkBookQuiz() {
        if (_isBookQuizChecked.value) return
        _isBookQuizChecked.value = true
        val chapter = _activeBookChapter.value
        val book = _activeBook.value
        if (chapter != null && book != null && _bookQuizChoice.value == chapter.correctChoice) {
            viewModelScope.launch {
                repository.recordHistoricalChapterRead(book.id, chapter.chapterNumber, 30)
                triggerReward("📜 +30 XP! Historical Reading Comprehension!")
            }
        }
    }

    // Sony Intellect Chat
    fun askSonyIntellect(prompt: String) {
        if (prompt.isBlank() || _isSonyThinking.value) return

        val userMsg = SonyMessage(
            id = UUID.randomUUID().toString(),
            text = prompt,
            isUser = true
        )
        _sonyChatMessages.value = _sonyChatMessages.value + userMsg
        _isSonyThinking.value = true

        viewModelScope.launch {
            val responseText = sonyService.askSonyIntellect(prompt)
            val aiMsg = SonyMessage(
                id = UUID.randomUUID().toString(),
                text = responseText,
                isUser = false
            )
            _sonyChatMessages.value = _sonyChatMessages.value + aiMsg
            _isSonyThinking.value = false
        }
    }

    // Settings Operations
    fun setLanguage(language: AppLanguage) {
        _selectedLanguage.value = language
        viewModelScope.launch {
            val current = userProfile.value ?: UserProfileEntity()
            repository.saveProfile(current.copy(languageCode = language.code))
        }
    }

    fun setSpeechVolume(vol: Float) {
        _speechVolume.value = vol
        viewModelScope.launch {
            val current = userProfile.value ?: UserProfileEntity()
            repository.saveProfile(current.copy(speechVolume = vol))
        }
    }

    fun setSpeechRate(rate: Float) {
        _speechRate.value = rate
        viewModelScope.launch {
            val current = userProfile.value ?: UserProfileEntity()
            repository.saveProfile(current.copy(speechRate = rate))
        }
    }

    fun setSoundEffects(enabled: Boolean) {
        _soundEffects.value = enabled
        viewModelScope.launch {
            val current = userProfile.value ?: UserProfileEntity()
            repository.saveProfile(current.copy(soundEffects = enabled))
        }
    }

    fun registerOrUpdateUser(name: String, isGoogle: Boolean, email: String? = null) {
        viewModelScope.launch {
            val current = userProfile.value ?: UserProfileEntity()
            repository.saveProfile(
                current.copy(
                    name = name.ifBlank { if (isGoogle) "Google Learner" else "Little Explorer" },
                    email = email,
                    isGoogleAccount = isGoogle
                )
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        ttsManager.shutdown()
        speechManager.destroy()
    }
}
