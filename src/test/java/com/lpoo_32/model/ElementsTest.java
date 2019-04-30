package com.lpoo_32.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ElementsTest {

    @Test
    public void gettersTest()
    {
        Elements elements = new Elements();
        elements.addElement(new FoodModel(10,new Position(4,5, 200, 200, 0)));
        elements.addElement(new SpikesModel(10,new Position(5,5, 200, 200, 0)));

        InteractableElement element = new FoodModel(20, new Position(10, 5, 200, 200, 0));
        elements.addElement(element);

        assertEquals(element, elements.getView(element.getPos()));
        assertNull(elements.getView(new Position(5, 10, 200, 200, 0)));
    }

    @Test
    public void addElements()
    {
        Elements elements = new Elements();
        InteractableElement food = new FoodModel(10,new Position(4,5, 200, 200, 0));
        InteractableElement spike = new SpikesModel(10,new Position(5,5, 200, 200, 0));

        elements.addElement(food);
        elements.addElement(spike);

        assertEquals(food, elements.getView(food.getPos()));
        assertEquals(spike, elements.getView(spike.getPos()));
        assertNull(elements.getView(new Position(5, 10, 200, 200, 0)));
    }


}
