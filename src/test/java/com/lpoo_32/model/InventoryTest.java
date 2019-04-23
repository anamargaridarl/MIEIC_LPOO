package com.lpoo_32.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class InventoryTest {

    @Test
    public void addElementTest() {
        FoodModel food = new FoodModel(23,new Position(3,3));
        Inventory inventory = new Inventory();

        inventory.addElement(food);

        assertNotEquals(null,inventory.getElement());
    }

    @Test
    public void MoveTest()
    {
        FoodModel food = new FoodModel(23,new Position(3,3));
        FoodModel food2 = new FoodModel(20,new Position(4,3));

        Inventory inventory = new Inventory();

        inventory.addElement(food);
        inventory.addElement(food2);

        inventory.moveRight();
        assertEquals(food2,inventory.getElement());
        inventory.moveLeft();
        assertEquals(food,inventory.getElement());

    }

    @Test
    public void RemoveTest()
    {
        FoodModel food = new FoodModel(23,new Position(3,3));
        FoodModel food2 = new FoodModel(20,new Position(4,3));

        Inventory inventory = new Inventory();

        inventory.addElement(food);
        inventory.addElement(food2);

        inventory.removeElement();
        assertEquals(food2,inventory.getElement());

        inventory.addElement(food);
        inventory.moveRight();
        inventory.removeElement();

        assertEquals(food2,inventory.getElement());
    }

    @Test
    public void getElementTest()
    {
        FoodModel food = new FoodModel(23,new Position(3,3));
        FoodModel food2 = new FoodModel(20,new Position(4,3));

        Inventory inventory = new Inventory();

        inventory.addElement(food);
        inventory.addElement(food2);

        assertEquals(food,inventory.getElement());

        inventory.moveRight();
        assertEquals(food2,inventory.getElement());
    }




}
