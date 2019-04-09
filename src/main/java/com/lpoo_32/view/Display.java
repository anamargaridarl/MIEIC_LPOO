package com.lpoo_32.view;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public abstract class Display {
    protected Screen screen;
//    protected WindowBasedTextGUI gui;

    Display() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        this.screen = new TerminalScreen(terminal);

        this.screen.startScreen();
        screen.doResizeIfNecessary();
    }
    abstract public void draw() throws IOException;
}
