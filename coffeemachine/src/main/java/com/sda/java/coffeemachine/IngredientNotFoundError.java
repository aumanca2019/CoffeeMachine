package com.sda.java.coffeemachine;

public class IngredientNotFoundError extends Error {
    public IngredientNotFoundError(Ingredient ingredient) {
        super("The Coffee Mashine doesn't contain the ingredient:" + ingredient);

    }
}
