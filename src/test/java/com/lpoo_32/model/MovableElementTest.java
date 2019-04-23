package com.lpoo_32.model;

import com.lpoo_32.exceptions.DownScreen;
import com.lpoo_32.exceptions.LeftScreen;
import com.lpoo_32.exceptions.RightScreen;
import com.lpoo_32.exceptions.UpScreen;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovableElementTest {

    @Test
    public void movement() throws DownScreen, UpScreen, LeftScreen, RightScreen {
        MovableElement player = new PlayerModel(new Position(4,4, 200, 200, 0));

        player.moveDown();
        assertEquals(5, player.getPosition().getY());

        player.moveUp();
        assertEquals(4, player.getPosition().getY());

        player.moveLeft();
        assertEquals(3, player.getPosition().getX());

        player.moveRight();
        assertEquals(4, player.getPosition().getX());
    }
}
