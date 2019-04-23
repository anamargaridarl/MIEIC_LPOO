package com.lpoo_32.controller;

import com.lpoo_32.model.*;

import com.lpoo_32.view.ElementView;
import com.lpoo_32.view.FoodView;
import com.lpoo_32.view.PlayerView;
import com.lpoo_32.view.SpikesView;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KeyboardTest {

    @Test
    public void testCollision()
    {
        PlayerModel player = new PlayerModel(new Position(3,2));
        SpikesModel spike = new SpikesModel(25, new Position(3,2));
        FoodModel food = new FoodModel(10, new Position(3,3));

        Elements elements = new Elements();
        elements.addElement(spike);
        elements.addElement(food);

        List<ElementView> props = new ArrayList<>();
        props.add(new FoodView(food));
        props.add(new SpikesView(spike));
        props.add(new PlayerView(player));


        Keyboard k = new Keyboard(player,elements,props);

        k.collisions(player.getPosition());

        assertEquals(75,player.getHealth().getValue());

        player.moveDown();
        k.collisions(player.getPosition());
        assertEquals(75,player.getHealth().getValue());
    }


}
