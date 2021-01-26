package com.example.dunzo.dunzodemo;

public class GreenTea extends Drink {
    public GreenTea() {
        super();
        this.DRINK_NAME = "GREEN_TEA";
        this.REQUIREMENTS.add(new Ingredient("milk", 10));
        this.REQUIREMENTS.add(new Ingredient("water", 10));
        this.REQUIREMENTS.add(new Ingredient("green_tea_powder", 10));
        this.REQUIREMENTS.add(new Ingredient("sugar", 10));
    }
}