package vendingmachine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import recipe.Recipe;

public class SmartCoffeeVendingMachine extends CoffeeVendingMashine {

    public SmartCoffeeVendingMachine(Collection<String> ingredients) {
        super(ingredients);
        //TODO Auto-generated constructor stub
    }
    
    @Override
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
            if (ingredients.get(ingredient) < CONTAINER_CAPACITY * 0.2) {
                notifySupport(ingredient);
            }
            ingredients.put(ingredient, ingredients.get(ingredient) - amount);
        }
        turnover += recipe.getPrice();
    }

    public void notifySupport(String ingredient) {
        try {
            File file = new File("notifications.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            String message = "Ingredient " + ingredient + " is almost over. Current amount: " + ingredients.get(ingredient);
            fw.write(message);
            fw.write(System.getProperty("line.separator"));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
