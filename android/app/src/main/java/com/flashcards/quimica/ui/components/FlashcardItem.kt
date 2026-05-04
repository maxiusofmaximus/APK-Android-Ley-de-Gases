package com.flashcards.quimica.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flashcards.quimica.data.Flashcard
import com.flashcards.quimica.ui.theme.*

@Composable
fun FlashcardItem(
    flashcard: Flashcard,
    isDark: Boolean,
    onFlip: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var isFlipped by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "flip"
    )

    val categoryColor = getCategoryColor(flashcard.category)
    val cardBg = if (isDark) CardDark else CardLight
    val borderColor = if (isDark) categoryColor.copy(alpha = 0.3f) else categoryColor.copy(alpha = 0.15f)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 14f * density
            }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                isFlipped = !isFlipped
                onFlip(isFlipped)
            }
    ) {
        if (rotation <= 90f) {
            // FRONT — Question
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .shadow(8.dp, RoundedCornerShape(16.dp), ambientColor = categoryColor.copy(alpha = 0.2f))
                    .clip(RoundedCornerShape(16.dp))
                    .background(cardBg)
                    .then(
                        Modifier.background(
                            Brush.verticalGradient(
                                colors = listOf(borderColor, Color.Transparent),
                                startY = 0f,
                                endY = 80f
                            )
                        )
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = flashcard.emoji,
                        fontSize = 28.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Category badge
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(categoryColor)
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = flashcard.category.uppercase(),
                            color = White,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                    }

                    Spacer(Modifier.height(10.dp))

                    Text(
                        text = flashcard.question,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Toca para ver",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                    )
                }
            }
        } else {
            // BACK — Answer
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { rotationY = 180f }
                    .shadow(12.dp, RoundedCornerShape(16.dp), ambientColor = categoryColor.copy(alpha = 0.4f))
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                categoryColor,
                                categoryColor.copy(alpha = 0.8f),
                                Violet.copy(alpha = 0.9f)
                            )
                        )
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "RESPUESTA",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = White.copy(alpha = 0.7f),
                        letterSpacing = 3.sp
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = flashcard.answer,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = White,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Monospace
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = "✓",
                        fontSize = 18.sp,
                        color = White.copy(alpha = 0.6f)
                    )
                }
            }
        }
    }
}

fun getCategoryColor(category: String): Color {
    return when (category) {
        "Conversiones" -> CategoryConversiones
        "Constantes" -> CategoryConstantes
        "Leyes" -> CategoryLeyes
        "Fórmulas" -> CategoryFormulas
        "Solubilidad" -> Cyan
        else -> Indigo
    }
}