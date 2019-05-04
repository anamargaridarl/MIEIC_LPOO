package com.lpoo_32.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class InventoryTest {
    private FoodModel food;
    private FoodModel food2;
    private Inventory inventory;

    @Before
    public void initializeValues(){
        this.food = new FoodModel(23, null);
        this.food2 = new FoodModel(23, null);
        this.inventory = new Inventory();
    }

    @Test
    public void addElementTest() {
        inventory.addElement(food);

        assertNotEquals(null,inventory.getElement());
    }

    @Test
    public void MoveTest()
    {
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
        inventory.addElement(food);
        inventory.addElement(food2);

        assertEquals(food,inventory.getElement());

        inventory.moveRight();
        assertEquals(food2,inventory.getElement());
    }




}
