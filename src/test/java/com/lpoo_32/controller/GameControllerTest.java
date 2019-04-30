package com.lpoo_32.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
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
import static org.mockito.Mockito.verify;

public class GameControllerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void testCollision() throws HungerRestored, HungerOVF, ThirstOVF, ThirstRestored, DownScreen, HealthOVF {
        PlayerModel player = new PlayerModel(new Position(3,2, 0, 0, 0));
        SpikesModel spike = new SpikesModel(25, new Position(3,2, 0, 0, 0));
        FoodModel food = new FoodModel(10, new Position(3,3, 0, 0, 0));

        Elements elements = new Elements();
        elements.addElement(spike);
        elements.addElement(food);

        List<ElementView> props = new ArrayList<>();
        props.add(new FoodView(food));
        props.add(new SpikesView(spike));
        props.add(new PlayerView(player));


        GameController k = new GameController(player,elements,props);

        k.collisions(player.getPosition());

        assertEquals(75,player.getHealth().getValue());

        player.moveDown();
        k.collisions(player.getPosition());
        assertEquals(75,player.getHealth().getValue());
    }

    @Test
    public void processKey() throws IOException, HungerOVF, ScreenClose, ThirstOVF, HealthOVF, ThirstRestored, HungerRestored, DownScreen, LeftScreen, UpScreen, RightScreen {
        TerminalKeyboard keyboard = Mockito.mock(TerminalKeyboard.class);
        PlayerModel player = Mockito.mock(PlayerModel.class);
        CatchableElement e = Mockito.mock(CatchableElement.class);
        Elements elements = Mockito.mock(Elements.class);
        GameController gameController = new GameController(player, new Elements(), null);

        Mockito.when(keyboard.processKey(null)).thenReturn(EventType.MOVEUP);
        gameController.processKey(keyboard.processKey(null));
        verify(player).moveUp();
        Mockito.when(keyboard.processKey(null)).thenReturn(EventType.MOVEDOWN);
        gameController.processKey(keyboard.processKey(null));
        verify(player).moveDown();
        Mockito.when(keyboard.processKey(null)).thenReturn(EventType.MOVELEFT);
        gameController.processKey(keyboard.processKey(null));
        verify(player).moveLeft();
        Mockito.when(keyboard.processKey(null)).thenReturn(EventType.MOVERIGHT);
        gameController.processKey(keyboard.processKey(null));
        verify(player).moveRight();
        Mockito.when(keyboard.processKey(null)).thenReturn(EventType.STORE);
        gameController.processKey(keyboard.processKey(null));
        verify(player).addElementInventory(e);
        Mockito.when(keyboard.processKey(null)).thenReturn(EventType.INVETORYUSE);
        gameController.processKey(keyboard.processKey(null));
        verify(gameController).removeElementProps(e);
        Mockito.when(keyboard.processKey(null)).thenReturn(EventType.EXIT);
        gameController.processKey(keyboard.processKey(null));
        thrown.expect(ScreenClose.class);


    }


}
