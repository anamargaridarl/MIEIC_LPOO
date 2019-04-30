package com.lpoo_32.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CatchableElementTest {

    @Test
    public void getValueTest()
    {
        FoodModel food = new FoodModel(23,new Position(3,3));
        FoodModel food2 = new FoodModel(20,new Position(4,3));

        assertEquals(23,food.getValue());
        assertEquals(20,food2.getValue());
    }
}
