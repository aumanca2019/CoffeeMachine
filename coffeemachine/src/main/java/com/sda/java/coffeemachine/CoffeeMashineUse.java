package com.sda.java.coffeemachine;

import java.util.Date;

public class CoffeeMashineUse {
    private final Coffee preparadCoffee;
    private final Date preparationDate;


    public CoffeeMashineUse(Coffee preparadCoffee, Date preparationDate) {
        this.preparadCoffee = preparadCoffee;
        this.preparationDate = preparationDate;
    }

    @Override
    public String toString() {
        return "CoffeeMashineUse{" +
                "preparadCoffee=" + preparadCoffee +
                ", preparationDate=" + preparationDate +
                '}';
    }
}

