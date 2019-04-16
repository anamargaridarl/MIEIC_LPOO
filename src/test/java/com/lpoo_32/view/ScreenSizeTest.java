package com.lpoo_32.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ScreenSizeTest {
    @Test
    public void testOutputs() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        TerminalSize size = screen.getTerminalSize();
        ScreenSize.createInstance(size);
        assertEquals((int) (size.getColumns() * ((double)100/ 100)), ScreenSize.instance().getColumn(100));
        assertEquals((int) (size.getRows() * ((double)100/ 100)), ScreenSize.instance().getRows(100));
        assertEquals((int) (size.getColumns() * ((double)20/ 100)), ScreenSize.instance().getColumn(20));
    }
}