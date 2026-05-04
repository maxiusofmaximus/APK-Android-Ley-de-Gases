package com.flashcards.quimica.data

data class Flashcard(
    val question: String,
    val answer: String,
    val category: String,
    val emoji: String
)

enum class Category(val label: String, val emoji: String) {
    ALL("Todas", "📋"),
    CONVERSIONES("Conversiones", "🔄"),
    CONSTANTES("Constantes", "#️⃣"),
    LEYES("Leyes", "📖"),
    FORMULAS("Fórmulas", "🧮"),
    SOLUBILIDAD("Solubilidad", "🧪")
}

val flashcards = listOf(
    Flashcard("1 mmHg a torr", "1 torr", "Conversiones", "🔄"),
    Flashcard("1 atm en mmHg", "760 mmHg", "Conversiones", "🔄"),
    Flashcard("1 Pa en atm", "9.86 × 10⁻⁶ atm", "Conversiones", "🔄"),
    Flashcard("1 atm en Pa", "101,325 Pa", "Conversiones", "🔄"),

    Flashcard("Temperatura Normal", "273.15 K", "Constantes", "🌡️"),
    Flashcard("Presión Normal", "1 atm", "Constantes", "⚖️"),
    Flashcard("Volumen normal", "22.4 L", "Constantes", "📦"),
    Flashcard("Número de Avogadro", "6.022 × 10²³", "Constantes", "🔢"),
    Flashcard("Constante R", "0.082 L·atm/mol·K", "Constantes", "®️"),

    Flashcard("Ley de Boyle", "V₁P₁ = V₂P₂\n(T y n constantes)", "Leyes", "📖"),
    Flashcard("Ley de Charles", "V₁/T₁ = V₂/T₂\n(P y n constantes)", "Leyes", "📖"),
    Flashcard("Ley de Gay-Lussac", "P₁/T₁ = P₂/T₂\n(V y n constantes)", "Leyes", "📖"),
    Flashcard("Gas Ideal", "PV = nRT", "Leyes", "📖"),

    // NUEVAS
    Flashcard("Ley de Dalton", "PT = P1 + P2 + P3...", "Leyes", "📖"),
    Flashcard("Presiones Parciales", "Pi = Xi · PT", "Leyes", "📖"),

    Flashcard("Densidad de un gas", "P = dRT / M", "Leyes", "📖"),
    Flashcard("Peso Molecular", "PV = mRT / M", "Leyes", "📖"),

    // SOLUBILIDAD
    Flashcard("Tiene un metal del grupo IA", "Soluble", "Solubilidad", "🧪"),
    Flashcard("Tiene un nitrato", "Soluble", "Solubilidad", "🧪"),
    Flashcard("Es un halogenuro + Ag, Hg o Pb", "Insoluble", "Solubilidad", "🧪"),
    Flashcard("Es un halogenuro - Ag, Hg o Pb", "Soluble", "Solubilidad", "🧪"),
    Flashcard("Es un carbonato o fosfato", "Insoluble", "Solubilidad", "🧪"),
    Flashcard("Es un carbonato o fosfato + metal Grupo IA", "Soluble", "Solubilidad", "🧪"),
    Flashcard("Es un sulfato + Na/ Cu/ Mg", "Soluble", "Solubilidad", "🧪"),
    Flashcard("Es un sulfato + Ba/ Pb / Sr/ Ca", "Insoluble", "Solubilidad", "🧪")
)
