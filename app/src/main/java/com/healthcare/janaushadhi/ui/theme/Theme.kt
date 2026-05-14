package com.healthcare.janaushadhi.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.healthcare.janaushadhi.ui.theme.AppColors
// Modern Medical App Color Palette
object AppColors {
    // Primary Gradient Colors
    val PrimaryBlue = Color(0xFF4A90E2)
    val PrimaryPurple = Color(0xFF7C4DFF)
    val PrimaryCyan = Color(0xFF00BCD4)

    // Secondary Colors
    val AccentPink = Color(0xFFFF4081)
    val AccentOrange = Color(0xFFFF6B6B)
    val AccentGreen = Color(0xFF4CAF50)

    // Background Gradients
    val BackgroundStart = Color(0xFFF8F9FA)
    val BackgroundEnd = Color(0xFFE3F2FD)

    // Card Colors
    val CardWhite = Color(0xFFFFFFFF)
    val CardLight = Color(0xFFFAFAFA)

    // Text Colors
    val TextPrimary = Color(0xFF2C3E50)
    val TextSecondary = Color(0xFF7F8C8D)
    val TextLight = Color(0xFFBDC3C7)

    // Status Colors
    val SuccessGreen = Color(0xFF27AE60)
    val WarningOrange = Color(0xFFF39C12)
    val ErrorRed = Color(0xFFE74C3C)
    val InfoBlue = Color(0xFF3498DB)

    // Gradient Brushes
    val PrimaryGradient = Brush.horizontalGradient(
        colors = listOf(PrimaryBlue, PrimaryPurple)
    )

    val SecondaryGradient = Brush.horizontalGradient(
        colors = listOf(PrimaryCyan, PrimaryBlue)
    )

    val AccentGradient = Brush.horizontalGradient(
        colors = listOf(AccentPink, AccentOrange)
    )

    val BackgroundGradient = Brush.verticalGradient(
        colors = listOf(BackgroundStart, BackgroundEnd)
    )

    val SuccessGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF56CCF2), Color(0xFF2F80ED))
    )

    val CardGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFFFFFF),
            Color(0xFFF5F7FA)
        )
    )
}

private val LightColorScheme = lightColorScheme(
    primary = AppColors.PrimaryBlue,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE3F2FD),
    secondary = AppColors.PrimaryCyan,
    onSecondary = Color.White,
    tertiary = AppColors.PrimaryPurple,
    background = AppColors.BackgroundStart,
    surface = AppColors.CardWhite,
    onSurface = AppColors.TextPrimary,
    error = AppColors.ErrorRed,
    onError = Color.White
)

@Composable
fun JanAushadhiFinderTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}