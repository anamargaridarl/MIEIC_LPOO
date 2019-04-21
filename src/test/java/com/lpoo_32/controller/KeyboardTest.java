package com.lpoo_32.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class KeyboardTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void testCollision() throws HungerRestored, HungerOVF, ThirstOVF, ThirstRestored {
        PlayerModel player = new PlayerModel(new Position(3,2));
        SpikesModel spike = new SpikesModel(25, new Position(3,2));
        FoodModel food = new FoodModel(10, new Position(3,3));
        Elements elements = new Elements();
        elements.addElement(spike);
        elements.addElement(food);
        Keyboard k = new Keyboard(player,elements);

        k.colisions(player.getPosition());

        assertEquals(75,player.getHealth().getValue());

        player.moveDown();
        k.colisions(player.getPosition());
        assertEquals(85,player.getHealth().getValue());
    }

    @Test
    public void processKey() throws IOException, HungerOVF, ScreenClose, ThirstOVF, HealthOVF, ThirstRestored, HungerRestored {
        Screen screen = Mockito.mock(Screen.class);
        PlayerModel player = Mockito.mock(PlayerModel.class);
        Keyboard keyboard = new Keyboard(player, new Elements());
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
        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke('q', false, false));
        thrown.expect(ScreenClose.class);
        keyboard.processKey(screen);
        verify(player).moveRight();
    }


}
