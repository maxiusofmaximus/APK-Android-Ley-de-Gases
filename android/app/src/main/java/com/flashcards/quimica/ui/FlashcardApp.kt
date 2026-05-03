package com.flashcards.quimica.ui

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flashcards.quimica.R
import com.flashcards.quimica.data.Category
import com.flashcards.quimica.data.Flashcard
import com.flashcards.quimica.data.flashcards
import com.flashcards.quimica.ui.components.FlashcardItem
import com.flashcards.quimica.ui.theme.*

enum class Screen {
    TARJETAS, EXAMEN
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashcardApp() {
    var isDark by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf(Category.ALL) }
    var cardList by remember { mutableStateOf(flashcards) }
    var reviewedSet by remember { mutableStateOf(setOf<Int>()) }
    var currentScreen by remember { mutableStateOf(Screen.TARJETAS) }
    var menuExpanded by remember { mutableStateOf(false) }
    var resetTrigger by remember { mutableStateOf(0) }

    val filteredCards = if (selectedCategory == Category.ALL) cardList
        else cardList.filter { it.category == selectedCategory.label }

    val totalCards = flashcards.size
    val reviewedCount = reviewedSet.size
    val progressPercent = if (totalCards > 0) (reviewedCount * 100 / totalCards) else 0

    FlashcardsQuimicaTheme(darkTheme = isDark) {
        val bgColor = MaterialTheme.colorScheme.background

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor)
                .systemBarsPadding()
        ) {
            // ═══ TOP BAR ═══
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.app_logo),
                            contentDescription = "Flashcards Quimica",
                            modifier = Modifier.size(40.dp).clip(CircleShape)
                        )
                    }
                },
                actions = {
                    Box {
                        IconButton(onClick = { menuExpanded = !menuExpanded }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menú", tint = Indigo)
                        }
                        DropdownMenu(
                            expanded = menuExpanded,
                            onDismissRequest = { menuExpanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("📋 Tarjetas") },
                                onClick = {
                                    currentScreen = Screen.TARJETAS
                                    menuExpanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("📝 Examen") },
                                onClick = {
                                    currentScreen = Screen.EXAMEN
                                    menuExpanded = false
                                }
                            )
                        }
                    }
                    // Shuffle
                    IconButton(onClick = { cardList = cardList.shuffled() }) {
                        Text("🔀", fontSize = 20.sp, modifier = Modifier.padding(4.dp))
                    }
                    // Reset
                    IconButton(onClick = {
                        reviewedSet = setOf()
                        cardList = flashcards
                        resetTrigger++
                    }) {
                        Text("🔄", fontSize = 20.sp, modifier = Modifier.padding(4.dp))
                    }
                    // Dark mode toggle
                    IconButton(onClick = { isDark = !isDark }) {
                        Text(if (isDark) "☀️" else "🌙", fontSize = 20.sp, modifier = Modifier.padding(4.dp))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = bgColor,
                    titleContentColor = Indigo
                )
            )

            if (currentScreen == Screen.TARJETAS) {
                // ═══ CARDS GRID ═══
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        // ═══ HERO SECTION ═══
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = if (isDark) listOf(
                                Color(0xFF1E1B4B).copy(alpha = 0.8f),
                                Color(0xFF13121F)
                            ) else listOf(
                                Indigo.copy(alpha = 0.08f),
                                bgColor
                            )
                        )
                    )
                    .padding(horizontal = 24.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Indigo.copy(alpha = 0.15f))
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "📚 Estudio Interactivo",
                        fontSize = 12.sp,
                        color = if (isDark) IndigoLight else Indigo,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(Modifier.height(12.dp))

                // Title with gradient look
                Text(
                    text = "Ley de Gases",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Domina las conversiones, constantes y leyes\nfundamentales con tarjetas interactivas",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp
                )

                Spacer(Modifier.height(20.dp))

                // ═══ PROGRESS STATS ═══
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            if (isDark) CardDark.copy(alpha = 0.7f)
                            else White.copy(alpha = 0.8f)
                        )
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem(value = "$totalCards", label = "TARJETAS", color = Indigo, isDark = isDark)
                    StatItem(value = "$reviewedCount", label = "REVISADAS", color = Pink, isDark = isDark)
                    StatItem(value = "$progressPercent%", label = "PROGRESO", color = Cyan, isDark = isDark)
                }

                Spacer(Modifier.height(12.dp))

                // Progress bar
                LinearProgressIndicator(
                    progress = { progressPercent / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = Indigo,
                    trackColor = if (isDark) ProgressTrackDark else ProgressTrack,
                )

                // Completion message
                if (progressPercent == 100) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "🎉 ¡Felicidades! Has revisado todas las tarjetas",
                        fontSize = 13.sp,
                        color = Cyan,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                }
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                // ═══ CATEGORY FILTERS ═══
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Category.entries.forEach { cat ->
                    val isSelected = selectedCategory == cat
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                if (isSelected) Indigo
                                else if (isDark) CardDark else Color(0xFFF3F4F6)
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) { selectedCategory = cat }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = if (cat == Category.ALL) "📋 Todas" else "${cat.emoji} ${cat.label}",
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) White
                                else MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1
                        )
                    }
                }
            }
        } // end item

        items(filteredCards, key = { it.question + resetTrigger.toString() }) { flashcard ->
            FlashcardItem(
                flashcard = flashcard,
                isDark = isDark,
                onFlip = { flipped ->
                    if (flipped) {
                        reviewedSet = reviewedSet + flashcards.indexOf(flashcard)
                    }
                }
            )
        }
    } // end LazyVerticalGrid
} else {
    key(resetTrigger) {
        ExamenScreen(isDark = isDark)
    }
}
        }
    }
}

@Composable
fun StatItem(value: String, label: String, color: Color, isDark: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = label,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            color = if (isDark) TextSecondaryDark else TextSecondary,
            letterSpacing = 1.5.sp
        )
    }
}

@Composable
fun ExamenScreen(isDark: Boolean) {
    val totalCards = flashcards.size
    val examenOrder by remember { mutableStateOf(flashcards.indices.shuffled()) }
    val pagerState = rememberPagerState(pageCount = { totalCards + 1 })
    val coroutineScope = rememberCoroutineScope()

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) { page ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (page < totalCards) {
                // EXAM CARD PAGE
                Text(
                    text = "Tarjeta ${page + 1} de $totalCards",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(32.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp),
                    contentAlignment = Alignment.Center
                ) {
                    key(page) {
                        Box(modifier = Modifier.height(250.dp).width(280.dp)) {
                            FlashcardItem(
                                flashcard = flashcards[examenOrder[page]],
                                isDark = isDark,
                                onFlip = {}
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Desliza para navegar",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                )
            } else {
                // RESULTS PAGE
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "🥳",
                        fontSize = 64.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "¡Has finalizado!",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Indigo,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Excelente trabajo repasando tus conocimientos.",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(0)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Indigo)
                    ) {
                        Text("🔄 Repetir Examen", color = White)
                    }
                }
            }
        }
    }
}