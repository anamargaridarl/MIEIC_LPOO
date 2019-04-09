package com.lpoo_32.logic;

import com.lpoo_32.exceptions.StatusOverflow;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;


public class InteractableElementTest {
    private PlayerModel player;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void initializeValues(){
        this.player = new PlayerModel();
    }


    @Test
    public void spikes() throws StatusOverflow {
        InteractableElement spike = new Spikes(20);
        spike.interact(player);
        assertEquals(80, player.getWater().getValue());
        assertEquals(80, player.getFood().getValue());
        assertEquals(80, player.getHealth().getValue());
    }


    @Test
    public void statusOVF() throws StatusOverflow {
        InteractableElement spike = new Spikes(200);
        thrown.expect(StatusOverflow.class);
        spike.interact(player);
    }
    @Test
    public void food() throws StatusOverflow {
        InteractableElement spike = new Spikes(20);
        spike.interact(player);
        assertEquals(80, player.getWater().getValue());
        assertEquals(80, player.getFood().getValue());
        assertEquals(80, player.getHealth().getValue());
        InteractableElement food = new Food(20);
        food.interact(player);
        assertEquals(100, player.getWater().getValue());
        assertEquals(100, player.getFood().getValue());
        assertEquals(100, player.getHealth().getValue());
    }
}
