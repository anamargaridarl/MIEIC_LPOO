package com.lpoo_32.model;

import com.lpoo_32.view.FoodView;
import com.lpoo_32.view.SpikesView;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElementsTest {

    @Test
    public void gettersTest()
    {
        Elements elements = new Elements();
        elements.addElement(new FoodView(new FoodModel(10,new Position(4,5, 200, 200, 0))));
        elements.addElement(new SpikesView(new SpikesModel(10,new Position(5,5, 200, 200, 0))));

        FoodModel element = new FoodModel(20, new Position(10, 5, 200, 200, 0));
        elements.addElement(new FoodView(element));

        assertEquals(element, elements.getModel(element.getPos()));
        assertNull(elements.getView(new Position(5, 10, 200, 200, 0)));
    }

    @Test
    public void addElements()
    {
        Elements elements = new Elements();
        FoodModel food = new FoodModel(10,new Position(4,5, 200, 200, 0));
        SpikesModel spike = new SpikesModel(10,new Position(5,5, 200, 200, 0));

        elements.addElement(new FoodView(food));
        elements.addElement(new SpikesView(spike));

        assertEquals(food, elements.getModel(food.getPos()));
        assertEquals(spike, elements.getModel(spike.getPos()));
        assertNull(elements.getView(new Position(5, 10, 200, 200, 0)));
    }


}
