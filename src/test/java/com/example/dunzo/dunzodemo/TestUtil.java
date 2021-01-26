package com.example.dunzo.dunzodemo;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static List<Ingredient> getFullInventory(){
        List<Ingredient> response = new ArrayList<>();
        response.add(new Ingredient("milk",100));
        response.add(new Ingredient("water",100));
        response.add(new Ingredient("latte_powder",100));
        response.add(new Ingredient("sugar",100));
        response.add(new Ingredient("lemon_tea_powder",100));
        response.add(new Ingredient("green_tea_powder",100));

        return response;
    }

    public static List<Ingredient> getFullInventoryForLatte(){
        List<Ingredient> response = new ArrayList<>();
        response.add(new Ingredient("milk",100));
        response.add(new Ingredient("water",100));
        response.add(new Ingredient("latte_powder",100));
        response.add(new Ingredient("sugar",100));

        return response;
    }

    public static List<Ingredient> getHalfInventoryForLatte(){
        List<Ingredient> response = new ArrayList<>();
        response.add(new Ingredient("milk",10));
        response.add(new Ingredient("water",10));
        response.add(new Ingredient("latte_powder",10));
        response.add(new Ingredient("sugar",10));

        return response;
    }
}
