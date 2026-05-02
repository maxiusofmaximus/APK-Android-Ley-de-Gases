package com.flashcards.quimica.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flashcards.quimica.data.flashcards
import com.flashcards.quimica.ui.components.FlashcardItem

@Composable
fun FlashcardApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F2F5))
            .padding(20.dp)
    ) {
        Text(
            text = "📚 Mis Flashcards de Química",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2C3E50),
            modifier = Modifier.padding(bottom = 20.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 250.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(flashcards) { flashcard -> FlashcardItem(flashcard = flashcard) }
        }
    }
}