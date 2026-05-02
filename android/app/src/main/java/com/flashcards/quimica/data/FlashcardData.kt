package com.flashcards.quimica.data

data class Flashcard(val question: String, val answer: String)

val flashcards = listOf(
    Flashcard("1 mmHg a torr", "1 torr"),
    Flashcard("1 atm en mmHg", "760 mmHg"),
    Flashcard("1 Pa en atm", "9.86 × 10⁻⁶ atm"),
    Flashcard("1 atm en Pa", "101,325 Pa"),
    Flashcard("Temperatura Normal", "273.15 K"),
    Flashcard("Presión Normal", "1 atm"),
    Flashcard("Volumen normal", "22.4 L"),
    Flashcard("Número de Avogadro", "6.022 × 10²³"),
    Flashcard("Constante R", "0.082 L·atm/mol·K"),
    Flashcard("Ley de Boyle", "V1P1 = V2P2 (T y n constantes)"),
    Flashcard("Ley de Charles", "V1/T1 = V2/T2 (P y n constantes)"),
    Flashcard("Ley de Gay-Lussac", "P1/T1 = P2/T2 (V y n constantes)"),
    Flashcard("Gas Ideal", "PV = nRT"),
    Flashcard("Densidad de un gas", "P = dRT / M"),
    Flashcard("Peso Molecular", "PV = mRT / M")
)