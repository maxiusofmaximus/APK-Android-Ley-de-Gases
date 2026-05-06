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
    FORMULAS("Fórmulas", "⚗️"),
    SOLUBILIDAD("Solubilidad", "🧪")
}

val flashcards = listOf(
    Flashcard("1 mmHg a torr", "1 torr", "Conversiones", "🔄"),
    Flashcard("1 atm en mmHg", "760 mmHg", "Conversiones", "🔄"),
    Flashcard("1 atm en Pa", "101,325 Pa", "Conversiones", "🔄"),

    Flashcard("Temperatura Normal", "273.15 K", "Constantes", "🌡️"),
    Flashcard("Presión Normal", "1 atm", "Constantes", "⚖️"),
    Flashcard("Volumen normal", "22.4 L", "Constantes", "📦"),
    Flashcard("Número de Avogadro", "6.022 × 10²³", "Constantes", "🔢"),
    Flashcard("Constante R", "0.082 L·atm/mol·K", "Constantes", "®️"),

    Flashcard("Ley de Boyle", "V₁P₁ = V₂P₂\n(T y n constantes)", "Leyes", "📖"),
    Flashcard("Ley de Charles", "<frac>V₁|T₁</frac> = <frac>V₂|T₂</frac>\n(P y n constantes)", "Leyes", "📖"),
    Flashcard("Ley de Gay-Lussac", "<frac>P₁|T₁</frac> = <frac>P₂|T₂</frac>\n(V y n constantes)", "Leyes", "📖"),
    Flashcard("Gas Ideal", "PV = nRT", "Leyes", "📖"),

    // NUEVAS
    Flashcard("Ley de Dalton", "PT = P1 + P2 + P3...", "Leyes", "📖"),
    Flashcard("Presiones Parciales", "Pi = Xi · PT", "Leyes", "📖"),

    Flashcard("Densidad de un gas", "P = <frac>dRT|M</frac>", "Leyes", "📖"),
    Flashcard("Peso Molecular", "PV = <frac>mRT|M</frac>", "Leyes", "📖"),

    // FORMULAS
    Flashcard("Porcentaje m/m", "% m/m = <frac>g sto|g sln</frac> * 100", "Fórmulas", "⚗️"),
    Flashcard("Porcentaje V/V", "% V/V = <frac>mL sto|mL sln</frac> * 100", "Fórmulas", "⚗️"),
    Flashcard("Porcentaje m/V", "% m/V = <frac>g sto|mL sln</frac> * 100", "Fórmulas", "⚗️"),
    Flashcard("Molaridad", "M = <frac>mol sto|L sln</frac>", "Fórmulas", "⚗️"),
    Flashcard("Molaridad (extendida)", "M = <frac>masa sto|(PM sto) V sln</frac>", "Fórmulas", "⚗️"),
    Flashcard("molalidad", "m = <frac>mol sto|Kg ste</frac>", "Fórmulas", "⚗️"),
    Flashcard("Partes por millón", "ppm = <frac>mg sto|L sln</frac>", "Fórmulas", "⚗️"),
    Flashcard("Diluciones", "C1 * V1 = C2 * V2", "Fórmulas", "⚗️"),
    Flashcard("Fracción molar", "X_A = <frac>mol A|moles totales</frac>\nX_B = <frac>mol B|moles totales</frac>\nX_A + X_B = 1", "Fórmulas", "⚗️"),

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
