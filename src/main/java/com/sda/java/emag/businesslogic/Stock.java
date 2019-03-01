package com.sda.java.emag.businesslogic;

import com.sda.java.emag.item.Item;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;



public class Stock implements Serializable {
    private final long serialVersionUID = 1L;
    private static final String SEPARATOR = " ";
    private  Map<Item, Integer> items;// Map este o interfata iar celelalte (LinkedHasMap, TreeMap)sunt clase care implementeaza interfata Map

    public Stock(Map<Item, Integer> items) {
        this.items = items;

    }

    public String showItems() {
        final StringBuilder displayResult = new StringBuilder();
        for (Map.Entry<Item, Integer> itemEntry : items.entrySet()) {
            final Item item = itemEntry.getKey();
            displayResult.append(item.getName());
            displayResult.append(SEPARATOR);
            displayResult.append(item.getPrice());
            displayResult.append(SEPARATOR);
            displayResult.append(item.getCategory());
            displayResult.append(System.lineSeparator());


        }
        return displayResult.toString();


    }


    public int addItem(Item item, int supplyQuantity) {

        if (items.containsKey(item)) {
            final int supply = items.get(item) + supplyQuantity;
            items.put(item, supply);
            return supply;
        }
        items.put(item, supplyQuantity);
        return supplyQuantity;
    }


    public int retrieveItem(Item item, int consumeQuantity) {
        if (!items.containsKey(item)) {
            return 0;
        }

        int currentQuantity = items.get(item);

        if (currentQuantity < consumeQuantity) {
            items.put(item, 0);
            return currentQuantity;
        }
        int updatedQuantity = currentQuantity - consumeQuantity;
        items.put(item, updatedQuantity);
        return consumeQuantity;

    }

    public void saveState() throws IOException {

        final FileOutputStream fileOutputStream = new FileOutputStream("State of Stock.txt");
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
        fileOutputStream.close();

    }

    public void loadState() throws IOException, ClassNotFoundException {
        if (!Files.exists(Paths.get("State of Stock.txt"))) {
        return;
    }
        final FileInputStream fileInputStream = new FileInputStream("State of Stock.txt");
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        final ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
       final Stock readObject = (Stock) objectInputStream.readObject();
       items = readObject.items;
        objectInputStream.close();
        fileInputStream.close();

    }
}



