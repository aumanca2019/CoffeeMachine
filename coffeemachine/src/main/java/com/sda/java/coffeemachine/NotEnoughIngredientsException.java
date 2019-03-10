package com.sda.java.coffeemachine;

public class NotEnoughIngredientsException extends Exception{

    public NotEnoughIngredientsException (CoffeeType coffeeType,Ingredient ingredient){
        super("Ingredient: " + ingredient + " is not sufficient for the desired drink");
    }
}
