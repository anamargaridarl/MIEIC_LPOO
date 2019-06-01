package com.lpoo_32.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.Elements;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.model.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static org.mockito.Mockito.*;


public class GameSwingTest {
    private GameSwing gameSwing;
    private Graphics graphics;
    private Elements elements;


    @Before
    public void setUp() {
        TerminalSize terminal = Mockito.mock(TerminalSize.class);
        Mockito.when(terminal.getColumns()).thenReturn(100);
        Mockito.when(terminal.getRows()).thenReturn(100);
        ScreenSize.createInstance(terminal);
        JFrame frame = Mockito.mock(JFrame.class);
        Image img = Mockito.mock(Image.class);
        graphics = Mockito.mock(Graphics.class);
        Mockito.when(frame.createImage(any(int.class), any(int.class))).thenReturn(img);
        Mockito.when(img.getGraphics()).thenReturn(graphics);
        Mockito.when(frame.getGraphics()).thenReturn(graphics);
        elements = Mockito.mock(Elements.class);
        gameSwing = new GameSwing(frame, new PlayerModel(Mockito.mock(Position.class)), elements);
    }

    @Test
    public void draw() throws HungerOVF, ThirstOVF, ThirstRestored, RightScreen, DownScreen, LeftScreen, HealthOVF, HungerRestored, Bedtime, UpScreen {
        gameSwing.draw();
        verify(graphics, atLeastOnce()).drawImage(any(Image.class), any(int.class), any(int.class), any(int.class),any(int.class), isNull());
        verify(graphics, atLeastOnce()).clearRect(any(int.class), any(int.class), any(int.class), any(int.class));
        verify(graphics, atLeastOnce()).drawRect(any(int.class), any(int.class), any(int.class), any(int.class));
        verify(elements, atLeastOnce()).getViewByCoord(any(int.class), any(int.class));
    }


}