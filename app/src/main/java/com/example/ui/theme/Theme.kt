package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = FdegoBlueLight,
    onPrimary = Color.Black,
    primaryContainer = FdegoBlueDark,
    onPrimaryContainer = Color.White,
    secondary = FdegoGreenLight,
    onSecondary = Color.Black,
    secondaryContainer = FdegoGreenDark,
    onSecondaryContainer = Color.White,
    background = Color(0xFF0F172A),
    surface = Color(0xFF1E293B),
    onBackground = Color.White,
    onSurface = Color.White,
    surfaceVariant = Color(0xFF334155),
    outline = Color(0xFF64748B)
)

private val LightColorScheme = lightColorScheme(
    primary = FdegoBlue,
    onPrimary = Color.White,
    primaryContainer = FdegoBlueContainer,
    onPrimaryContainer = FdegoBlueDark,
    secondary = FdegoGreen,
    onSecondary = Color.White,
    secondaryContainer = FdegoGreenContainer,
    onSecondaryContainer = FdegoGreenDark,
    background = FdegoBackground,
    surface = FdegoSurface,
    surfaceVariant = FdegoSurfaceVariant,
    onBackground = FdegoTextPrimary,
    onSurface = FdegoTextPrimary,
    onSurfaceVariant = FdegoTextSecondary,
    outline = FdegoBorder
)

@Composable
fun FdegoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

// For compatibility
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    FdegoTheme(darkTheme = darkTheme, content = content)
}
