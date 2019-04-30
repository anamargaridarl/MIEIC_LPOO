package com.lpoo_32.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.*;

import org.junit.Rule;
import com.lpoo_32.view.ElementView;
import com.lpoo_32.view.FoodView;
import com.lpoo_32.view.PlayerView;
import com.lpoo_32.view.SpikesView;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class KeyboardTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void testCollision() throws HungerRestored, HungerOVF, ThirstOVF, ThirstRestored, DownScreen {
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


        Keyboard k = new Keyboard(player,elements,props);

        k.collisions(player.getPosition());

        assertEquals(75,player.getHealth().getValue());

        player.moveDown();
        k.collisions(player.getPosition());
        assertEquals(75,player.getHealth().getValue());
    }

    @Test
    public void processKey() throws IOException, HungerOVF, ScreenClose, ThirstOVF, HealthOVF, ThirstRestored, HungerRestored, DownScreen, LeftScreen, UpScreen, RightScreen {
        Screen screen = Mockito.mock(Screen.class);
        PlayerModel player = Mockito.mock(PlayerModel.class);
        CatchableElement e = Mockito.mock(CatchableElement.class);
        Elements elements = Mockito.mock(Elements.class);
        Keyboard keyboard = new Keyboard(player, new Elements(), null);

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowUp));
        keyboard.processKey(screen);
        verify(player).moveUp();
        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowDown));
        keyboard.processKey(screen);
        verify(player).moveDown();
        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowLeft));
        keyboard.processKey(screen);
        verify(player).moveLeft();
        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowRight));
        keyboard.processKey(screen);
        verify(player).moveRight();
        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke('z', false, false));
        thrown.expect(ScreenClose.class);
        keyboard.processKey(screen);
        verify(player).moveRight();

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke('f',false,false));
        keyboard.processKey(screen);
        verify(player).addElementInventory(e);

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke('t',false,false));
        keyboard.processKey(screen);
        verify(keyboard).removeElementProps(e);

    }


}
