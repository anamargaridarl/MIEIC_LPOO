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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GameControllerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void testCollision() throws HungerRestored, HungerOVF, ThirstOVF, ThirstRestored, DownScreen, HealthOVF, OutOfBoundaries {
        PlayerModel player = new PlayerModel(new Position(3,2, Game.width/4, Game.height/4, 0));
        SpikesModel spike = new SpikesModel(25, new Position(3,2, 0, 0, 0));
        FoodModel food = new FoodModel(10, new Position(3,3, 0, 0, 0));

        Elements elements = new Elements();
        elements.addElement(new SpikesView(spike));
        elements.addElement(new FoodView(food));

        List<ElementView> props = new ArrayList<>();
        props.add(new FoodView(food));
        props.add(new SpikesView(spike));
        props.add(new PlayerView(player, weapons));


        DisplayProps displayProps = Mockito.mock(DisplayProps.class);
        Mockito.when(displayProps.getScreen()).thenReturn(Mockito.mock(Screen.class));
        GameController k = new GameController(displayProps, elements, player);

        k.collisions(player.getPosition());

        assertEquals(75,player.getHealth().getValue());

        player.moveDown();
        k.collisions(player.getPosition());
        assertEquals(75,player.getHealth().getValue());
    }

    @Test
    public void processKey() throws IOException, HungerOVF, ScreenClose, ThirstOVF, HealthOVF, ThirstRestored, HungerRestored, DownScreen, LeftScreen, UpScreen, RightScreen, OutOfBoundaries {
        TerminalKeyboard keyboard = Mockito.mock(TerminalKeyboard.class);
        PlayerModel player = Mockito.mock(PlayerModel.class);
        CatchableElement e = Mockito.mock(CatchableElement.class);
        Inventory inventory = Mockito.mock(Inventory.class);
        DisplayProps displayProps = Mockito.mock(DisplayProps.class);
        Mockito.when(displayProps.getScreen()).thenReturn(Mockito.mock(Screen.class));
        Mockito.when(player.getInventory()).thenReturn(inventory);
        GameController gameController = new GameController(displayProps, Mockito.mock(Elements.class), player);

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
    public void screenClose() throws HungerOVF, ThirstOVF, IOException, DownScreen, LeftScreen, ScreenClose, RightScreen, UpScreen, HealthOVF, ThirstRestored, HungerRestored, OutOfBoundaries {
        TerminalKeyboard keyboard = Mockito.mock(TerminalKeyboard.class);
        PlayerModel player = Mockito.mock(PlayerModel.class);
        DisplayProps displayProps = Mockito.mock(DisplayProps.class);
        Mockito.when(displayProps.getScreen()).thenReturn(Mockito.mock(Screen.class));
        GameController gameController = new GameController(displayProps, Mockito.mock(Elements.class), player);
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
        GameController controller = new GameController(displayProps, Mockito.mock(Elements.class), player);
        controller.setTime(3599);
        controller.updateGame();
        controller.setTime(5399);
        controller.updateGame();
        verify(status, atLeast(2)).decreaseValue(5);
    }
}
