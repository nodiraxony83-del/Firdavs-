package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.dictionary.QuizData
import com.example.ui.theme.FdegoBlue
import com.example.ui.theme.FdegoBlueContainer
import com.example.ui.theme.FdegoError
import com.example.ui.theme.FdegoGold
import com.example.ui.theme.FdegoGreen
import com.example.ui.theme.FdegoGreenContainer
import com.example.ui.viewmodel.FdegoViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TestsScreen(
    viewModel: FdegoViewModel,
    modifier: Modifier = Modifier
) {
    val isRunning by viewModel.isTestRunning.collectAsStateWithLifecycle()
    val isCompleted by viewModel.isTestCompleted.collectAsStateWithLifecycle()
    val activeSuite by viewModel.activeTestSuite.collectAsStateWithLifecycle()
    val currentQIndex by viewModel.currentQuestionIndex.collectAsStateWithLifecycle()
    val selectedChoice by viewModel.selectedTestChoice.collectAsStateWithLifecycle()
    val isSubmitted by viewModel.isChoiceSubmitted.collectAsStateWithLifecycle()
    val scoreCount by viewModel.testScoreCount.collectAsStateWithLifecycle()
    val testHistory by viewModel.testResultsHistory.collectAsStateWithLifecycle()

    if (isCompleted) {
        // Results Screen
        TestResultsView(
            suite = activeSuite,
            correctCount = scoreCount,
            totalCount = activeSuite.questions.size,
            onRestart = { viewModel.startTest(activeSuite) },
            onExit = { viewModel.exitTest() },
            modifier = modifier
        )
    } else if (isRunning) {
        // Active Test Question Runner
        val currentQ = activeSuite.questions.getOrNull(currentQIndex)
        if (currentQ != null) {
            ActiveTestRunnerView(
                suite = activeSuite,
                question = currentQ,
                questionIndex = currentQIndex,
                totalQuestions = activeSuite.questions.size,
                selectedChoice = selectedChoice,
                isSubmitted = isSubmitted,
                onSelectChoice = { viewModel.selectTestChoice(it) },
                onSubmitChoice = { viewModel.submitTestChoice() },
                onNextQuestion = { viewModel.nextTestQuestion() },
                onPronounce = { word -> viewModel.pronounceWord(word) },
                onExit = { viewModel.exitTest() },
                modifier = modifier
            )
        }
    } else {
        // Test Selection & History
        TestSelectionView(
            testSuites = viewModel.testSuites,
            history = testHistory,
            onStartTest = { viewModel.startTest(it) },
            modifier = modifier
        )
    }
}

@Composable
private fun TestSelectionView(
    testSuites: List<QuizData.TestSuite>,
    history: List<com.example.data.local.TestResultEntity>,
    onStartTest: (QuizData.TestSuite) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("test_selection_list"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = FdegoBlueContainer),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = FdegoBlue,
                        shape = CircleShape,
                        modifier = Modifier.size(44.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(imageVector = Icons.Default.Quiz, contentDescription = null, tint = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Fdego Knowledge Tests",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = FdegoBlue
                            )
                        )
                        Text(
                            text = "Complete interactive tests and earn gold stars!",
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                        )
                    }
                }
            }
        }

        item {
            Text(
                text = "Available Tests:",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
        }

        // Test Cards
        items(testSuites, key = { it.id }) { suite ->
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("test_card_${suite.id}")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = when (suite.category) {
                                    "Listening" -> Icons.Default.Hearing
                                    "Vocabulary" -> Icons.AutoMirrored.Filled.MenuBook
                                    else -> Icons.Default.Quiz
                                },
                                contentDescription = null,
                                tint = FdegoBlue,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = suite.title,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        Surface(
                            color = FdegoGreenContainer,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "${suite.questions.size} Questions",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = FdegoGreen
                                ),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = suite.description,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = { onStartTest(suite) },
                        colors = ButtonDefaults.buttonColors(containerColor = FdegoGreen),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("btn_start_${suite.id}")
                    ) {
                        Text("Start Test 🚀")
                    }
                }
            }
        }

        // Test History Section
        if (history.isNotEmpty()) {
            item {
                Text(
                    text = "Recent Test Results History:",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            items(history) { result ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = result.testTitle,
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            val dateStr = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(result.completedAt))
                            Text(
                                text = "$dateStr • ${result.correctCount}/${result.totalQuestions} correct",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "${result.scorePercentage}%",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = if (result.scorePercentage >= 80) FdegoGreen else FdegoBlue
                                )
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Row {
                                repeat(result.starsEarned) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null,
                                        tint = FdegoGold,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ActiveTestRunnerView(
    suite: QuizData.TestSuite,
    question: com.example.data.model.QuizQuestion,
    questionIndex: Int,
    totalQuestions: Int,
    selectedChoice: Int?,
    isSubmitted: Boolean,
    onSelectChoice: (Int) -> Unit,
    onSubmitChoice: () -> Unit,
    onNextQuestion: () -> Unit,
    onPronounce: (String) -> Unit,
    onExit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("active_test_runner"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Progress Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Question ${questionIndex + 1} of $totalQuestions",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
            )
            OutlinedButton(
                onClick = onExit,
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text("Exit Test", style = MaterialTheme.typography.labelSmall)
            }
        }

        LinearProgressIndicator(
            progress = { (questionIndex + 1).toFloat() / totalQuestions.toFloat() },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = FdegoBlue,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )

        // Question Card
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = suite.title,
                        style = MaterialTheme.typography.labelMedium.copy(color = FdegoBlue, fontWeight = FontWeight.Bold)
                    )

                    if (question.audioWord != null) {
                        IconButton(
                            onClick = { onPronounce(question.audioWord) },
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(FdegoBlue)
                                .size(40.dp)
                                .testTag("btn_question_audio")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                contentDescription = "Play Question Audio",
                                tint = Color.White
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = question.question,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp
                    )
                )

                if (question.audioWord != null) {
                    Surface(
                        color = FdegoBlueContainer,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text(
                            text = "🔊 Tap the speaker button to hear the pronunciation!",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = FdegoBlue,
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Options List
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    question.options.forEachIndexed { index, option ->
                        val isSelected = selectedChoice == index
                        val isCorrectOption = index == question.correctIndex

                        val containerColor = when {
                            isSubmitted && isCorrectOption -> FdegoGreenContainer
                            isSubmitted && isSelected && !isCorrectOption -> MaterialTheme.colorScheme.errorContainer
                            isSelected -> FdegoBlueContainer
                            else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        }

                        val borderColor = when {
                            isSubmitted && isCorrectOption -> FdegoGreen
                            isSubmitted && isSelected && !isCorrectOption -> FdegoError
                            isSelected -> FdegoBlue
                            else -> Color.Transparent
                        }

                        Surface(
                            onClick = { onSelectChoice(index) },
                            shape = RoundedCornerShape(12.dp),
                            color = containerColor,
                            border = androidx.compose.foundation.BorderStroke(1.5.dp, borderColor),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("test_choice_$index")
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${('A' + index)}. $option",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                    ),
                                    modifier = Modifier.weight(1f)
                                )

                                if (isSubmitted) {
                                    if (isCorrectOption) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = "Correct",
                                            tint = FdegoGreen,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    } else if (isSelected) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Wrong",
                                            tint = FdegoError,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Explanation Banner after submit
                AnimatedVisibility(visible = isSubmitted) {
                    val isCorrect = selectedChoice == question.correctIndex
                    Surface(
                        color = if (isCorrect) FdegoGreenContainer else MaterialTheme.colorScheme.errorContainer,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = if (isCorrect) "🌟 Excellent! That's correct!" else "💡 Good try! Correct Answer:",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (isCorrect) FdegoGreen else FdegoError
                                )
                            )
                            Text(
                                text = question.explanation,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                    }
                }

                // Action Button
                if (!isSubmitted) {
                    Button(
                        onClick = onSubmitChoice,
                        enabled = selectedChoice != null,
                        colors = ButtonDefaults.buttonColors(containerColor = FdegoGreen),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("btn_submit_choice")
                    ) {
                        Text("Check Answer")
                    }
                } else {
                    Button(
                        onClick = onNextQuestion,
                        colors = ButtonDefaults.buttonColors(containerColor = FdegoBlue),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("btn_next_question")
                    ) {
                        Text(if (questionIndex < totalQuestions - 1) "Next Question ➔" else "See Results 🏆")
                    }
                }
            }
        }
    }
}

@Composable
private fun TestResultsView(
    suite: QuizData.TestSuite,
    correctCount: Int,
    totalCount: Int,
    onRestart: () -> Unit,
    onExit: () -> Unit,
    modifier: Modifier = Modifier
) {
    val percentage = if (totalCount > 0) (correctCount * 100) / totalCount else 0
    val stars = when {
        percentage >= 80 -> 3
        percentage >= 60 -> 2
        else -> 1
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .testTag("test_results_view"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    color = FdegoGold.copy(alpha = 0.15f),
                    shape = CircleShape,
                    modifier = Modifier.size(80.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = "Trophy",
                            tint = FdegoGold,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Test Completed! 🎉",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )

                Text(
                    text = suite.title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier.padding(top = 2.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Stars display
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(3) { index ->
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = if (index < stars) FdegoGold else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                            modifier = Modifier.size(42.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "$percentage%",
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = if (percentage >= 80) FdegoGreen else FdegoBlue
                    )
                )

                Text(
                    text = "$correctCount of $totalCount Correct Answers",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                )

                Spacer(modifier = Modifier.height(8.dp))

                val performanceMsg = when {
                    percentage >= 80 -> "🌟 Magnificent! You are a brilliant word champion!"
                    percentage >= 60 -> "👍 Great effort! You're making fantastic progress!"
                    else -> "💪 Good try! Practice a bit more and you'll reach 100%!"
                }

                Text(
                    text = performanceMsg,
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = onRestart,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("btn_retake_test")
                    ) {
                        Text("Retake Test")
                    }

                    Button(
                        onClick = onExit,
                        colors = ButtonDefaults.buttonColors(containerColor = FdegoBlue),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("btn_finish_test")
                    ) {
                        Text("Finish ➔")
                    }
                }
            }
        }
    }
}
