package com.flashcards.quimica.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = Indigo,
    onPrimary = White,
    primaryContainer = IndigoLight,
    secondary = Pink,
    tertiary = Cyan,
    surface = SurfaceLight,
    onSurface = TextPrimary,
    background = BackgroundLight,
    onBackground = TextPrimary,
    surfaceVariant = CardLight,
    onSurfaceVariant = TextSecondary
)

private val DarkColorScheme = darkColorScheme(
    primary = IndigoLight,
    onPrimary = White,
    primaryContainer = IndigoDark,
    secondary = PinkLight,
    tertiary = CyanLight,
    surface = SurfaceDark,
    onSurface = TextPrimaryDark,
    background = BackgroundDark,
    onBackground = TextPrimaryDark,
    surfaceVariant = CardDark,
    onSurfaceVariant = TextSecondaryDark
)

@Composable
fun FlashcardsQuimicaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val activity = view.context as? Activity
            activity?.window?.let { window ->
                window.statusBarColor = colorScheme.background.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}