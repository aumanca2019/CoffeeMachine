package com.sda.java.emag.businesslogic;

import com.sda.java.emag.item.Item;

public class Users implements Runnable {


    private static final int MILLISECONDS_TO_SECONDS_RATIO = 1000;
    private static final int RUN_TIME_SECONDS = 5;
    public static  int MAX_QUANTITY_PER_REQUEST = 1;
    private final CartController cartController;
    private final Item requestedItem;
    private int requestedQuantity;
    private int retrievedItemsQuantity;


    public Users(CartController cartController, Item item, int requestedQuantity) {
        this.cartController = cartController;
        this.requestedItem = item;
        this.requestedQuantity= requestedQuantity;
    }

    public CartController getCartController() {
        return cartController;
    }


    @Override
    public void run() {
        retrievedItemsQuantity = 0;
        final long startTime = System.currentTimeMillis();
        while ((retrievedItemsQuantity < requestedQuantity) && (getElapsedTimeInSeconds(startTime) < RUN_TIME_SECONDS)) {
            retrievedItemsQuantity += cartController.addItemToCart(requestedItem, MAX_QUANTITY_PER_REQUEST);
        }
    }

    private int getElapsedTimeInSeconds(long startTime) {
        return new Long((System.currentTimeMillis() - startTime) / MILLISECONDS_TO_SECONDS_RATIO).intValue();

    }

    public int getRetrievedItemsQuantity() {
        return retrievedItemsQuantity;
    }



}


