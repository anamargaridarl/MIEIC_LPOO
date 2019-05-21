package com.lpoo_32.model;

import com.lpoo_32.exceptions.OutOfBoundaries;
import com.lpoo_32.view.lanterna.FoodView;
import com.lpoo_32.view.lanterna.Game;
import com.lpoo_32.view.lanterna.SpikesView;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElementsTest {

    @Test
    public void gettersTest() throws OutOfBoundaries {
        Elements elements = new Elements();
        elements.addElement(new FoodView(new FoodModel(10,new Position(4,5, Game.width/4, Game.height/4, 0))));
        elements.addElement(new SpikesView(new SpikesModel(10,new Position(5,5, Game.width/4, Game.height/4, 0))));

        FoodModel element = new FoodModel(20, new Position(10, 5, Game.width/4, Game.height/4, 0));
        elements.addElement(new FoodView(element));

        assertEquals(element, elements.getModel(element.getPos()));
        assertNull(elements.getView(new Position(5, 10, Game.width/4, Game.height/4, 0)));
    }

    @Test
    public void addElements() throws OutOfBoundaries {
        Elements elements = new Elements();
        FoodModel food = new FoodModel(10,new Position(4,5, Game.width/4, Game.height/4, 0));
        SpikesModel spike = new SpikesModel(10,new Position(5,5, Game.width/4, Game.height, 0));

        elements.addElement(new FoodView(food));
        elements.addElement(new SpikesView(spike));

        assertEquals(food, elements.getModel(food.getPos()));
        assertEquals(spike, elements.getModel(spike.getPos()));
        assertNull(elements.getView(new Position(5, 10, Game.width/4, Game.width/4, 0)));
    }


}
