package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.LearningLevel
import com.example.ui.navigation.Screen
import com.example.ui.screens.BooksScreen
import com.example.ui.screens.GrammarScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.SonyIntellectScreen
import com.example.ui.screens.SpeakingScreen
import com.example.ui.screens.TestsScreen
import com.example.ui.screens.WordsScreen
import com.example.ui.theme.FdegoBlue
import com.example.ui.theme.FdegoBlueContainer
import com.example.ui.theme.FdegoGold
import com.example.ui.theme.FdegoGreen
import com.example.ui.theme.FdegoGreenContainer
import com.example.ui.theme.FdegoTheme
import com.example.ui.viewmodel.FdegoViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: FdegoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FdegoTheme {
                FdegoApp(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FdegoApp(viewModel: FdegoViewModel) {
    val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()
    val selectedLanguage by viewModel.selectedLanguage.collectAsStateWithLifecycle()
    val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()
    val learningLevel by viewModel.learningLevel.collectAsStateWithLifecycle()

    BackHandler(enabled = currentScreen != Screen.Words) {
        viewModel.navigateTo(Screen.Words)
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isWideScreen = maxWidth >= 600.dp

        if (isWideScreen) {
            // Adaptive Layout for Tablet / Large Screen: NavigationRail on side
            Row(modifier = Modifier.fillMaxSize()) {
                NavigationRail(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(
                        text = "Fdego",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = FdegoBlue
                        ),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Screen.bottomNavItems.filterNotNull().forEach { screen ->
                        NavigationRailItem(
                            selected = currentScreen == screen,
                            onClick = { viewModel.navigateTo(screen) },
                            icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                            label = { Text(screen.title, fontSize = 11.sp) },
                            colors = NavigationRailItemDefaults.colors(
                                selectedIconColor = FdegoBlue,
                                selectedTextColor = FdegoBlue,
                                indicatorColor = FdegoBlue.copy(alpha = 0.15f)
                            ),
                            modifier = Modifier.testTag(screen.testTag)
                        )
                    }
                }

                Scaffold(
                    topBar = {
                        FdegoTopBar(
                            title = currentScreen.title,
                            flag = selectedLanguage.flag,
                            stars = userProfile?.totalStars ?: 0,
                            learningLevel = learningLevel
                        )
                    },
                    modifier = Modifier.weight(1f)
                ) { innerPadding ->
                    ScreenContent(
                        screen = currentScreen,
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        } else {
            // Standard Mobile Layout: Bottom NavigationBar
            Scaffold(
                topBar = {
                    FdegoTopBar(
                        title = currentScreen.title,
                        flag = selectedLanguage.flag,
                        stars = userProfile?.totalStars ?: 0,
                        learningLevel = learningLevel
                    )
                },
                bottomBar = {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.surface,
                        tonalElevation = 4.dp
                    ) {
                        Screen.bottomNavItems.filterNotNull().forEach { screen ->
                            val isSelected = currentScreen == screen
                            NavigationBarItem(
                                selected = isSelected,
                                onClick = { viewModel.navigateTo(screen) },
                                icon = {
                                    Icon(
                                        imageVector = screen.icon,
                                        contentDescription = screen.title,
                                        modifier = Modifier.size(22.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        text = screen.title,
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontSize = 9.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                        ),
                                        maxLines = 1
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = FdegoBlue,
                                    selectedTextColor = FdegoBlue,
                                    indicatorColor = FdegoBlue.copy(alpha = 0.15f)
                                ),
                                modifier = Modifier.testTag(screen.testTag)
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                ScreenContent(
                    screen = currentScreen,
                    viewModel = viewModel,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FdegoTopBar(
    title: String,
    flag: String,
    stars: Int,
    learningLevel: LearningLevel
) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Fdego",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = FdegoBlue,
                        letterSpacing = 0.5.sp
                    )
                )
                Spacer(modifier = Modifier.width(6.dp))
                Surface(
                    color = FdegoGreenContainer,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Kids",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = FdegoGreen,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
        },
        actions = {
            // Learning Level Pill
            Surface(
                color = FdegoBlueContainer,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(end = 6.dp)
                    .testTag("topbar_learning_level")
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = learningLevel.badgeIcon, fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "Lv.${learningLevel.levelNumber}",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = FdegoBlue
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${learningLevel.totalPoints} XP",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = FdegoGreen
                        )
                    )
                }
            }

            // Stars and Flag
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.padding(end = 12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = flag, fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(3.dp))
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Stars",
                        tint = FdegoGold,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "$stars",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = FdegoGold
                        )
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
fun ScreenContent(
    screen: Screen,
    viewModel: FdegoViewModel,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (screen) {
            Screen.Words -> WordsScreen(viewModel = viewModel)
            Screen.Speaking -> SpeakingScreen(viewModel = viewModel)
            Screen.Grammar -> GrammarScreen(viewModel = viewModel)
            Screen.Tests -> TestsScreen(viewModel = viewModel)
            Screen.Books -> BooksScreen(viewModel = viewModel)
            Screen.SonyIntellect -> SonyIntellectScreen(viewModel = viewModel)
            Screen.Settings -> SettingsScreen(viewModel = viewModel)
        }
    }
}
