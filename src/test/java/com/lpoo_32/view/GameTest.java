package com.lpoo_32.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.lpoo_32.exceptions.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class GameTest {
    private Game game;
    private Screen screen;
    private TextGraphics graphics;

    @Before
    public void initalizeGame() throws IOException {
        TerminalSize terminal = Mockito.mock(TerminalSize.class);
        Mockito.when(terminal.getColumns()).thenReturn(100);
        Mockito.when(terminal.getRows()).thenReturn(100);
        ScreenSize.createInstance(terminal);
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        game = new Game(screen);
    }
    @Test
    public void updateGame() throws IOException, ScreenClose, HealthOVF, InterruptedException, DownScreen, LeftScreen, UpScreen, RightScreen {
        game.updateGame();
        verify(screen, times(1)).refresh();
        verify(screen, times(1)).clear();
    }

    @Test
    public void draw(){
        game.draw();
        verify(graphics, atLeastOnce()).setBackgroundColor(any(TextColor.class));
        verify(graphics, atLeastOnce()).setForegroundColor(any(TextColor.class));
        verify(graphics, atLeastOnce()).fillRectangle(any(TerminalPosition.class), any(TerminalSize.class), any(char.class));
    }
}