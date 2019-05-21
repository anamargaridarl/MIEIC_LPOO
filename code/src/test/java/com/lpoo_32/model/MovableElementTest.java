package com.lpoo_32.model;

import com.lpoo_32.exceptions.*;
import com.lpoo_32.view.lanterna.GameLanterna;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovableElementTest {

    @Test
    public void movement() throws DownScreen, UpScreen, LeftScreen, RightScreen, OutOfBoundaries {
        MovableElement player = new PlayerModel(new Position(4,4, GameLanterna.width/4, GameLanterna.height/4, 0));

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
