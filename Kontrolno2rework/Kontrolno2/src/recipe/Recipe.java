package recipe;

import java.util.HashMap;
import java.util.Map;

public class Recipe {
    private String name;
    private double price;
    private Map<String, Integer> ingredients = new HashMap<>();

    public Recipe(String name, double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be a positive number.");
        }
        this.name = name;
        this.price = price;
    }

    public void addIngredient(String ingredient, int amount) {
        if (ingredients.containsKey(ingredient)) {
            throw new IllegalArgumentException("Ingredient already exists.");
        }
        ingredients.put(ingredient, amount);
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIngredients(Map<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }
}
