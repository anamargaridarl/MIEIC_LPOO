package com.lpoo_32.model;

import com.lpoo_32.view.lanterna.FoodViewLanterna;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class InventoryTest {
    private FoodViewLanterna food;
    private FoodModel foodM;
    private FoodViewLanterna food2;
    private FoodModel foodM2;
    private Inventory inventory;

    @Before
    public void initializeValues(){
        this.foodM = new FoodModel(23, null);
        this.food = new FoodViewLanterna(foodM);
        this.foodM2 = new FoodModel(20, null);
        this.food2 = new FoodViewLanterna(foodM2);
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
        assertEquals(food2,inventory.getView());
        inventory.moveLeft();
        assertEquals(food,inventory.getView());

    }

    @Test
    public void RemoveTest()
    {
        inventory.addElement(food);
        inventory.addElement(food2);

        inventory.removeElement();
        assertEquals(food,inventory.getView());

        inventory.addElement(food2);
        inventory.moveLeft();
        inventory.removeElement();

        assertEquals(food2,inventory.getView());
    }

    @Test
    public void getElementTest()
    {
        inventory.addElement(food);
        inventory.addElement(food2);

        assertEquals(foodM2,inventory.getElement());

        inventory.moveLeft();
        assertEquals(foodM,inventory.getElement());
    }




}
