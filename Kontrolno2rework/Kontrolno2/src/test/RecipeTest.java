package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import recipe.Recipe;

public class RecipeTest {
    private Recipe recipe;

    @BeforeEach
    public void setUp() {
        recipe = new Recipe("Test", 1);
    }

    @Test
    public void testInvalidPrice() {
        try {
            recipe = new Recipe("Test", -1);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Price must be a positive number.", e.getMessage());
        }
    }

    @Test
    public void testDuplicateIngredient() {
        try {
            recipe.addIngredient("coffee", 15);
            recipe.addIngredient("coffee", 20);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Ingredient already exists in the recipe.", e.getMessage());
        }
    }
}
