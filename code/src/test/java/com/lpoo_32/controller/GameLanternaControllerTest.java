package com.lpoo_32.controller;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.*;
import com.lpoo_32.view.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GameLanternaControllerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void testCollision() throws HungerRestored, HungerOVF, ThirstOVF, ThirstRestored, DownScreen, HealthOVF, OutOfBoundaries, Bedtime {
        PlayerModel player = new PlayerModel(new Position(3,2, Game.width/4, Game.height/4, 0));
        SpikesModel spike = new SpikesModel(25, new Position(3,2, 0, 0, 0));
        FoodModel food = new FoodModel(10, new Position(3,3, 0, 0, 0));

        Elements elements = new Elements();
        elements.addElement(new SpikesView(spike));
        elements.addElement(new FoodView(food));

        List<ElementView> props = new ArrayList<>();
        props.add(new FoodView(food));
        props.add(new SpikesView(spike));
        props.add(new PlayerView(player));


        DisplayProps displayProps = Mockito.mock(DisplayProps.class);
        Mockito.when(displayProps.getScreen()).thenReturn(Mockito.mock(Screen.class));
        GameController k = new GameController(displayProps, elements, player, this.game = new GameLanterna(displayProps, this.player, elements), this.game = new GameLanterna(displayProps, this.player, elements));

        k.collisions(player.getPosition());

        assertEquals(75,player.getHealth().getValue());

        player.moveDown();
        k.collisions(player.getPosition());
        assertEquals(75,player.getHealth().getValue());
    }

    @Test
    public void processKey() throws IOException, HungerOVF, ScreenClose, ThirstOVF, HealthOVF, ThirstRestored, HungerRestored, DownScreen, LeftScreen, UpScreen, RightScreen, OutOfBoundaries, Bedtime {
        TerminalKeyboard keyboard = Mockito.mock(TerminalKeyboard.class);
        PlayerModel player = Mockito.mock(PlayerModel.class);
        CatchableElement e = Mockito.mock(CatchableElement.class);
        Inventory inventory = Mockito.mock(Inventory.class);
        DisplayProps displayProps = Mockito.mock(DisplayProps.class);
        Mockito.when(displayProps.getScreen()).thenReturn(Mockito.mock(Screen.class));
        Mockito.when(player.getInventory()).thenReturn(inventory);
        Position pos = Mockito.mock(Position.class);
        Mockito.when(player.getPosition()).thenReturn(pos);
        Mockito.when(pos.checkMovementLeft()).thenReturn(pos);
        Mockito.when(pos.checkMovementDown()).thenReturn(pos);
        Mockito.when(pos.checkMovementRight()).thenReturn(pos);
        Mockito.when(pos.checkMovementUp()).thenReturn(pos);
        GameController gameController = new GameController(displayProps, Mockito.mock(Elements.class), player, this.game = new GameLanterna(displayProps, this.player, Mockito.mock(Elements.class)), this.game = new GameLanterna(displayProps, this.player, Mockito.mock(Elements.class)));

        /*
        Mockito.when(keyboard.processKey()).thenReturn(EventType.MOVEUP);
        gameController.processKey(keyboard.processKey());
        verify(player).moveUp();
        Mockito.when(keyboard.processKey()).thenReturn(EventType.MOVEDOWN);
        gameController.processKey(keyboard.processKey());
        verify(player).moveDown();
        Mockito.when(keyboard.processKey()).thenReturn(EventType.MOVELEFT);
        gameController.processKey(keyboard.processKey());
        verify(player).moveLeft();
        Mockito.when(keyboard.processKey()).thenReturn(EventType.MOVERIGHT);
        gameController.processKey(keyboard.processKey());
        verify(player).moveRight();
        */
        Mockito.when(keyboard.processKey()).thenReturn(EventType.STORE);
        gameController.processKey(keyboard.processKey());
        Mockito.when(keyboard.processKey()).thenReturn(EventType.USE);
        gameController.processKey(keyboard.processKey());
        verify(player, atLeast(6)).getPosition();
        Mockito.when(keyboard.processKey()).thenReturn(EventType.LEFTINVENTORY);
        gameController.processKey(keyboard.processKey());
        verify(inventory).moveLeft();
        Mockito.when(keyboard.processKey()).thenReturn(EventType.RIGHTINVENTORY);
        gameController.processKey(keyboard.processKey());
        verify(inventory).moveRight();
        Mockito.when(keyboard.processKey()).thenReturn(EventType.INVETORYUSE);
        gameController.processKey(keyboard.processKey());
        verify(inventory).getElement();
    }

    @Test
    public void screenClose() throws HungerOVF, ThirstOVF, IOException, DownScreen, LeftScreen, ScreenClose, RightScreen, UpScreen, HealthOVF, ThirstRestored, HungerRestored, OutOfBoundaries, Bedtime {
        TerminalKeyboard keyboard = Mockito.mock(TerminalKeyboard.class);
        PlayerModel player = Mockito.mock(PlayerModel.class);
        DisplayProps displayProps = Mockito.mock(DisplayProps.class);
        Mockito.when(displayProps.getScreen()).thenReturn(Mockito.mock(Screen.class));
        GameController gameController = new GameController(displayProps, Mockito.mock(Elements.class), player, this.game = new GameLanterna(displayProps, this.player, Mockito.mock(Elements.class)), this.game = new GameLanterna(displayProps, this.player, Mockito.mock(Elements.class)));
        Mockito.when(keyboard.processKey()).thenReturn(EventType.EXIT);
        thrown.expect(ScreenClose.class);
        gameController.processKey(keyboard.processKey());
    }


    @Test
    public void updateGame() throws ScreenClose, InterruptedException, LeftScreen, DownScreen, IOException, RightScreen, HealthOVF, UpScreen, ThirstOVF, HungerOVF, OutOfBoundaries {
        DisplayProps displayProps = Mockito.mock(DisplayProps.class);
        PlayerModel player = Mockito.mock(PlayerModel.class);
        Status status = Mockito.mock(Status.class);
        Screen screen = Mockito.mock(Screen.class);
        Mockito.when(player.getFood()).thenReturn(status);
        Mockito.when(player.getHealth()).thenReturn(status);
        Mockito.when(player.getWater()).thenReturn(status);
        Mockito.when(player.getPosition()).thenReturn(Mockito.mock(Position.class));
        Mockito.when(player.getInventory()).thenReturn(Mockito.mock(Inventory.class));
        Mockito.when(displayProps.getScreen()).thenReturn(screen);
        Mockito.when(screen.newTextGraphics()).thenReturn(Mockito.mock(TextGraphics.class));
        TerminalSize terminal = Mockito.mock(TerminalSize.class);
        Mockito.when(terminal.getColumns()).thenReturn(100);
        Mockito.when(terminal.getRows()).thenReturn(100);
        ScreenSize.createInstance(terminal);
        GameController controller = new GameController(displayProps, Mockito.mock(Elements.class), player, this.game = new GameLanterna(displayProps, this.player, Mockito.mock(Elements.class)), this.game = new GameLanterna(displayProps, this.player, Mockito.mock(Elements.class)));
        controller.setTime(3599);
        controller.updateGame();
        controller.setTime(5399);
        controller.updateGame();
        verify(status, atLeast(2)).decreaseValue(5);
    }

    @Test
    public void monsterEqualsPlayer() throws OutOfBoundaries {
        Position p2 = new Position(4, 5, Game.width / 4, Game.height / 4, 0);
        Position p1 = new Position(5, 5, Game.width / 4, Game.height / 4, 0);

        Elements elements = new Elements();
        PlayerModel player = new PlayerModel(p2);

        DisplayProps displayProps = Mockito.mock(DisplayProps.class);
        Mockito.when(displayProps.getScreen()).thenReturn(Mockito.mock(Screen.class));
        GameController k = new GameController(displayProps, elements, player);

        assertTrue(k.monsterEqualsPlayer(p2,p1));

    }


    @Test
    public void changeWeaponInventory() throws OutOfBoundaries {

        Position p1 = new Position(5, 5, Game.width / 4, Game.height / 4, 0);
        WeaponModel w1 = new WeaponModel(p1,20);
        WeaponModel w2 = new WeaponModel(p1,40);

        Elements elements = new Elements();
        PlayerModel player = new PlayerModel(p1);

        DisplayProps displayProps = Mockito.mock(DisplayProps.class);
        Mockito.when(displayProps.getScreen()).thenReturn(Mockito.mock(Screen.class));
        GameController k = new GameController(displayProps, elements, player);

        player.setWeapon(w1);
        player.addElementInventory(new WeaponView(w2));
        k.changeWeaponInventory();

        assertEquals(40, player.getWeapon().getValue());
        assertEquals( 20 , player.getInventory().getElement().getValue());

        player.setWeapon(null);
        k.changeWeaponInventory();
        assertEquals(20, player.getWeapon().getValue());
        assertEquals(null, player.getInventory().getElement());

        assertFalse(k.changeWeaponInventory());

    }

    @Test
    public void checkForMonsterAndAttack() throws OutOfBoundaries, HungerOVF, ThirstOVF, ThirstRestored, RightScreen, DownScreen, LeftScreen, HealthOVF, HungerRestored, UpScreen {

        Position p1 = new Position(5, 5, Game.width / 4, Game.height / 4, 0);
        Position p2 = new Position(4, 5, Game.width / 4, Game.height / 4, 0);
        Position p3 = new Position(6, 5, Game.width / 4, Game.height / 4, 0);
        WeaponModel w1 = new WeaponModel(p1,20);


        Elements elements = new Elements();
        PlayerModel player = new PlayerModel(p1);

        DisplayProps displayProps = Mockito.mock(DisplayProps.class);
        Mockito.when(displayProps.getScreen()).thenReturn(Mockito.mock(Screen.class));
        GameController k = new GameController(displayProps, elements, player);

        WaterModel water = new WaterModel(20,p3);
        MonsterModel monster = new MonsterModel(p2, 40, new MovableElement(p2), k, p1);
        elements.addElement(new MonsterView(monster));

        k.checkForMonsterAndAttack(p1,Attacks.ALEFT,w1);
        assertEquals(20, monster.getValue());

        k.checkForMonsterAndAttack(p1,Attacks.ARIGHT,w1);
        assertEquals(20, monster.getValue());

        k.checkForMonsterAndAttack(p1,Attacks.ARIGHT,null);
        assertEquals(20, monster.getValue());

        k.checkForMonsterAndAttack(p1,Attacks.ARIGHT,w1);
        assertEquals(20, monster.getValue());
        
    }




}
