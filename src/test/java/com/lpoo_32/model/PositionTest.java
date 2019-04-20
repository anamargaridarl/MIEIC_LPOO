package com.lpoo_32.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {
    private  Position position;

    @Before
    public void initalizePosition(){
        this.position = new Position(10, 10);
    }
    @Test
    public void testMovements(){
        this.position.moveDown();
        assertEquals(11, this.position.getY());
        this.position.moveUp();
        assertEquals(10, this.position.getY());
        this.position.moveRight();
        assertEquals(11, this.position.getX());
        this.position.moveLeft();
        assertEquals(10, this.position.getX());
    }

    @Test
    public void terminalPosition(){
        assertEquals(10, this.position.getTerminalPosition().getColumn());
        assertEquals(10, this.position.getTerminalPosition().getRow());
    }

}