package com.lpoo_32.model;

import com.lpoo_32.exceptions.OutOfBoundaries;
import com.lpoo_32.view.lanterna.FoodViewLanterna;
import com.lpoo_32.view.lanterna.GameLanterna;
import com.lpoo_32.view.lanterna.SpikesViewLanterna;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElementsTest {

    @Test
    public void gettersTest() throws OutOfBoundaries {
        Elements elements = new Elements();
        elements.addElement(new FoodViewLanterna(new FoodModel(10,new Position(4,5, GameLanterna.width/4, GameLanterna.height/4, 0))));
        elements.addElement(new SpikesViewLanterna(new SpikesModel(10,new Position(5,5, GameLanterna.width/4, GameLanterna.height/4, 0))));

        FoodModel element = new FoodModel(20, new Position(10, 5, GameLanterna.width/4, GameLanterna.height/4, 0));
        elements.addElement(new FoodViewLanterna(element));

        assertEquals(element, elements.getModel(element.getPos()));
        assertNull(elements.getView(new Position(5, 10, GameLanterna.width/4, GameLanterna.height/4, 0)));
    }

    @Test
    public void addElements() throws OutOfBoundaries {
        Elements elements = new Elements();
        FoodModel food = new FoodModel(10,new Position(4,5, GameLanterna.width/4, GameLanterna.height/4, 0));
        SpikesModel spike = new SpikesModel(10,new Position(5,5, GameLanterna.width/4, GameLanterna.height, 0));

        elements.addElement(new FoodViewLanterna(food));
        elements.addElement(new SpikesViewLanterna(spike));

        assertEquals(food, elements.getModel(food.getPos()));
        assertEquals(spike, elements.getModel(spike.getPos()));
        assertNull(elements.getView(new Position(5, 10, GameLanterna.width/4, GameLanterna.width/4, 0)));
    }


}
