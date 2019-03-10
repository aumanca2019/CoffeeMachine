package com.sda.java.coffeemachine;

public enum CoffeeType {
    ESPRESSO(15,50,0,0),
    LATTE(10,100,5,100),
    FILTERCOFFEE(10,200,2,0);

    private final int coffeeRequired;
    private final int waterRequired;
    private final int sugarRequired;
    private final int milkRequired;

    CoffeeType(int coffee, int water, int sugar, int milk) {
        this.coffeeRequired = coffee;
        this.waterRequired = water;
        this.sugarRequired = sugar;
        this.milkRequired = milk;
    }

    public int getCoffeeRequired() {
        return coffeeRequired;
    }

    public int getWaterRequired() {
        return waterRequired;
    }

    public int getSugarRequired() {
        return sugarRequired;
    }

    public int getMilkRequired() {
        return milkRequired;
    }


}

