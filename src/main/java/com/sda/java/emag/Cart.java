package com.sda.java.emag;

import com.sda.java.emag.item.Item;
import com.sun.javafx.collections.MappingChange;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Cart {
    private static final String SEPARATOR = " ";

    private Map<Item, Integer> cartItems;
    private float total;


    public Cart(Map<Item, Integer> cartItems) {
        this.cartItems = cartItems;
    }

    public int addItem(Item item, int addQuantity) {
        total += addQuantity * item.getPrice();
        if (cartItems.containsKey(item)) {
            final int updateQuantity = cartItems.get(item) + addQuantity;
            cartItems.put(item, addQuantity);
            return updateQuantity;
        }
        cartItems.put(item, addQuantity);
        return addQuantity;
    }


    public int removeItem(Item item, int removeQuantity) {
        if (!cartItems.containsKey(item)) {
            return 0;
        }
        int currentQuantity = cartItems.get(item);
        if (currentQuantity <= removeQuantity) {
            cartItems.remove(item);
            total -= removeQuantity * item.getPrice();
            return currentQuantity;
        }
        int updateQuantity = currentQuantity - removeQuantity;
        cartItems.put(item, updateQuantity);
        return removeQuantity;
    }


    public Map<Item, Integer> removeAll() {
        final Map<Item, Integer> currentState = cartItems;
        cartItems = new HashMap<>();
        total = 0;
        return currentState;

    }

    public String checkout() {
        final String processedItems = getItemToString();
        removeAll();
        return processedItems;


    }

    private String getItemToString() {
        return cartItems.entrySet().stream()
                    .map(itemEntry -> {
                        final StringBuilder displayResult = new StringBuilder();
                        final Item item = itemEntry.getKey();
                        displayResult.append(item.showDetails());
                        displayResult.append(SEPARATOR);
                        displayResult.append(itemEntry.getValue());
                        return displayResult.toString();
                    })
                    .collect(Collectors.joining(System.lineSeparator()));
    }

    public void print()throws IOException {

        final FileWriter fileWriter = new FileWriter("print cart.txt");
        final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        final String processedItems = getItemToString();
        bufferedWriter.write(processedItems);
        bufferedWriter.flush();  //forced write on disk
        bufferedWriter.close();




    }
}







