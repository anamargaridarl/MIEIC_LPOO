package com.lpoo_32.model;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.view.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.*;

public class MonsterTest {

    private PlayerModel player;
    private GameController gameController;
    private MonsterModel monster;
    private MonsterView monsterview;
    private Elements elements;


    @Before
    public void init() throws OutOfBoundaries {
        Position positionmonster = new Position(5, 5, Game.width / 4, Game.height / 4, 0);
        elements = new Elements();
        player = new PlayerModel(new Position(4, 5, Game.width / 4, Game.height / 4, 0));
        gameController = new GameController(elements, player, Mockito.mock(Game.class));
        monster = new MonsterModel(positionmonster, 40, new MovableElement(positionmonster, ""), gameController, player.getPosition());
        monsterview = new MonsterView(monster);
        elements.addElement(monsterview);
    }

    @Test
    public void interact() throws OutOfBoundaries, HungerOVF, HealthOVF, ThirstOVF {
        monster.interact(player);
        assertEquals(60, player.getHealth().getValue());
    }


    @Test
    public void getMovement() throws HungerOVF, ThirstOVF, ThirstRestored, RightScreen, DownScreen, LeftScreen, HealthOVF, HungerRestored, UpScreen, OutOfBoundaries {

        Position newp = new Position(5, 4, Game.width / 4, Game.height / 4, 0);
        Position oldp = new Position(5, 5, Game.width / 4, Game.height / 4, 0);
        boolean t;


        InteractableElementView waterView = new WaterView(new WaterModel(20, new Position(5,4,Game.width / 4, Game.height / 4, 0)));
        elements.addElement(waterView);
         t = monster.getMovement(Movements.UP,monsterview);
        assertFalse(t);

        elements.removeElement(waterView.getElement());
        assertNull(elements.getView(waterView.getElement().getPos()));
        t = monster.getMovement(Movements.UP, monsterview);
        assertTrue(t);

        assertEquals(newp.getY(),monster.getPos().getY());
        assertEquals(newp.getX(),monster.getPos().getX());

        assertNotNull(elements.getView(newp));
        assertNull(elements.getView(oldp));

    }

    @Test
    public void decreaseValue()  {
        WeaponModel weapon = new WeaponModel(player.getPosition(),20);

        monster.decreaseValue(weapon.getValue());
        assertEquals(30, monster.getHealth());

        monster.decreaseValue(weapon.getValue());
        monster.decreaseValue(weapon.getValue());
        assertNull(elements.getView(monster.getPos()));

    }

    @Test
    public void chooseXY() throws OutOfBoundaries {

        Position p = new Position(5, 4, Game.width / 4, Game.height / 4, 0);
        int i;

        i = monster.chooseX(p);
        assertEquals(0,i);

        Position p2 = new Position(4, 5, Game.width / 4, Game.height / 4, 0);
        i  = monster.chooseY(p2);
        assertEquals(2,i);

    }



}
