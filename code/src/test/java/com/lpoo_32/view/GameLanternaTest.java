package com.lpoo_32.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.Elements;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.model.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class GameLanternaTest {
    private GameLanterna gameLanterna;
    private Screen screen;
    private TextGraphics graphics;
    private Elements elements;

    @Before
    public void initalizeGame() {
        TerminalSize terminal = Mockito.mock(TerminalSize.class);
        Mockito.when(terminal.getColumns()).thenReturn(100);
        Mockito.when(terminal.getRows()).thenReturn(100);
        ScreenSize.createInstance(terminal);
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        elements = Mockito.mock(Elements.class);
        gameLanterna = new GameLanterna(screen, new PlayerModel(Mockito.mock(Position.class)), elements);
    }

    @Test
    public void draw() throws IOException, HungerOVF, ThirstOVF, ThirstRestored, RightScreen, DownScreen, LeftScreen, HealthOVF, HungerRestored, UpScreen, Bedtime {
        gameLanterna.draw();
        verify(graphics, atLeastOnce()).setBackgroundColor(any(TextColor.class));
        verify(graphics, atLeastOnce()).setForegroundColor(any(TextColor.class));
        verify(graphics, atLeastOnce()).fillRectangle(any(TerminalPosition.class), any(TerminalSize.class), any(char.class));
        verify(screen, atLeastOnce()).refresh();
        verify(screen, atLeastOnce()).clear();
        verify(elements, atLeastOnce()).getViewByCoord(any(int.class), any(int.class));
    }
}