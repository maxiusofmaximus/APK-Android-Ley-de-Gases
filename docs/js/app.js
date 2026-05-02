// ============================================
// Flashcards Química — App.js
// Premium Interactive Flashcard Application
// ============================================

const flashcards = [
    { q: "1 mmHg a torr", a: "1 torr", category: "conversiones", icon: "🔄" },
    { q: "1 atm en mmHg", a: "760 mmHg", category: "conversiones", icon: "🔄" },
    { q: "1 Pa en atm", a: "9.86 × 10⁻⁶ atm", category: "conversiones", icon: "🔄" },
    { q: "1 atm en Pa", a: "101,325 Pa", category: "conversiones", icon: "🔄" },

    { q: "Temperatura Normal", a: "273.15 K", category: "constantes", icon: "🌡️" },
    { q: "Presión Normal", a: "1 atm", category: "constantes", icon: "⚖️" },
    { q: "Volumen normal", a: "22.4 L", category: "constantes", icon: "📦" },
    { q: "Número de Avogadro", a: "6.022 × 10²³", category: "constantes", icon: "🔢" },
    { q: "Constante R", a: "0.082 L·atm/mol·K", category: "constantes", icon: "®️" },

    { q: "Ley de Boyle", a: "V₁P₁ = V₂P₂ (T y n constantes)", category: "leyes", icon: "📐" },
    { q: "Ley de Charles", a: "V₁/T₁ = V₂/T₂ (P y n constantes)", category: "leyes", icon: "📐" },
    { q: "Ley de Gay-Lussac", a: "P₁/T₁ = P₂/T₂ (V y n constantes)", category: "leyes", icon: "📐" },
    { q: "Gas Ideal", a: "PV = nRT", category: "leyes", icon: "⚗️" },

    { q: "Ley de Dalton", a: "PT = P1 + P2 + P3...", category: "leyes", icon: "📐" },
    { q: "Presiones Parciales", a: "Pi = Xi · PT", category: "leyes", icon: "📐" },

    { q: "Densidad de un gas", a: "P = dRT / M", category: "formulas", icon: "🧮" },
    { q: "Peso Molecular", a: "PV = mRT / M", category: "formulas", icon: "🧮" }
];

// State
let viewedCards = new Set();
let currentFilter = 'all';
let cardElements = [];

// ============================================
// Card Creation
// ============================================
function createFlashcard(item, index) {
    const card = document.createElement('button');
    card.className = 'card';
    card.setAttribute('aria-label', `Tarjeta: ${item.q}`);
    card.dataset.category = item.category;
    card.dataset.index = index;
    card.style.setProperty('--card-delay', `${index * 0.08}s`);

    const categoryColors = {
        conversiones: 'var(--cat-conversiones)',
        constantes: 'var(--cat-constantes)',
        leyes: 'var(--cat-leyes)',
        formulas: 'var(--cat-formulas)'
    };

    card.innerHTML = `
        <div class="card-inner">
            <div class="card-front">
                <span class="card-icon">${item.icon}</span>
                <span class="card-category-badge" style="background: ${categoryColors[item.category]}">${item.category}</span>
                <span class="card-question">${item.q}</span>
                <span class="card-hint"><i class="bi bi-hand-index"></i> Toca para ver</span>
            </div>
            <div class="card-back">
                <span class="card-back-label">Respuesta</span>
                <span class="card-answer">${item.a}</span>
                <span class="card-back-icon">✓</span>
            </div>
        </div>
    `;

    card.addEventListener('click', () => {
        card.classList.toggle('flip');
        if (card.classList.contains('flip')) {
            viewedCards.add(index);
            updateProgress();
        }
    });

    return card;
}

// ============================================
// Progress Tracking
// ============================================
function updateProgress() {
    const total = document.querySelectorAll('.card:not(.hidden)').length || flashcards.length;
    const viewed = viewedCards.size;
    const percent = Math.round((viewed / flashcards.length) * 100);

    document.getElementById('stat-viewed').textContent = viewed;
    document.getElementById('stat-percent').textContent = `${percent}%`;

    const progressFill = document.querySelector('.progress-fill');
    if (progressFill) {
        progressFill.style.width = `${percent}%`;
    }

    const progressBar = document.getElementById('progress-bar');
    if (progressBar) {
        progressBar.setAttribute('aria-valuenow', percent);
    }

    // Celebration when all cards viewed
    if (viewed === flashcards.length) {
        celebrateCompletion();
    }
}

function celebrateCompletion() {
    const hero = document.querySelector('.hero-title');
    if (hero && !hero.classList.contains('celebrated')) {
        hero.classList.add('celebrated');
        hero.innerHTML = '🎉 <span class="gradient-text">¡Completo!</span>';
        setTimeout(() => {
            hero.innerHTML = 'Ley de <span class="gradient-text">Gases</span>';
            hero.classList.remove('celebrated');
        }, 3000);
    }
}

// ============================================
// Filtering
// ============================================
function filterCards(category) {
    currentFilter = category;
    const cards = document.querySelectorAll('.card');

    cards.forEach((card, i) => {
        if (category === 'all' || card.dataset.category === category) {
            card.classList.remove('hidden');
            card.style.setProperty('--card-delay', `${i * 0.06}s`);
            card.classList.remove('card-enter');
            void card.offsetWidth; // trigger reflow
            card.classList.add('card-enter');
        } else {
            card.classList.add('hidden');
        }
    });

    // Update filter buttons
    document.querySelectorAll('.filter-btn').forEach(btn => {
        btn.classList.toggle('active', btn.dataset.category === category);
    });
}

// ============================================
// Shuffle
// ============================================
function shuffleCards() {
    const container = document.getElementById('flashcards-container');
    const cards = Array.from(container.children);

    // Fisher-Yates shuffle
    for (let i = cards.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        container.appendChild(cards[j]);
        cards.splice(j, 1, cards[i]);
    }

    // Re-animate entrance
    container.querySelectorAll('.card').forEach((card, i) => {
        card.style.setProperty('--card-delay', `${i * 0.05}s`);
        card.classList.remove('card-enter');
        void card.offsetWidth;
        card.classList.add('card-enter');
    });
}

// ============================================
// Reset
// ============================================
function resetProgress() {
    viewedCards.clear();
    document.querySelectorAll('.card.flip').forEach(card => {
        card.classList.remove('flip');
    });
    updateProgress();
}

// ============================================
// Dark Mode
// ============================================
function initTheme() {
    const saved = localStorage.getItem('quimica-theme');
    const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
    const theme = saved || (prefersDark ? 'dark' : 'light');
    setTheme(theme);
}

function setTheme(theme) {
    document.documentElement.setAttribute('data-theme', theme);
    localStorage.setItem('quimica-theme', theme);

    const icon = document.querySelector('#btn-theme i');
    if (icon) {
        icon.className = theme === 'dark' ? 'bi bi-sun-fill' : 'bi bi-moon-stars-fill';
    }

    // Update theme-color meta tag
    const meta = document.querySelector('meta[name="theme-color"]');
    if (meta) {
        meta.content = theme === 'dark' ? '#1a1a2e' : '#6366f1';
    }
}

function toggleTheme() {
    const current = document.documentElement.getAttribute('data-theme');
    setTheme(current === 'dark' ? 'light' : 'dark');
}

// ============================================
// Navbar scroll effect
// ============================================
function initScrollEffect() {
    const nav = document.getElementById('main-nav');
    let ticking = false;

    window.addEventListener('scroll', () => {
        if (!ticking) {
            requestAnimationFrame(() => {
                nav.classList.toggle('scrolled', window.scrollY > 50);
                ticking = false;
            });
            ticking = true;
        }
    });
}

// ============================================
// Examen View Logic
// ============================================
let examenOrder = [];
let currentExamenIndex = 0;

function initExamen() {
    // Shuffle all indices
    examenOrder = Array.from({length: flashcards.length}, (_, i) => i);
    for (let i = examenOrder.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [examenOrder[i], examenOrder[j]] = [examenOrder[j], examenOrder[i]];
    }
    currentExamenIndex = 0;
    document.getElementById('examen-total').textContent = flashcards.length;
    
    document.getElementById('examen-controls').classList.remove('d-none');
    document.getElementById('examen-end-message').classList.add('d-none');
    document.querySelector('.examen-progress-text').classList.remove('d-none');
    
    renderExamenCard();
}

function renderExamenCard() {
    const container = document.getElementById('examen-card-container');
    container.innerHTML = '';
    
    if (currentExamenIndex >= flashcards.length) {
        // Exam finished
        container.innerHTML = '';
        document.getElementById('examen-controls').classList.add('d-none');
        document.querySelector('.examen-progress-text').classList.add('d-none');
        document.getElementById('examen-end-message').classList.remove('d-none');
        return;
    }
    
    document.getElementById('examen-current-idx').textContent = currentExamenIndex + 1;
    
    const cardIndex = examenOrder[currentExamenIndex];
    const item = flashcards[cardIndex];
    const card = createFlashcard(item, cardIndex);
    card.classList.add('card-enter');
    container.appendChild(card);
}

function nextExamenCard() {
    currentExamenIndex++;
    renderExamenCard();
}

function switchView(viewName) {
    const viewTarjetas = document.getElementById('view-tarjetas');
    const viewExamen = document.getElementById('view-examen');
    const navTarjetas = document.getElementById('nav-tarjetas');
    const navExamen = document.getElementById('nav-examen');
    
    if (viewName === 'tarjetas') {
        viewTarjetas.classList.remove('d-none');
        viewExamen.classList.add('d-none');
        navTarjetas.classList.add('active');
        navExamen.classList.remove('active');
    } else {
        viewTarjetas.classList.add('d-none');
        viewExamen.classList.remove('d-none');
        navTarjetas.classList.remove('active');
        navExamen.classList.add('active');
        initExamen();
    }
}

// ============================================
// Initialize
// ============================================
function init() {
    const container = document.getElementById('flashcards-container');

    flashcards.forEach((item, index) => {
        const card = createFlashcard(item, index);
        card.classList.add('card-enter');
        container.appendChild(card);
    });

    // Event listeners
    document.getElementById('btn-theme').addEventListener('click', toggleTheme);
    document.getElementById('btn-shuffle').addEventListener('click', shuffleCards);
    document.getElementById('btn-reset').addEventListener('click', resetProgress);
    
    // View Switchers
    document.getElementById('nav-tarjetas').addEventListener('click', (e) => { e.preventDefault(); switchView('tarjetas'); });
    document.getElementById('nav-examen').addEventListener('click', (e) => { e.preventDefault(); switchView('examen'); });
    
    // Examen listeners
    document.getElementById('btn-next-examen').addEventListener('click', nextExamenCard);
    document.getElementById('btn-restart-examen').addEventListener('click', initExamen);

    // Filter buttons
    document.querySelectorAll('.filter-btn').forEach(btn => {
        btn.addEventListener('click', () => filterCards(btn.dataset.category));
    });

    // Init features
    initTheme();
    initScrollEffect();
    updateProgress();
}

document.addEventListener('DOMContentLoaded', init);
