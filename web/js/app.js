const flashcards = [
    { q: "1 mmHg a torr", a: "1 torr" },
    { q: "1 atm en mmHg", a: "760 mmHg" },
    { q: "1 Pa en atm", a: "9.86 × 10⁻⁶ atm" },
    { q: "1 atm en Pa", a: "101,325 Pa" },
    { q: "Temperatura Normal", a: "273.15 K" },
    { q: "Presión Normal", a: "1 atm" },
    { q: "Volumen normal", a: "22.4 L" },
    { q: "Número de Avogadro", a: "6.022 × 10²³" },
    { q: "Constante R", a: "0.082 L·atm/mol·K" },
    { q: "Ley de Boyle", a: "V1P1 = V2P2 (T y n constantes)" },
    { q: "Ley de Charles", a: "V1/T1 = V2/T2 (P y n constantes)" },
    { q: "Ley de Gay-Lussac", a: "P1/T1 = P2/T2 (V y n constantes)" },
    { q: "Gas Ideal", a: "PV = nRT" },
    { q: "Densidad de un gas", a: "P = dRT / M" },
    { q: "Peso Molecular", a: "PV = mRT / M" }
];

function createFlashcard(item) {
    const card = document.createElement('button');
    card.className = 'card';
    card.setAttribute('aria-label', `Tarjeta: ${item.q}`);
    card.innerHTML = `
        <div class="inner">
            <div class="front">${item.q}</div>
            <div class="back">${item.a}</div>
        </div>
    `;
    card.addEventListener('click', () => card.classList.toggle('flip'));
    return card;
}

function init() {
    const container = document.getElementById('flashcards-container');
    flashcards.forEach(item => container.appendChild(createFlashcard(item)));
}

document.addEventListener('DOMContentLoaded', init);