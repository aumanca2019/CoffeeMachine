package com.sda.java.emag.businesslogic;

import com.sda.java.emag.item.Item;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CartController {
    private final Cart cart = new Cart(new LinkedHashMap<>());  //only managedby the controller
    private final Stock stock; // can be updated by a supplier, therefor the instance is injected

    public CartController(Stock stock) {
        this.stock = stock;
    }

    public int addItemToCart(Item item, int quantity) {
        final int itemToBuy = stock.retrieveItem(item, quantity);
         cart.addItem(item, itemToBuy);
         return itemToBuy;
        }



    public int removeItemFromCart(Item item, int itemToBuy) {
        final int quantity1 = stock.addItem(item, itemToBuy);
        return cart.removeItem(item, quantity1);
    }

    public int removeAllCartItems() {
        final Map<Item, Integer> removeItems = cart.removeAll();
        removeItems.entrySet().size();
        for (Map.Entry<Item, Integer> entry : removeItems.entrySet()) {
            stock.addItem(entry.getKey(), entry.getValue());

        }
        return removeItems.entrySet().size();


    }

    public int removeAllCartItemsWithLambda() {
        final Map<Item, Integer> removeItems = cart.removeAll();
        removeItems.forEach(stock::addItem); // Not functional oriented programming
//        for(Map.Entry<Item, Integer> entry : removeItems.entrySet()){
//            stock.addItem(entry.getKey(),entry.getValue());


        return removeItems.entrySet().size();


    }
}

