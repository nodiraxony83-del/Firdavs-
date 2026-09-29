package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.R
import com.example.data.model.BookChapter
import com.example.data.model.HistoricalFigureBook
import com.example.ui.theme.FdegoBlue
import com.example.ui.theme.FdegoBlueContainer
import com.example.ui.theme.FdegoError
import com.example.ui.theme.FdegoGreen
import com.example.ui.theme.FdegoGreenContainer
import com.example.ui.viewmodel.FdegoViewModel

@Composable
fun BooksScreen(
    viewModel: FdegoViewModel,
    modifier: Modifier = Modifier
) {
    val activeBook by viewModel.activeBook.collectAsStateWithLifecycle()
    val activeChapter by viewModel.activeBookChapter.collectAsStateWithLifecycle()
    val quizChoice by viewModel.bookQuizChoice.collectAsStateWithLifecycle()
    val isQuizChecked by viewModel.isBookQuizChecked.collectAsStateWithLifecycle()
    val isSpeaking by viewModel.ttsManager.isSpeaking.collectAsStateWithLifecycle()

    if (activeBook != null && activeChapter != null) {
        BackHandler {
            viewModel.closeBook()
        }

        // Single Book Reader View
        BookReaderView(
            book = activeBook!!,
            currentChapter = activeChapter!!,
            quizChoice = quizChoice,
            isQuizChecked = isQuizChecked,
            isSpeaking = isSpeaking,
            onChapterSelect = { viewModel.selectBookChapter(it) },
            onPronounce = { viewModel.pronounceWord(it) },
            onReadAloud = {
                if (isSpeaking) {
                    viewModel.stopSpeaking()
                } else {
                    viewModel.pronounceWord(activeChapter!!.content)
                }
            },
            onQuizChoice = { viewModel.selectBookQuizChoice(it) },
            onCheckQuiz = { viewModel.checkBookQuiz() },
            onBack = { viewModel.closeBook() },
            modifier = modifier
        )
    } else {
        // Book Library View
        BookLibraryView(
            books = viewModel.booksList,
            onOpenBook = { viewModel.openBook(it) },
            modifier = modifier
        )
    }
}

@Composable
private fun BookLibraryView(
    books: List<HistoricalFigureBook>,
    onOpenBook: (HistoricalFigureBook) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("books_library_list"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Banner
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.fdego_history_books),
                            contentDescription = "History Books Banner",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.35f))
                        )
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Historical People Electronic Books",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "Inspiring stories of great minds who changed history",
                                style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = FdegoBlueContainer,
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AutoStories,
                                    contentDescription = null,
                                    tint = FdegoBlue,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "${books.size} Electronic Biographies",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = FdegoBlue,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                        }

                        Surface(
                            color = FdegoGreenContainer,
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Read Aloud with Voice 🔊",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = FdegoGreen,
                                    fontWeight = FontWeight.SemiBold
                                ),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
            }
        }

        item {
            Text(
                text = "Library Collection:",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
        }

        items(books, key = { it.id }) { book ->
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("book_card_${book.id}")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = book.name,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = FdegoBlue
                                )
                            )
                            Text(
                                text = book.epithet,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = FdegoGreen
                                )
                            )
                            Text(
                                text = "${book.era} • ${book.country}",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }

                        Surface(
                            color = FdegoGreenContainer,
                            shape = CircleShape,
                            modifier = Modifier.size(40.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.MenuBook,
                                    contentDescription = null,
                                    tint = FdegoGreen,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = book.coverSummary,
                        style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 20.sp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Quote
                    Surface(
                        color = FdegoBlueContainer.copy(alpha = 0.6f),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.FormatQuote,
                                contentDescription = null,
                                tint = FdegoBlue,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "\"${book.famousQuote}\"",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Medium,
                                    color = FdegoBlue
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${book.chapters.size} Chapters • Audio Supported",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )

                        Button(
                            onClick = { onOpenBook(book) },
                            colors = ButtonDefaults.buttonColors(containerColor = FdegoBlue),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.testTag("btn_read_${book.id}")
                        ) {
                            Text("Open Book 📖")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BookReaderView(
    book: HistoricalFigureBook,
    currentChapter: BookChapter,
    quizChoice: Int?,
    isQuizChecked: Boolean,
    isSpeaking: Boolean,
    onChapterSelect: (BookChapter) -> Unit,
    onPronounce: (String) -> Unit,
    onReadAloud: () -> Unit,
    onQuizChoice: (Int) -> Unit,
    onCheckQuiz: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .testTag("book_reader_view"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Navigation Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.testTag("btn_back_to_library")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back to Library",
                    tint = FdegoBlue
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = book.name,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = book.epithet,
                    style = MaterialTheme.typography.labelSmall.copy(color = FdegoGreen)
                )
            }

            // Audio Read Aloud Button
            Button(
                onClick = onReadAloud,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSpeaking) FdegoError else FdegoGreen
                ),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                modifier = Modifier.testTag("btn_read_aloud_toggle")
            ) {
                Icon(
                    imageVector = if (isSpeaking) Icons.Default.Stop else Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(if (isSpeaking) "Stop" else "Listen", color = Color.White)
            }
        }

        // Chapters Switcher Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            book.chapters.forEach { chapter ->
                val isSelected = chapter.chapterNumber == currentChapter.chapterNumber
                Surface(
                    onClick = { onChapterSelect(chapter) },
                    shape = RoundedCornerShape(12.dp),
                    color = if (isSelected) FdegoBlue else MaterialTheme.colorScheme.surface,
                    shadowElevation = 2.dp,
                    modifier = Modifier.testTag("chapter_tab_${chapter.chapterNumber}")
                ) {
                    Text(
                        text = "Chapter ${chapter.chapterNumber}",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                    )
                }
            }
        }

        // Chapter Content Card
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Chapter ${currentChapter.chapterNumber}: ${currentChapter.title}",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = FdegoBlue
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = currentChapter.content,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        lineHeight = 28.sp,
                        fontSize = 17.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Key Historical Vocabulary Tags
                Text(
                    text = "Historical Words Spotlight (Tap to hear pronunciation):",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    book.vocabularyWords.forEach { word ->
                        Surface(
                            onClick = { onPronounce(word) },
                            color = FdegoGreenContainer,
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                    contentDescription = null,
                                    tint = FdegoGreen,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = word,
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = FdegoGreen,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }

        // Fun Facts Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = FdegoBlueContainer.copy(alpha = 0.5f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = FdegoBlue
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Did You Know?",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = FdegoBlue
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                book.funFacts.forEach { fact ->
                    Text(
                        text = "• $fact",
                        style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
            }
        }

        // Chapter Comprehension Mini-Quiz
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = "🧠 Chapter Comprehension Check:",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = currentChapter.comprehensionQuestion,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    currentChapter.choices.forEachIndexed { index, choice ->
                        val isSelected = quizChoice == index
                        val isCorrect = index == currentChapter.correctChoice

                        val containerColor = when {
                            isQuizChecked && isCorrect -> FdegoGreenContainer
                            isQuizChecked && isSelected && !isCorrect -> MaterialTheme.colorScheme.errorContainer
                            isSelected -> FdegoBlueContainer
                            else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        }

                        Surface(
                            onClick = { onQuizChoice(index) },
                            shape = RoundedCornerShape(10.dp),
                            color = containerColor,
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("book_quiz_choice_$index")
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${('A' + index)}. $choice",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.weight(1f)
                                )

                                if (isQuizChecked) {
                                    if (isCorrect) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = null,
                                            tint = FdegoGreen
                                        )
                                    } else if (isSelected) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = null,
                                            tint = FdegoError
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                if (!isQuizChecked) {
                    Button(
                        onClick = onCheckQuiz,
                        enabled = quizChoice != null,
                        colors = ButtonDefaults.buttonColors(containerColor = FdegoGreen),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("btn_check_book_quiz")
                    ) {
                        Text("Check Answer")
                    }
                } else {
                    val isCorrect = quizChoice == currentChapter.correctChoice
                    Surface(
                        color = if (isCorrect) FdegoGreenContainer else MaterialTheme.colorScheme.errorContainer,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = if (isCorrect) "🌟 Excellent reading comprehension! You mastered this chapter!" else "💡 Good try! Read the chapter text again to find the answer!",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = if (isCorrect) FdegoGreen else FdegoError
                            ),
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        }
    }
}
