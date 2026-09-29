package com.example.data.model

enum class AppLanguage(val code: String, val displayName: String, val nativeName: String, val flag: String) {
    EN("en", "English", "English", "🇬🇧"),
    UZ("uz", "Uzbek", "O'zbekcha", "🇺🇿"),
    RU("ru", "Russian", "Русский", "🇷🇺"),
    AR("ar", "Arabic", "العربية", "🇸🇦")
}

data class WordItem(
    val id: String,
    val word: String,
    val phoneticIpa: String,
    val kidPronunciation: String,
    val syllables: String,
    val partOfSpeech: String,
    val kidDefinition: String,
    val exampleSentence: String,
    val category: String,
    val level: String,
    val translations: Map<String, String>,
    val difficulty: Int = 1
) {
    fun getTranslation(language: AppLanguage): String {
        return translations[language.code] ?: translations["en"] ?: word
    }
}

data class GrammarExercise(
    val id: String,
    val questionText: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class GrammarTopic(
    val id: String,
    val title: String,
    val description: String,
    val kidRule: String,
    val formula: String,
    val examples: List<Pair<String, String>>,
    val exercises: List<GrammarExercise>
)

data class QuizQuestion(
    val id: String,
    val question: String,
    val audioWord: String? = null,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val category: String = "General"
)

data class BookChapter(
    val chapterNumber: Int,
    val title: String,
    val content: String,
    val comprehensionQuestion: String,
    val choices: List<String>,
    val correctChoice: Int
)

data class HistoricalFigureBook(
    val id: String,
    val name: String,
    val epithet: String,
    val era: String,
    val country: String,
    val coverSummary: String,
    val famousQuote: String,
    val chapters: List<BookChapter>,
    val funFacts: List<String>,
    val vocabularyWords: List<String>
)
