const { test, expect } = require('@playwright/test');

test.describe('Flashcards Química', () => {
    test.beforeEach(async ({ page }) => {
        await page.goto('/');
    });

    test('page loads without errors', async ({ page }) => {
        const errors = [];
        page.on('console', msg => {
            if (msg.type() === 'error') errors.push(msg.text());
        });
        await page.waitForLoadState('networkidle');
        expect(errors).toHaveLength(0);
    });

    test('displays all flashcards', async ({ page }) => {
        const cards = page.locator('.card');
        await expect(cards).toHaveCount(15);
    });

    test('card flips on click', async ({ page }) => {
        const firstCard = page.locator('.card').first();
        await expect(firstCard.locator('.front')).toBeVisible();
        await firstCard.click();
        await expect(firstCard.locator('.back')).toBeVisible();
    });

    test('title is visible', async ({ page }) => {
        await expect(page.locator('h1')).toContainText('Flashcards');
    });
});