package com.lpoo_32.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ElementsTest {

    @Test
    public void gettersTest()
    {
        Elements elements = new Elements();
        elements.addElement(new FoodModel(10,new Position(4,5, 200, 200)));
        elements.addElement(new SpikesModel(10,new Position(5,5, 200, 200)));

        InteractableElement element = new FoodModel(20, new Position(10, 5, 200, 200));
        elements.addElement(element);

        assertEquals(element, elements.getValue(element.getPos()));
        assertNull(elements.getValue(new Position(5, 10, 200, 200)));
    }

    @Test
    public void addElements()
    {
        Elements elements = new Elements();
        InteractableElement food = new FoodModel(10,new Position(4,5, 200, 200));
        InteractableElement spike = new SpikesModel(10,new Position(5,5, 200, 200));

        elements.addElement(food);
        elements.addElement(spike);

        assertEquals(food, elements.getValue(food.getPos()));
        assertEquals(spike, elements.getValue(spike.getPos()));
        assertNull(elements.getValue(new Position(5, 10, 200, 200)));
    }


}
