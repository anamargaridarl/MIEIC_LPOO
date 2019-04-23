package com.lpoo_32.model;

import com.lpoo_32.exceptions.StatusOverflow;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class InventoryTest {

    @Test
    public void addElement() {
        FoodModel food = new FoodModel(23,new Position(3,3));
        Inventory inventory = new Inventory();

        inventory.addElement(food);

        assertNotEquals(null,inventory.getElement());
    }

    @Test
    public void MoveRight()
    {

    }
}
