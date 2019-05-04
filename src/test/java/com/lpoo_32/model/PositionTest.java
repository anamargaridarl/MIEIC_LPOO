package com.lpoo_32.model;

import com.googlecode.lanterna.TerminalSize;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.view.ScreenSize;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PositionTest {
    private  Position position;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void initializePosition() throws OutOfBoundaries {
        this.position = new Position(1, 1, 2, 2, 0);
        TerminalSize size = Mockito.mock(TerminalSize.class);
        Mockito.when(size.getColumns()).thenReturn(100);
        Mockito.when(size.getRows()).thenReturn(100);
        ScreenSize.createInstance(size);
    }
    @Test
    public void testMovements() throws DownScreen, RightScreen, LeftScreen, UpScreen {
        this.position.moveDown();
        assertEquals(2, this.position.getY());
        this.position.moveUp();
        assertEquals(1, this.position.getY());
        this.position.moveRight();
        assertEquals(2, this.position.getX());
        this.position.moveLeft();
        assertEquals(1, this.position.getX());
    }

    @Test
    public void rightScreenException() throws RightScreen {
        thrown.expect(RightScreen.class);
        this.position.moveRight();
        this.position.moveRight();
    }

    @Test
    public void leftScreenException() throws LeftScreen {
        thrown.expect(LeftScreen.class);
        this.position.moveLeft();
        this.position.moveLeft();
    }

    @Test
    public void downScreenException() throws DownScreen {
        thrown.expect(DownScreen.class);
        this.position.moveDown();
        this.position.moveDown();
    }

    @Test
    public void upScreenException() throws UpScreen {
        thrown.expect(UpScreen.class);
        this.position.moveUp();
        this.position.moveUp();
    }

}