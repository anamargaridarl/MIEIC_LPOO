package com.lpoo_32.model;

import com.googlecode.lanterna.TerminalSize;
import com.lpoo_32.view.ScreenSize;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PositionTest {
    private  Position position;

    @Before
    public void initalizePosition(){
        this.position = new Position(10, 10, 12, 12);
        TerminalSize size = Mockito.mock(TerminalSize.class);
        Mockito.when(size.getColumns()).thenReturn(100);
        Mockito.when(size.getRows()).thenReturn(100);
        ScreenSize.createInstance(size);
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
        this.position.moveRight();
        this.position.moveRight();
        assertEquals(12, this.position.getX());
        this.position.moveRight();
        assertEquals(12, this.position.getX());
        this.position.moveDown();
        this.position.moveDown();
        assertEquals(12, this.position.getX());
        this.position.moveDown();
        assertEquals(12, this.position.getX());
    }

    @Test
    public void terminalPosition(){
        assertEquals(19, this.position.getTerminalPosition().getColumn());
        assertEquals(19, this.position.getTerminalPosition().getRow());
    }

}