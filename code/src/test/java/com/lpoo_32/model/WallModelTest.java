package com.lpoo_32.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class WallModelTest {
    @Test
    public void interact(){
        WallModel wall = new WallModel(null);
        assertFalse(wall.interact(null));
    }

}