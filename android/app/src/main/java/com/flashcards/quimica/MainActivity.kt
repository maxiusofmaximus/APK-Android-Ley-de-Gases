package com.flashcards.quimica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.flashcards.quimica.ui.FlashcardApp
import com.flashcards.quimica.ui.theme.FlashcardsQuimicaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlashcardsQuimicaTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    FlashcardApp()
                }
            }
        }
    }
}