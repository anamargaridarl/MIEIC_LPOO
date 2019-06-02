package com.lpoo_32.model;

import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.view.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static junit.framework.TestCase.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class MonsterTest {

    private PlayerModel player;
    private MonsterModel monster;
    private MonsterView monsterview;
    private Elements elements;

    private Position monsterposition;
    private MonsterModel monsterRandom;
    private MonsterView monsterViewRandom;
    private Random random;



    @Before
    public void init() throws OutOfBoundaries {
        Position positionmonster = new Position(5, 5, Game.width / 4, Game.height / 4, 0);
        elements = new Elements();
        player = new PlayerModel(new Position(4, 5, Game.width / 4, Game.height / 4, 0));
        GameController gameController = new GameController(elements, player, mock(Game.class));
        monster = new MonsterModel(positionmonster, 40, new MovableElement(positionmonster, ""), gameController, player.getPosition(), new Random());
        monsterview = new MonsterView(monster);
        elements.addElement(monsterview);


       monsterposition = new Position(6, 5, Game.width / 4, Game.height / 4, 0);
         random = mock(Random.class);
        Elements elements2 = new Elements();
        GameController gameController2 = new GameController(elements2, player, mock(Game.class));
         monsterRandom = new MonsterModel(monsterposition, 40, new MovableElement(monsterposition, ""), gameController2, player.getPosition(), random);
         monsterViewRandom = new MonsterView(monsterRandom);
        elements2.addElement(monsterViewRandom);
    }

    @Test
    public void interact() throws HungerOVF, HealthOVF, ThirstOVF {
        monster.interact(player);
        assertEquals(60, player.getHealth().getValue());
    }


    @Test
    public void getMovement() throws RightScreen, DownScreen, LeftScreen, UpScreen, OutOfBoundaries {

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

    @Test
    public void moveMonster() throws DownScreen, LeftScreen, UpScreen, RightScreen {


        Mockito.when(random.nextInt( any(int.class))).thenReturn(1);

        monsterRandom.moveMonster(monsterViewRandom,player.getPosition());
        assertEquals(5, monsterViewRandom.getMonster().getPos().getX());

        monsterRandom.moveLeft();
        monsterRandom.moveLeft();
        monsterRandom.moveLeft();

        assertEquals( 2, monsterViewRandom.getMonster().getPos().getX());

        monsterRandom.moveMonster(monsterViewRandom,player.getPosition());
        assertEquals( 3, monsterViewRandom.getMonster().getPos().getX());

        Mockito.when(random.nextInt( any(int.class))).thenReturn(2);

        monsterRandom.moveDown();
        monsterRandom.moveDown();

        monsterRandom.moveMonster(monsterViewRandom,player.getPosition());
        assertEquals( 6, monsterViewRandom.getMonster().getPos().getY());

        monsterRandom.moveUp();
        monsterRandom.moveUp();
        monsterRandom.moveUp();

        monsterRandom.moveMonster(monsterViewRandom,player.getPosition());
        assertEquals( 4, monsterViewRandom.getMonster().getPos().getY());

        monsterRandom.moveMonster(monsterViewRandom,player.getPosition());
        assertEquals( 4, monsterViewRandom.getMonster().getPos().getY());


    }


    @Test
    public void updateMove() throws HungerOVF, ThirstOVF, ThirstRestored, RightScreen, DownScreen, LeftScreen, HealthOVF, HungerRestored, Bedtime, UpScreen {

        monsterRandom.updateMove(monsterViewRandom, 1);
        monsterRandom.updateMove(monsterViewRandom, 1);

        assertEquals( 5, monsterViewRandom.getMonster().getPos().getY());

    }




}
