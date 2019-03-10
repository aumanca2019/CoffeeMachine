package com.sda.java.emag;

import com.sda.java.emag.businesslogic.Cart;
import com.sda.java.emag.businesslogic.CartController;
import com.sda.java.emag.businesslogic.Stock;
import com.sda.java.emag.businesslogic.Users;
import com.sda.java.emag.item.Colours;
import com.sda.java.emag.item.Item;
import com.sda.java.emag.item.Phone;
import com.sda.java.emag.item.Shoes;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationEmag{
    public static final String PHONE_NAME = "X";
    public static final float PRICE = 1000f;
    public static final String BRAND = "Iphone";
    public static final String CPU = "A10";
    public static final float DISPLAY_SIZE = 5.5f;
    public static final float COMPARE_DOUBLE_DELTA = 0.01f;
    private static final int AVAILABLE_QUANTITY = 100000;
    private static final int REQUESTED_QUANTITY = AVAILABLE_QUANTITY;

    public static final String BRAND_NAME = "Adidas";
    public static final float PRICE_SHOES = 250f;
    public static final String BRAND_SHOES = "Nike";
    public static final int SIZE = 38;
    public static final String Colour ="RED";
    public static final float COMPARE_DOUBLE_DELTA2 = 0.01f;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        final Object stockMutex = new Object();
        final Stock BaneasaMall = new Stock(new ConcurrentHashMap<>(), stockMutex);
        final Stock MegaMall = new Stock(new ConcurrentHashMap<>(),stockMutex);

         BaneasaMall.loadState();
        final Phone iphoneX = new Phone(PHONE_NAME, PRICE, BRAND, CPU, DISPLAY_SIZE);
        final Shoes NikeAdidas = new Shoes(BRAND_NAME,PRICE_SHOES,BRAND_SHOES,SIZE,Colours.RED);
        //  final Phone iphoneX2 = new Phone(PHONE_NAME, PRICE, BRAND, CPU, DISPLAY_SIZE);
        BaneasaMall.addItem(iphoneX, AVAILABLE_QUANTITY);


        final Users anca = new Users(new CartController(BaneasaMall), iphoneX, REQUESTED_QUANTITY);
        final Users george = new Users(new CartController(BaneasaMall), iphoneX, REQUESTED_QUANTITY);
        final Thread ancaThread = new Thread(anca);
        final Thread georgeThread = new Thread(george);

        ancaThread.start();
        georgeThread.start();

        ancaThread.join(); //se uneste cu main ul
        georgeThread.join();

        System.out.println("Anca's retrieved items quantity: " + anca.getRetrievedItemsQuantity());
        System.out.println("George's retrieved items quantity: " + george.getRetrievedItemsQuantity());
        System.out.println("Total items retrieved: " + (anca.getRetrievedItemsQuantity() + george.getRetrievedItemsQuantity()));
        System.out.println("Loaded items: " + BaneasaMall.showItems());

        useStock(BaneasaMall,iphoneX);
        useCart(iphoneX,NikeAdidas);
        BaneasaMall.saveState();
    }

    final Shoes NikeAdidas = new Shoes(BRAND_NAME,PRICE_SHOES,BRAND_SHOES,SIZE,Colours.RED);

        private static void useStock (Stock BaneasaMall, Phone iphoneX) {
            final int supply_quantity = 10;
            final int consume_quantity = 3;
            final int currentIphoneSrock = BaneasaMall.addItem(iphoneX, supply_quantity);
            System.out.println("Initial Iphone X stock quantity: " + currentIphoneSrock);

            int retrievedIphoneStock = BaneasaMall.retrieveItem(iphoneX, consume_quantity);
            System.out.println("Received Iphone X quantity1: " + retrievedIphoneStock);
            retrievedIphoneStock = BaneasaMall.retrieveItem(iphoneX, consume_quantity);
            System.out.println("Received Iphone X quantity2: " + retrievedIphoneStock);
            System.out.println( BaneasaMall.showItems());

        }


            private static void useCart(Phone iphoneX, Shoes NikeAdidas) {
                final Cart cart = new Cart(new HashMap<>());
                cart.addItem(iphoneX,20);
                cart.addItem(NikeAdidas,100);
                try {
                    cart.print();
                } catch (IOException e) {
                    System.out.println("Cannot access file during print. " + e.getMessage());
                }
        }
}
