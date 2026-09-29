package com.example.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val testTag: String
) {
    data object Words : Screen("words", "Words", Icons.AutoMirrored.Filled.MenuBook, "nav_words")
    data object Speaking : Screen("speaking", "Speaking", Icons.Filled.Mic, "nav_speaking")
    data object Grammar : Screen("grammar", "Grammar", Icons.Filled.School, "nav_grammar")
    data object Tests : Screen("tests", "Tests", Icons.Filled.Quiz, "nav_tests")
    data object Books : Screen("books", "Reading", Icons.Filled.AutoStories, "nav_books")
    data object SonyIntellect : Screen("sony_intellect", "Sony Intellect", Icons.Filled.Psychology, "nav_sony")
    data object Settings : Screen("settings", "Settings", Icons.Filled.Settings, "nav_settings")

    companion object {
        val bottomNavItems: List<Screen>
            get() = listOf(
                Words,
                Speaking,
                Grammar,
                Tests,
                Books,
                SonyIntellect,
                Settings
            )
    }
}
