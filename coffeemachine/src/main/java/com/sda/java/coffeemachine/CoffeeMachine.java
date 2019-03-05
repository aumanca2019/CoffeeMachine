package com.sda.java.coffeemachine;

public class CoffeeMachine {

    private int coffeeStock = 10;

    public static void main(String[] args) {

        final CoffeeMachine coffeeMachine = new CoffeeMachine();
    }

    public Coffee prepareCoffee() throws Exception {
        if (coffeeStock > CoffeeType.ESPRESSO.getCoffeeRequired()) {
            coffeeStock -= CoffeeType.ESPRESSO.getCoffeeRequired();
            return new Espresso();

        } else {
            throw new Exception("Cannot create coffee");


        }

    }
}