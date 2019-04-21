package com.lpoo_32.model;

import com.lpoo_32.exceptions.HealthOVF;
import com.lpoo_32.exceptions.NourishOVF;
import com.lpoo_32.exceptions.NourishRestored;
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
        this.player = new PlayerModel(new Position(4,4));
    }


    @Test
    public void spikes() throws HealthOVF, NourishRestored, NourishOVF {
        InteractableElement spike = new SpikesModel(20, null);
        spike.interact(player);
        assertEquals(100, player.getWater().getValue());
        assertEquals(100, player.getFood().getValue());
        assertEquals(80, player.getHealth().getValue());
    }


    @Test
    public void statusOVF() throws HealthOVF, NourishRestored, NourishOVF {
        InteractableElement spike = new SpikesModel(200, null);
        thrown.expect(HealthOVF.class);
        spike.interact(player);
    }
    @Test
    public void food() throws HealthOVF, NourishRestored, NourishOVF {
        InteractableElement spike = new SpikesModel(20, null);
        spike.interact(player);
        assertEquals(100, player.getWater().getValue());
        assertEquals(100, player.getFood().getValue());
        assertEquals(80, player.getHealth().getValue());
        InteractableElement food = new FoodModel(20, null);
        food.interact(player);
        assertEquals(100, player.getWater().getValue());
        assertEquals(100, player.getFood().getValue());
        assertEquals(100, player.getHealth().getValue());
    }

    @Test
    public void water() throws HealthOVF, NourishRestored, NourishOVF {
        this.player.setWater(new NourishStatus(70));
        InteractableElement water = new WaterModel(null);
        assertEquals(70, this.player.getWater().getValue());
        assertEquals(100, this.player.getFood().getValue());
        assertEquals(100, this.player.getHealth().getValue());
        water.interact(player);
        assertEquals(90, this.player.getWater().getValue());
        assertEquals(100, this.player.getFood().getValue());
        assertEquals(100, this.player.getHealth().getValue());
    }
}
