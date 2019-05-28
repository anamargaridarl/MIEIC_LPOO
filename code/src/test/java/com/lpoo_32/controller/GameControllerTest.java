package com.lpoo_32.controller;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.lpoo_32.controller.action.*;
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

public class GameControllerTest {
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
        props.add(new PlayerView(player, ""));

        Game game = Mockito.mock(Game.class);
        GameController k = new GameController(elements, player, game);

        k.collisions(player.getPosition());

        assertEquals(75,player.getHealth().getValue());

        player.moveDown();
        k.collisions(player.getPosition());
        assertEquals(75,player.getHealth().getValue());
    }

    @Test
    public void processKey() throws IOException, DownScreen, LeftScreen, UpScreen, RightScreen, OutOfBoundaries {
        TerminalKeyboard keyboard = Mockito.mock(TerminalKeyboard.class);
        PlayerModel player = Mockito.mock(PlayerModel.class);
        CatchableElement e = Mockito.mock(CatchableElement.class);
        Inventory inventory = Mockito.mock(Inventory.class);
        Elements elements = Mockito.mock(Elements.class);
        Mockito.when(player.getInventory()).thenReturn(inventory);
        Position pos = Mockito.mock(Position.class);
        Mockito.when(player.getPosition()).thenReturn(pos);
        Mockito.when(pos.checkMovementLeft()).thenReturn(pos);
        Mockito.when(pos.checkMovementDown()).thenReturn(pos);
        Mockito.when(pos.checkMovementRight()).thenReturn(pos);
        Mockito.when(pos.checkMovementUp()).thenReturn(pos);
        Mockito.when(elements.getModel(any(Position.class))).thenReturn(Mockito.mock(InteractableElement.class));
        GameController gameController = new GameController(elements, player, Mockito.mock(Game.class));

        gameController.processKey(new MoveUp(gameController));
        verify(player).moveUp();
        gameController.processKey(new MoveDown(gameController));
        verify(player).moveDown();
        gameController.processKey(new MoveLeft(gameController));
        verify(player).moveLeft();
        gameController.processKey(new MoveRight(gameController));
        verify(player).moveRight();
        Mockito.when(elements.getView(any(Position.class))).thenReturn(Mockito.mock(CatchableView.class));
        Mockito.when(elements.getModel(any(Position.class))).thenReturn(Mockito.mock(CatchableElement.class));
        gameController.processKey(new Store(gameController));
        verify(player).addElementInventory(any(CatchableView.class));
        Mockito.when(elements.getView(any(Position.class))).thenReturn(Mockito.mock(WeaponView.class));
        gameController.processKey(new Use(gameController));
        verify(elements, atLeast(3)).getModel(any(Position.class));
        gameController.processKey(new LeftInventory(player));
        verify(inventory).moveLeft();
        gameController.processKey(new RightInventory(player));
        verify(inventory).moveRight();
        gameController.processKey(new InventoryUse(gameController));
        verify(inventory).getElement();
    }

    @Test
    public void updateGame() throws ScreenClose, InterruptedException, LeftScreen, DownScreen, IOException, RightScreen, HealthOVF, UpScreen, ThirstOVF, HungerOVF, OutOfBoundaries {
        PlayerModel player = Mockito.mock(PlayerModel.class);
        Status status = Mockito.mock(Status.class);
        Screen screen = Mockito.mock(Screen.class);
        Mockito.when(player.getFood()).thenReturn(status);
        Mockito.when(player.getHealth()).thenReturn(status);
        Mockito.when(player.getWater()).thenReturn(status);
        Mockito.when(player.getPosition()).thenReturn(Mockito.mock(Position.class));
        Mockito.when(player.getInventory()).thenReturn(Mockito.mock(Inventory.class));
        Mockito.when(screen.newTextGraphics()).thenReturn(Mockito.mock(TextGraphics.class));
        TerminalSize terminal = Mockito.mock(TerminalSize.class);
        Mockito.when(terminal.getColumns()).thenReturn(100);
        Mockito.when(terminal.getRows()).thenReturn(100);
        ScreenSize.createInstance(terminal);
        Game game = Mockito.mock(Game.class);
        GameController controller = new GameController(Mockito.mock(Elements.class), player, game);
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

        Game game = Mockito.mock(Game.class);
        GameController k = new GameController(elements, player, game);
        assertTrue(k.monsterEqualsPlayer(p2,p1));

    }


    @Test
    public void changeWeaponInventory() throws OutOfBoundaries {

        Position p1 = new Position(5, 5, Game.width / 4, Game.height / 4, 0);
        WeaponModel w1 = new WeaponModel(p1,20);
        WeaponModel w2 = new WeaponModel(p1,40);

        Elements elements = new Elements();
        PlayerModel player = new PlayerModel(p1);

        Game game = Mockito.mock(Game.class);
        GameController k = new GameController(elements, player, game);

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
}
