package com.example.dunzo.dunzodemo;

public class Latte extends Drink {
    public Latte() {
        super();
        this.DRINK_NAME = "LATTE";
        this.REQUIREMENTS.add(new Ingredient("milk", 10));
        this.REQUIREMENTS.add(new Ingredient("water", 10));
        this.REQUIREMENTS.add(new Ingredient("latte_powder", 10));
        this.REQUIREMENTS.add(new Ingredient("sugar", 10));
    }
}
