package com.sda.java.emag;

import com.sda.java.emag.businesslogic.Cart;
import com.sda.java.emag.businesslogic.Stock;
import com.sda.java.emag.item.Colours;
import com.sda.java.emag.item.Phone;
import com.sda.java.emag.item.Shoes;


import java.io.IOException;
import java.util.HashMap;

public class ApplicationEmag {
    public static final String PHONE_NAME = "X";
    public static final float PRICE = 1000f;
    public static final String BRAND = "Iphone";
    public static final String CPU = "A10";
    public static final float DISPLAY_SIZE = 5.5f;
    public static final float COMPARE_DOUBLE_DELTA = 0.01f;

    public static final String BRAND_NAME = "Adidas";
    public static final float PRICE_SHOES = 250f;
    public static final String BRAND_SHOES = "Nike";
    public static final int SIZE = 38;
    public static final String Colour ="RED";
    public static final float COMPARE_DOUBLE_DELTA2 = 0.01f;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Stock BaneasaMall = new Stock(new HashMap<>());

        BaneasaMall.loadState();
        System.out.println("Loaded items: " + BaneasaMall.showItems());
        final Phone iphoneX = new Phone(PHONE_NAME, PRICE, BRAND, CPU, DISPLAY_SIZE);
        final Phone iphoneX2 = new Phone(PHONE_NAME, PRICE, BRAND, CPU, DISPLAY_SIZE);


        final Cart cart = new Cart(new HashMap<>());

        final int supply_quantity = 10;
        final int consume_quantity = 3;

        final Stock MegaMall = new Stock(new HashMap<>());
        final Shoes NikeAdidas = new Shoes(BRAND_NAME,PRICE_SHOES,BRAND_SHOES,SIZE,Colours.RED);



        final int currentIphoneSrock = BaneasaMall.addItem(iphoneX,supply_quantity);
        System.out.println("Initial Iphone X stock quantity: " + currentIphoneSrock);


        int retrievedIphoneStock = BaneasaMall.retrieveItem(iphoneX,consume_quantity);
        System.out.println("Received Iphone X quantity1: " + retrievedIphoneStock);

        retrievedIphoneStock = BaneasaMall.retrieveItem(iphoneX,consume_quantity);
        System.out.println("Received Iphone X quantity2: " + retrievedIphoneStock);

        System.out.println(BaneasaMall.showItems());

        cart.addItem(iphoneX,20);
        cart.addItem(NikeAdidas,100);
        cart.print();

        BaneasaMall.saveState();


    }
}
