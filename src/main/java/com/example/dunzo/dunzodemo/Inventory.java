package com.example.dunzo.dunzodemo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    public final Map<String, Integer> map = new ConcurrentHashMap<>();

    public boolean extractIngredients(String drinkName,List<Ingredient> ingredientList) {
        synchronized (this) {
            try {
                Thread.sleep(5000);  //Simulate some arbitrary work being done
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }

            boolean valid = true;
            for (Ingredient ingredient : ingredientList) {
                if (!(map.containsKey(ingredient.getName()) && map.get(ingredient.getName()) >= ingredient.getQuantity())) {
                    System.out.println(drinkName + " cannot be prepared due to insufficient quantity of the ingredient " + ingredient.getName());
                    valid = false;
                    break;
                }
            }

            if (!valid) {
                return false;
            } else {
                for (Ingredient ingredient : ingredientList) {
                    Integer curr = map.get(ingredient.getName());
                    map.put(ingredient.getName(), curr - ingredient.getQuantity());
                }
                System.out.println(drinkName + " has been prepared.");
                return true;
            }
        }
    }
}
