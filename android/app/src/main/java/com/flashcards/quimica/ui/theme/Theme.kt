package com.flashcards.quimica.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = Blue,
    onPrimary = White,
    surface = LightGray,
    onSurface = DarkBlue
)

@Composable
fun FlashcardsQuimicaTheme(content: @Composable () -> Unit) {
    val colorScheme = LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        val activity = view.context as? Activity
        activity?.window?.let { window ->
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.setDecorFitsSystemWindows(window, true)
        }
    }
    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}