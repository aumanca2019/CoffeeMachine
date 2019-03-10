package com.sda.java.emag.item;


import java.util.Objects;

public class Shoes extends Item {

    private final String brand;
    private final int size;
    private final Colours colour;

    public Shoes(String name, float price, String brand, int size, Colours colour) {
        super(Category.FASHION, name, price);
        this.brand = brand;
        this.size = size;
        this.colour = colour;
    }

    public String getBrand() {
        return brand;
    }

    public int getSize() {
        return size;
    }

    public Colours getColour() {
        return colour;
    }

    @Override
    public String showDetails() {
        final StringBuilder displayResult = new StringBuilder();
            displayResult.append(this.getItemDetails());
            displayResult.append(SEPARATOR);
            displayResult.append(this.getBrand());
            displayResult.append(SEPARATOR);
            displayResult.append(this.getSize());
            displayResult.append(SEPARATOR);
            displayResult.append(this.getColour());
            displayResult.append(System.lineSeparator());
            return displayResult.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Shoes shoes = (Shoes) o;
        return size == shoes.size &&
                Objects.equals(brand, shoes.brand) &&
                colour == shoes.colour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), brand, size, colour);
    }

    public <T> boolean matchCase(T input) {
        if (this.getName().equals(input)) return true;
        if ((Float) this.getPrice() == input) return true;
        if (this.getCategory() == input) return true;
        if ((Integer) size == input) return true;
        if (colour == input) return true;
        return false;

    }
}

