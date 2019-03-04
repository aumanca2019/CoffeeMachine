package com.sda.java.emag.businesslogic;

import com.sda.java.emag.item.Item;

import java.util.HashMap;
import java.util.Map;

public class Users implements Runnable {


    private final CartController cartController;
    private final Item item;
    private int quantity;
    private static final int MILLISECONDS_TO_SECONDS_RATIO = 1000;
    private int counter;


    public Users(CartController cartController, Item item, int quantity) {
        this.cartController = cartController;
        this.item = item;
        this.quantity = quantity;
    }

    public CartController getCartController() {
        return cartController;
    }

    @Override
    public void run() {
        counter =0;
        final long startTime = System.currentTimeMillis();
        while ((counter < quantity)&& (getElapsedTimeInSeconds(startTime)<5)){
            counter += cartController.addItemToCart(item, 1);
        }
    }

    private int getElapsedTimeInSeconds(long startTime) {
        return new Long((System.currentTimeMillis() - startTime) / MILLISECONDS_TO_SECONDS_RATIO).intValue();

    }


    public int getAddedItems() {

        return counter;
    }
}

