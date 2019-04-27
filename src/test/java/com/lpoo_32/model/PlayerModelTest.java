package com.lpoo_32.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PlayerModelTest {
    PlayerModel player;
    @Before
    public void initializeValues(){
        this.player = new PlayerModel(null);
    }

    @Test
    public void getterTests(){
        assertEquals(100, this.player.getFood().getValue());
        assertEquals(100, this.player.getHealth().getValue());
        assertEquals(100, this.player.getWater().getValue());
    }
}
