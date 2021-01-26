package com.example.dunzo.dunzodemo;

public class LemonTea extends Drink {
    public LemonTea() {
        super();
        this.DRINK_NAME = "LEMON_TEA";
        this.REQUIREMENTS.add(new Ingredient("milk", 10));
        this.REQUIREMENTS.add(new Ingredient("water", 10));
        this.REQUIREMENTS.add(new Ingredient("lemon_tea_powder", 10));
        this.REQUIREMENTS.add(new Ingredient("sugar", 10));
    }
}
