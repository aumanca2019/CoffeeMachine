package com.sda.java.emag.businesslogic;

import com.sda.java.emag.item.Item;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;



public class Stock implements Serializable {
    static final String SEPARATOR = " ";
    private static final long serialVersionUID = 1L;
    private Map<Item, Integer> stock;// Map este o interfata iar celelalte (LinkedHasMap, TreeMap)sunt clase care implementeaza interfata Map
    private final transient Object mutex;

    public Stock(Map<Item, Integer> stock, Object mutex) {
        this.stock = stock;
        this.mutex = mutex;
    }


    public String showItems() {
        final StringBuilder displayResult = new StringBuilder();
        for (Map.Entry<Item, Integer> itemEntry : stock.entrySet()) {
            Item item = itemEntry.getKey();
            displayResult.append(item.showDetails());
            displayResult.append(SEPARATOR);
            displayResult.append(itemEntry.getValue());
            displayResult.append(System.lineSeparator());
            System.out.println("Stoc final =  " + itemEntry.getValue());

        }
        return displayResult.toString();

    }

//    public String showItems() {
//        final StringBuilder displayResult = new StringBuilder();
//        for (Map.Entry<Item, Integer> itemEntry : items.entrySet()) {
//            final Item item = itemEntry.getKey();
//            displayResult.append(item.getName());
//            displayResult.append(SEPARATOR);
//            displayResult.append(item.getPrice());
//            displayResult.append(SEPARATOR);
//            displayResult.append(item.getCategory());
//            displayResult.append(System.lineSeparator()
//      }
//        return displayResult.toString();

//    public int addItem(Item item, int supplyQuantity) {
//
//        if (stock.containsKey(item)) {
//            final int supply = stock.get(item) + supplyQuantity;
//            stock.put(item, supply);
//            return supply;
//        }
//        stock.put(item, supplyQuantity);
//        return supplyQuantity;
//    }

    public int addItem(Item item, int supplyQuantity) {
        final Integer previousValue = stock.putIfAbsent(item, supplyQuantity);
        if (previousValue == null) {
            return supplyQuantity;
        }
        synchronized (mutex) {
            final int supply = stock.get(item) + supplyQuantity;
            stock.put(item, supply);
            return supply;
        }
    }


    public int retrieveItem(Item item, int consumeQuantity) {
        synchronized (mutex) {
            if (!stock.containsKey(item)) {
                return 0;
            }
            int currentQuantity = stock.get(item);
            if (currentQuantity < consumeQuantity) {
                stock.put(item, 0);
                return currentQuantity;
            }
            int updatedQuantity = currentQuantity - consumeQuantity;
            stock.put(item, updatedQuantity);
        }
        return consumeQuantity;

    }

    public void saveState() throws IOException {

        final FileOutputStream fileOutputStream = new FileOutputStream("State of Stock.txt");
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
        objectOutputStream.writeObject(stock);
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
            final Stock readObject;
        readObject = (Stock) objectInputStream.readObject();
        stock = readObject.stock;

            objectInputStream.close();
            fileInputStream.close();

        }
    }

//    public static Stock loadState(String path) {
//
//        //try with resources
//
//        try (
//
//                FileInputStream fileInputStream =
//
//                        new FileInputStream(path);
//
//                BufferedInputStream bufferedInputStream =
//
//                        new BufferedInputStream(fileInputStream);
//
//                ObjectInputStream objectInputStream =

//                        new ObjectInputStream(bufferedInputStream);) {
//
//            return (Stock) objectInputStream.readObject();
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//            return null;






