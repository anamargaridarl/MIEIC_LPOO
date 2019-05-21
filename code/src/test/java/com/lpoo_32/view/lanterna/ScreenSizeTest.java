package com.lpoo_32.view.lanterna;

import com.googlecode.lanterna.TerminalSize;
import com.lpoo_32.view.lanterna.ScreenSize;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class ScreenSizeTest {
    @Test
    public void testOutputs() {
        TerminalSize terminal = Mockito.mock(TerminalSize.class);
        Mockito.when(terminal.getColumns()).thenReturn(100);
        Mockito.when(terminal.getRows()).thenReturn(100);
        ScreenSize.createInstance(terminal);
        assertEquals(100, ScreenSize.instance().getColumn(100));
        assertEquals(100, ScreenSize.instance().getRows(100));
        assertEquals((20), ScreenSize.instance().getColumn(20));
        assertEquals((20), ScreenSize.instance().getRows(20));
    }
}