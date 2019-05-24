package com.lpoo_32.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DoorModelTest {
    @Test
    public void interact(){
        DoorModel door = new DoorModel(null);
        assertTrue(door.interact(null));
    }
}