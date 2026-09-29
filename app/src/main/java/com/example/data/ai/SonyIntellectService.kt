package com.example.data.ai

import com.example.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

data class SonyMessage(
    val id: String,
    val text: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis(),
    val pronunciationWord: String? = null
)

class SonyIntellectService {

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val systemPrompt = """
        You are 'Sony Intellect', a joyful, encouraging, and super-intelligent AI learning companion and tutor for children in the Fdego educational app.
        Your mission is to help children learn new words, improve their voice pronunciation, explain grammar simply, inspire curiosity with historical heroes, and make learning fun.
        Guidelines:
        1. Always be warm, friendly, clear, and age-appropriate (for children ages 5 to 14).
        2. When explaining a word:
           - Give the simple kid-friendly definition.
           - Show how to pronounce it with syllables and phonetic phonetic hints (e.g., 'vo-CAB-yoo-ler-ee').
           - Give an exciting example sentence.
           - Offer translations if asked in Uzbek, Russian, Arabic, or English.
        3. Keep answers cheerful and concise (2-4 short paragraphs maximum).
        4. Use emojis like 🌟, 📚, 🗣️, 🚀, 💡 to make reading fun.
    """.trimIndent()

    suspend fun askSonyIntellect(userQuery: String): String = withContext(Dispatchers.IO) {
        val apiKey = try {
            BuildConfig.GEMINI_API_KEY
        } catch (_: Throwable) {
            ""
        }

        if (apiKey.isBlank() || apiKey == "MY_GEMINI_API_KEY") {
            // Smart built-in kid educator responses
            return@withContext generateOfflineKnowledgeResponse(userQuery)
        }

        try {
            val url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey"

            val jsonBody = JSONObject().apply {
                val contents = JSONArray().apply {
                    put(JSONObject().apply {
                        put("role", "user")
                        put("parts", JSONArray().apply {
                            put(JSONObject().put("text", "$systemPrompt\n\nChild's question: $userQuery"))
                        })
                    })
                }
                put("contents", contents)

                put("generationConfig", JSONObject().apply {
                    put("temperature", 0.7)
                    put("maxOutputTokens", 600)
                })
            }

            val request = Request.Builder()
                .url(url)
                .post(jsonBody.toString().toRequestBody("application/json".toMediaType()))
                .build()

            val response = client.newCall(request).execute()
            val responseBody = response.body?.string()

            if (response.isSuccessful && !responseBody.isNullOrEmpty()) {
                val json = JSONObject(responseBody)
                val candidates = json.optJSONArray("candidates")
                if (candidates != null && candidates.length() > 0) {
                    val candidate = candidates.getJSONObject(0)
                    val content = candidate.optJSONObject("content")
                    val parts = content?.optJSONArray("parts")
                    val text = parts?.optJSONObject(0)?.optString("text")
                    if (!text.isNullOrBlank()) {
                        return@withContext text
                    }
                }
            }

            // Fallback to rich built-in response on network/quota error
            generateOfflineKnowledgeResponse(userQuery)
        } catch (e: Exception) {
            generateOfflineKnowledgeResponse(userQuery)
        }
    }

    private fun generateOfflineKnowledgeResponse(query: String): String {
        val q = query.lowercase().trim()

        return when {
            q.contains("pronounce") || q.contains("pronunciation") || q.contains("say") -> {
                val word = extractTargetWord(q)
                "🗣️ **How to Pronounce '$word'**:\n\n" +
                        "• Syllables: **${word.chunked(3).joinToString(" · ")}**\n" +
                        "• Voice guide: Say it slowly, then tap the speaker icon to hear me speak it aloud!\n" +
                        "• Remember: Take a deep breath and smile while pronouncing every letter clearly! 🌟"
            }
            q.contains("vocabulary") -> {
                "📚 **Vocabulary** /vəˈkæb.jə.lər.i/ (voh-KAB-yuh-lair-ee):\n\n" +
                        "Vocabulary is the treasure chest of all the words you know and use to speak, read, and write! 🗝️\n\n" +
                        "• *Example*: 'Every new story you read adds sparkling words to your vocabulary!'\n" +
                        "• *In Uzbek*: Lug'at boyligi 🇺🇿\n" +
                        "• *In Russian*: Словарный запас 🇷🇺\n" +
                        "• *In Arabic*: المفردات اللغوية 🇸🇦"
            }
            q.contains("story") || q.contains("tale") -> {
                "✨ **A Mini Adventure for You**:\n\n" +
                        "Once upon a starry night, a curious little owl named Fdego found a glowing crystal book in the ancient library. When he whispered 'Adventure', the book floated into the sky and showed him galaxies, dolphin oceans, and the wondrous inventions of Ibn Sina and Edison! From that day on, every word he spoke sparkled with magic. 🦉🌌"
            }
            q.contains("grammar") || q.contains("rule") || q.contains("plural") -> {
                "💡 **Sony's Grammar Secret**:\n\n" +
                        "Words love having partners! When we talk about one thing, we say 'One cat'. When there are two or more, we add a friendly '-s': 'Two cats'! 🐱🐱\n\n" +
                        "Try practicing in our Grammar section to earn gold stars!"
            }
            q.contains("who is") || q.contains("ibn sina") || q.contains("avicenna") -> {
                "🌟 **Ibn Sina (Avicenna)**:\n\n" +
                        "He was a phenomenal young scholar born near Bukhara in Central Asia! By age 10 he had read entire libraries. He wrote 'The Canon of Medicine', a masterwork that helped doctors around the world heal people for hundreds of years! 🩺📖"
            }
            q.contains("al-khwarizmi") || q.contains("algorithm") -> {
                "⚡ **Muhammad Al-Khwarizmi**:\n\n" +
                        "He was a genius mathematician from Khwarazm. He invented Algebra and gave us 'Algorithms'—the step-by-step instructions that run all computer games, apps, and rockets today! 🧮💻"
            }
            else -> {
                "🌟 **Hello bright learner! I am Sony Intellect!**\n\n" +
                        "You asked: \"$query\"\n\n" +
                        "That is a wonderful question! Learning new words and speaking clearly helps your brain grow stronger every day. Would you like me to:\n" +
                        "1. Break down the pronunciation of a word? 🗣️\n" +
                        "2. Explain a grammar rule simply? 📖\n" +
                        "3. Tell an inspiring story about a historical hero? 🏰"
            }
        }
    }

    private fun extractTargetWord(query: String): String {
        val words = query.split(" ")
        val keywords = setOf("how", "do", "you", "pronounce", "say", "the", "word", "is", "what")
        val candidate = words.lastOrNull { it.lowercase() !in keywords && it.length > 2 }
        return candidate?.replace("?", "")?.replace("\"", "")?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(java.util.Locale.ROOT) else it.toString() } ?: "Vocabulary"
    }
}
