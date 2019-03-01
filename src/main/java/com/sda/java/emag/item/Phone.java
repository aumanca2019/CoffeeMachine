package com.sda.java.emag.item;


import java.util.Objects;

public class Phone extends Item {
    public static final Category CATEGORY = Category.ELECTRONICS;
    //brand e.g.Samsung,
    //display_size e.g.5,
    //cpu e.g. Snapdragon 855,

    private final String brand;
    private final String CPU;
    private final float display_size;


    public Phone(String name, float price, String brand, String CPU, float display_size) {
        super(CATEGORY, name, price);
        this.brand = brand;
        this.CPU = CPU;
        this.display_size = display_size;
    }

    public String getBrand() {
        return brand;
    }

    public String getCPU() {
        return CPU;
    }

    public float getDisplay_size() {
        return display_size;
    }

    @Override
    public String showDetails() {
        final StringBuilder displayResult = new StringBuilder();
        displayResult.append(getItemDetails());
        displayResult.append(SEPARATOR);
        displayResult.append(brand);
        displayResult.append(SEPARATOR);
        displayResult.append(CPU);
        displayResult.append(SEPARATOR);
        displayResult.append(display_size);
        displayResult.append(System.lineSeparator());
        return displayResult.toString();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Phone phone = (Phone) o;
        return Float.compare(phone.display_size, display_size) == 0 &&
                Objects.equals(brand, phone.brand) &&
                Objects.equals(CPU, phone.CPU);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), brand, CPU, display_size);
    }
}

