package com.lpoo_32.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerModelTest {
    PlayerModel player;
    @Before
    public void initializeValues(){
        this.player = new PlayerModel(new Position(4,4, 200, 200));
    }

    @Test
    public void getterTests(){
        assertEquals(100, this.player.getFood().getValue());
        assertEquals(100, this.player.getHealth().getValue());
        assertEquals(100, this.player.getWater().getValue());
    }
}
