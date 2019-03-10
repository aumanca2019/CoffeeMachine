package com.sda.java.coffeemachine;

import com.sda.java.coffeemachine.menu.Espresso;
import com.sda.java.coffeemachine.menu.FilterCoffee;
import com.sda.java.coffeemachine.menu.Latte;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoffeeMachine {
    private final List<CoffeeMashineUse> coffeeLog = new ArrayList<>();


    private Stock stock = new Stock();
    private CoffeeType coffeeType = CoffeeType.FILTERCOFFEE;

    public static void main(String[] args) throws Exception {
        final CoffeeMachine coffeeMachine = new CoffeeMachine();
        Stock stock = coffeeMachine.getStock();
        stock.addToStock(Ingredient.BEANS,500);
        stock.addToStock(Ingredient.WATER,1000);
        stock.addToStock(Ingredient.SUGAR,500);
        stock.addToStock(Ingredient.MILK,500);

        coffeeMachine.chooseCoffee(CoffeeType.ESPRESSO);
        try {
            final Coffee coffee1 = coffeeMachine.prepareCoffee();
            System.out.println("My coffee1 is: " + coffee1);
        }catch (NotEnoughIngredientsException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Ingredients remains: " + stock.toString());


        coffeeMachine.chooseCoffee(CoffeeType.LATTE);
        try {
            final Coffee coffee2 = coffeeMachine.prepareCoffee();
            System.out.println("My coffee2 is: " + coffee2);
        }catch (NotEnoughIngredientsException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Ingredients remains: " + stock.toString());


        coffeeMachine.chooseCoffee(CoffeeType.FILTERCOFFEE);
        try {
            final Coffee coffee3 = coffeeMachine.prepareCoffee();
            System.out.println("My coffee3 is: " + coffee3);
        }catch(NotEnoughIngredientsException e){
            System.out.println(e.getMessage());

        }
        System.out.println("Ingredients remains: " + stock.toString());

        String myLog = coffeeMachine.swowLog();
        System.out.println("History: " + myLog);
        Files.write(Paths.get("Print_history.txt"), myLog.getBytes());



    }

    public Stock getStock() {
        return stock;
    }

    public void chooseCoffee(CoffeeType coffeeType) {
        //TODO
        this.coffeeType = coffeeType;


    }

    public Coffee prepareCoffee() throws Exception {
        validateStock();
            stock.removeFromStock(Ingredient.BEANS,coffeeType.getCoffeeRequired());
            stock.removeFromStock(Ingredient.WATER,coffeeType.getWaterRequired());
            stock.removeFromStock(Ingredient.SUGAR,coffeeType.getSugarRequired());
            stock.removeFromStock(Ingredient.MILK,coffeeType.getMilkRequired());
        Coffee coffeel = createCoffee();

        coffeeLog.add(new CoffeeMashineUse(coffeel,new Date()));
        return coffeel;




        }

        private void validateStock() throws Exception {
        if (!(stock.getIngredient(Ingredient.BEANS) >= coffeeType.getCoffeeRequired())){
            throw new NotEnoughIngredientsException(coffeeType,Ingredient.BEANS);
        }
        if(!(stock.getIngredient(Ingredient.WATER)>=coffeeType.getWaterRequired())){
            throw new NotEnoughIngredientsException(coffeeType,Ingredient.WATER);
        }
        if(!(stock.getIngredient(Ingredient.SUGAR)>=coffeeType.getSugarRequired())){
            throw new NotEnoughIngredientsException(coffeeType,Ingredient.SUGAR);
                        }
        if(!(stock.getIngredient(Ingredient.MILK)>coffeeType.getMilkRequired())){
            throw new NotEnoughIngredientsException(coffeeType,Ingredient.MILK);
        }
    }


    private Coffee createCoffee() {
        switch (coffeeType){
            case LATTE:
                return new Latte();
            case ESPRESSO:
                return new Espresso();
            case FILTERCOFFEE:
                return new FilterCoffee();
                default:
                    return new FilterCoffee();

        }
    }
    private String swowLog() {
        StringBuilder sb = new StringBuilder();

        coffeeLog.forEach(logEntry -> sb.append(logEntry).append(System.lineSeparator()));
        return sb.toString();


//        final FileInputStream fileInputStream = new FileInputStream("Coffee log history");
//        final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
//        final ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
//        final Stock readObject;
//        readObject = (Stock) objectInputStream.readObject();
//        stock = readObject.stock;
//
//        objectInputStream.close();
//        fileInputStream.close();


    }


}
