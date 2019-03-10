package com.sda.java.coffeemachine;

import java.util.HashMap;
import java.util.Map;

public class Stock {
    private Map<Ingredient, Integer> ingredients = new HashMap<>();

//    public Stock() {
//        for (Ingredient eachingredient : Ingredient.values()) {
//            ingredients.put(eachingredient, 0);
//        }

        public Stock(){
            for( int i = 0;i<Ingredient.values().length;i++){
                ingredients.put(Ingredient.values()[i],0);
            }
        }



    public int getIngredient(Ingredient ingredient) {
        if (!ingredients.containsKey(ingredient)) {
            throw new IngredientNotFoundError (ingredient);
        }
        return ingredients.get(ingredient);



        }

        public void addToStock (Ingredient ingredient,int quantity){
//            if (!ingredients.containsKey(ingredient)) {
//                throw new Exception("404 Ingredient not found: " + ingredient);
//            }
            int currentQuantity = getIngredient(ingredient);
            int updatedQuantity = currentQuantity + quantity;

            ingredients.put(ingredient,updatedQuantity);
            }



        public void removeFromStock (Ingredient ingredient,int quantity) throws Exception {
//        if (!ingredients.containsKey(ingredient)) {
//            throw new Exception("404 Ingredient not found: " + ingredient);
//
//        }
        int currentQuantity = getIngredient(ingredient);
        int updatedQuantity = currentQuantity - quantity;

        ingredients.put(ingredient,updatedQuantity);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "ingredients=" + ingredients +
                '}';
    }
}




