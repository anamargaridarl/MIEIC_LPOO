package com.lpoo_32.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ElementsTest {

    @Test
    public void gettersTest()
    {
        Elements elements = new Elements();
        elements.addElement(new FoodModel(10,new Position(4,5)));
        elements.addElement(new SpikesModel(10,new Position(5,5)));

        assertNotEquals(null,elements.getValue(new Position(4,5)));
    }
}
