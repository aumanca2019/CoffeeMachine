package com.sda.java.emag.item;


import com.sda.java.emag.item.Phone;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PhoneTest {

    public static final String PHONE_NAME = "X";
    public static final float PRICE = 1000f;
    public static final String BRAND = "Iphone";
    public static final String CPU = "A10";
    public static final float DISPLAY_SIZE = 5.5f;
    public static final float COMPARE_DOUBLE_DELTA = 0.01f;


    @Test
    public void itCreatesAPhone(){
        //Given

        //When
       final Phone iphone = new Phone (PHONE_NAME, PRICE, BRAND, CPU, DISPLAY_SIZE);



        //theN assertNotNull(iphone);
        assertEquals(PHONE_NAME,iphone.getName());
        assertEquals(PRICE,iphone.getPrice(), COMPARE_DOUBLE_DELTA);
        assertEquals(BRAND,iphone.getBrand());
        assertEquals(CPU, iphone.getCPU());
        assertEquals(DISPLAY_SIZE,iphone.getDisplay_size(),COMPARE_DOUBLE_DELTA);
        assertEquals(Phone.CATEGORY, iphone.getCategory());



    }

}