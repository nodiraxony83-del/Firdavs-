package com.example.audio

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale
import kotlin.math.max

sealed interface SpeechPracticeState {
    data object Idle : SpeechPracticeState
    data object Listening : SpeechPracticeState
    data class Processing(val partialText: String = "") : SpeechPracticeState
    data class Result(
        val spokenText: String,
        val targetWord: String,
        val score: Int,
        val stars: Int,
        val feedback: String,
        val isAccurate: Boolean
    ) : SpeechPracticeState
    data class Error(val message: String) : SpeechPracticeState
}

class SpeechPracticeManager(private val context: Context) {

    private var speechRecognizer: SpeechRecognizer? = null
    private var currentTargetWord: String = ""

    private val _state = MutableStateFlow<SpeechPracticeState>(SpeechPracticeState.Idle)
    val state: StateFlow<SpeechPracticeState> = _state.asStateFlow()

    private val isRecognitionAvailable: Boolean by lazy {
        SpeechRecognizer.isRecognitionAvailable(context)
    }

    fun startListening(targetWord: String) {
        currentTargetWord = targetWord
        _state.value = SpeechPracticeState.Listening

        if (!isRecognitionAvailable) {
            // Emulate or guide user
            _state.value = SpeechPracticeState.Error("Speech Recognition is not available on this device. You can simulate speaking test below!")
            return
        }

        try {
            speechRecognizer?.destroy()
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context).apply {
                setRecognitionListener(createListener())
            }

            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US.toLanguageTag())
                putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3)
                putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
            }

            speechRecognizer?.startListening(intent)
        } catch (e: Exception) {
            _state.value = SpeechPracticeState.Error("Could not start microphone: ${e.localizedMessage}")
        }
    }

    fun stopListening() {
        try {
            speechRecognizer?.stopListening()
        } catch (_: Exception) {}
    }

    fun cancel() {
        try {
            speechRecognizer?.cancel()
            _state.value = SpeechPracticeState.Idle
        } catch (_: Exception) {}
    }

    fun simulatePractice(targetWord: String, simulatedSpoken: String) {
        val (score, stars, feedback, isAccurate) = evaluatePronunciation(targetWord, simulatedSpoken)
        _state.value = SpeechPracticeState.Result(
            spokenText = simulatedSpoken,
            targetWord = targetWord,
            score = score,
            stars = stars,
            feedback = feedback,
            isAccurate = isAccurate
        )
    }

    private fun createListener(): RecognitionListener {
        return object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
                _state.value = SpeechPracticeState.Listening
            }

            override fun onBeginningOfSpeech() {
                _state.value = SpeechPracticeState.Processing()
            }

            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {
                _state.value = SpeechPracticeState.Processing("Analyzing pronunciation...")
            }

            override fun onError(error: Int) {
                val errorMsg = when (error) {
                    SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
                    SpeechRecognizer.ERROR_CLIENT -> "Client error"
                    SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Microphone permission required"
                    SpeechRecognizer.ERROR_NETWORK -> "Network error during recognition"
                    SpeechRecognizer.ERROR_NO_MATCH -> "Didn't catch that. Please speak clearly into the microphone!"
                    SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech heard. Try again!"
                    else -> "Speech recognition error ($error)"
                }
                _state.value = SpeechPracticeState.Error(errorMsg)
            }

            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                val spoken = matches?.firstOrNull()?.trim() ?: ""

                if (spoken.isEmpty()) {
                    _state.value = SpeechPracticeState.Error("No words detected. Try again!")
                    return
                }

                val (score, stars, feedback, isAccurate) = evaluatePronunciation(currentTargetWord, spoken)
                _state.value = SpeechPracticeState.Result(
                    spokenText = spoken,
                    targetWord = currentTargetWord,
                    score = score,
                    stars = stars,
                    feedback = feedback,
                    isAccurate = isAccurate
                )
            }

            override fun onPartialResults(partialResults: Bundle?) {
                val matches = partialResults?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                val partial = matches?.firstOrNull() ?: ""
                if (partial.isNotEmpty()) {
                    _state.value = SpeechPracticeState.Processing(partial)
                }
            }

            override fun onEvent(eventType: Int, params: Bundle?) {}
        }
    }

    private fun evaluatePronunciation(
        target: String,
        spoken: String
    ): EvaluatedResult {
        val cleanTarget = target.lowercase().trim()
        val cleanSpoken = spoken.lowercase().trim()

        if (cleanTarget == cleanSpoken) {
            return EvaluatedResult(
                score = 100,
                stars = 3,
                feedback = "Outstanding! Perfect pronunciation!",
                isAccurate = true
            )
        }

        // Levenshtein distance comparison
        val distance = levenshtein(cleanTarget, cleanSpoken)
        val maxLen = max(cleanTarget.length, cleanSpoken.length)
        val rawScore = ((1.0 - (distance.toDouble() / maxLen.toDouble())) * 100).toInt().coerceIn(0, 100)

        // If target word is contained in spoken sentence (e.g. "I say vocabulary")
        val bonus = if (cleanSpoken.contains(cleanTarget)) 15 else 0
        val finalScore = (rawScore + bonus).coerceIn(0, 100)

        val stars = when {
            finalScore >= 80 -> 3
            finalScore >= 55 -> 2
            else -> 1
        }

        val feedback = when (stars) {
            3 -> "Wonderful! Clear and natural speaking!"
            2 -> "Good try! Almost there, listen once more and repeat!"
            else -> "Keep going! Listen to the voice pronunciation and try again!"
        }

        return EvaluatedResult(
            score = finalScore,
            stars = stars,
            feedback = feedback,
            isAccurate = finalScore >= 60
        )
    }

    private fun levenshtein(s: String, t: String): Int {
        val dp = Array(s.length + 1) { IntArray(t.length + 1) }
        for (i in 0..s.length) dp[i][0] = i
        for (j in 0..t.length) dp[0][j] = j
        for (i in 1..s.length) {
            for (j in 1..t.length) {
                val cost = if (s[i - 1] == t[j - 1]) 0 else 1
                dp[i][j] = minOf(
                    dp[i - 1][j] + 1,
                    dp[i][j - 1] + 1,
                    dp[i - 1][j - 1] + cost
                )
            }
        }
        return dp[s.length][t.length]
    }

    fun destroy() {
        try {
            speechRecognizer?.destroy()
            speechRecognizer = null
        } catch (_: Exception) {}
    }

    private data class EvaluatedResult(
        val score: Int,
        val stars: Int,
        val feedback: String,
        val isAccurate: Boolean
    )
}
