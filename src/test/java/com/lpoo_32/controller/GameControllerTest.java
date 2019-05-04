package com.lpoo_32.controller;

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
        PlayerModel player = new PlayerModel(new Position(3,2, 100, 100, 0));
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
        GameController k = new GameController(displayProps, elements, player);

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
        DisplayProps displayProps = Mockito.mock(DisplayProps.class);
        Mockito.when(displayProps.getScreen()).thenReturn(Mockito.mock(Screen.class));
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
        Mockito.when(keyboard.processKey()).thenReturn(EventType.EXIT);
        thrown.expect(ScreenClose.class);
        gameController.processKey(keyboard.processKey());


    }


}
