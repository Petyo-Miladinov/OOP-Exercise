package vendingmachine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import recipe.Recipe;

public class CoffeeVendingMashine {
    protected Map<String, Integer> ingredients;
    protected double turnover;
    protected static final int CONTAINER_CAPACITY = 5000;

    public CoffeeVendingMashine(Collection<String> ingredients) {
        this.ingredients = new HashMap<>();
        for (String ingredient : ingredients) {
            this.ingredients.put(ingredient, CONTAINER_CAPACITY);
        }
    }

    public void resupplyContainer(String ingredient) {
        if (!ingredients.containsKey(ingredient)) {
            throw new RuntimeException("Vending machine does not have container for this ingredient.");
        }
        ingredients.put(ingredient, CONTAINER_CAPACITY);
    }

    public void resupply() {
        for (Map.Entry<String, Integer> entry : ingredients.entrySet()) {
            entry.setValue(CONTAINER_CAPACITY);
        }
        turnover = 0;
    }

    public int getIngredientSupply(String ingredient) {
        if (!ingredients.containsKey(ingredient)) {
            throw new RuntimeException("Vending machine does not have container for this ingredient.");
        }
        return ingredients.get(ingredient);
    }

    public void useIngredient(String ingredient, int amount) {
        if (!ingredients.containsKey(ingredient)) {
            throw new RuntimeException("Vending machine does not have container for this ingredient.");
        }
        if (ingredients.get(ingredient) < amount) {
            throw new IllegalArgumentException("Insufficient ingredient stock.");
        }
        ingredients.put(ingredient, ingredients.get(ingredient) - amount);
    }

    public void brewRecipe(Recipe recipe) {
        for (Map.Entry<String, Integer> entry : recipe.getIngredients().entrySet()) {
            String ingredient = entry.getKey();
            int amount = entry.getValue();
            if (!ingredients.containsKey(ingredient)) {
                throw new RuntimeException("Vending machine does not have container for this ingredient.");
            }
            if (ingredients.get(ingredient) < amount) {
                throw new IllegalArgumentException("Insufficient ingredient stock.");
            }
            ingredients.put(ingredient, ingredients.get(ingredient) - amount);
        }  
        turnover += recipe.getPrice();
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public static int getContainerCapacity() {
        return CONTAINER_CAPACITY;
    }
}
