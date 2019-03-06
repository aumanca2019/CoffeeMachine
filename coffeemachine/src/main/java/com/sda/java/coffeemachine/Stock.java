package com.sda.java.coffeemachine;

public class Stock {


    private int beansStock;
    private int waterStock;
    private int sugarStock;
    private int milkStock;


    public int getBeansStock() {
        return beansStock;
    }

    public int getWaterStock() {
        return waterStock;
    }

    public int getSugarStock() {
        return sugarStock;
    }

    public int getMilkStock() {
        return milkStock;
    }

    public void removeBeansFromStock(int quantity) {
        beansStock -= quantity;

    }

    public void removeWaterFromStock(int quantity) {
        waterStock -= quantity;
    }

    public void removeSugarFromStock(int quantity) {
        sugarStock -= quantity;
    }

    public void removeMilkFromStock(int quantity) {
        milkStock -= quantity;
    }




}



