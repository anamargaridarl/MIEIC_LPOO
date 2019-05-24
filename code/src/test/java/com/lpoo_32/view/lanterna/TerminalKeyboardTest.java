package com.lpoo_32.view.lanterna;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.lpoo_32.exceptions.HungerOVF;
import com.lpoo_32.exceptions.ThirstOVF;
import com.lpoo_32.view.EventType;
import com.lpoo_32.view.TerminalKeyboard;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;

public class TerminalKeyboardTest {
    @Test
    public void processKey() throws IOException, ThirstOVF, HungerOVF {
        Screen screen = Mockito.mock(Screen.class);
        TerminalKeyboard keyboard = new TerminalKeyboard(screen);
        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowRight));
        Assert.assertEquals(EventType.MOVERIGHT, keyboard.processKey());
        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowUp));
        assertEquals(EventType.MOVEUP, keyboard.processKey());
        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowDown));
        assertEquals(EventType.MOVEDOWN, keyboard.processKey());
        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowLeft));
        assertEquals(EventType.MOVELEFT, keyboard.processKey());

        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke('z', false, false));
        assertEquals(EventType.EXIT, keyboard.processKey());
        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke('h', false, false));
        assertEquals(EventType.HELP, keyboard.processKey());
        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke('f', false, false));
        assertEquals(EventType.STORE, keyboard.processKey());
        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke('q', false, false));
        assertEquals(EventType.LEFTINVENTORY, keyboard.processKey());
        Mockito.when(screen.pollInput()).thenReturn(new KeyStroke('w', false, false));
        assertEquals(EventType.RIGHTINVENTORY, keyboard.processKey());
        Mockito.when(screen.pollInput()).thenReturn(null);
        assertEquals(null, keyboard.processKey());
    }
}
