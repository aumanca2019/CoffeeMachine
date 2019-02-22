package com.sda.java.emag;

public abstract class Item {

    private final String name;
    private float price;
   private final String category;

    public Item(String category,String name,float price) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

