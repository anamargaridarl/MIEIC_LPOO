package com.lpoo_32.controller;

import com.lpoo_32.exceptions.NourishOVF;
import com.lpoo_32.exceptions.NourishRestored;
import com.lpoo_32.model.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KeyboardTest {

    @Test
    public void testCollision() throws NourishRestored, NourishOVF {
        PlayerModel player = new PlayerModel(new Position(3,2));
        SpikesModel spike = new SpikesModel(25, new Position(3,2));
        FoodModel food = new FoodModel(10, new Position(3,3));
        Elements elements = new Elements();
        elements.addElement(spike);
        elements.addElement(food);
        Keyboard k = new Keyboard(player,elements);

        k.colisions(player.getPosition());

        assertEquals(75,player.getHealth().getValue());

        player.moveDown();
        k.colisions(player.getPosition());
        assertEquals(85,player.getHealth().getValue());
    }


}
