package com.lpoo_32.view;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;
import com.lpoo_32.exceptions.*;

import java.io.IOException;

abstract class Display {
    Screen screen;

    Display(Terminal terminal) throws IOException {

        this.screen = new TerminalScreen(terminal);

        this.screen.startScreen();
        screen.doResizeIfNecessary();

        ScreenSize.createInstance(this.screen.getTerminalSize());
        terminal.addResizeListener((terminal1, newSize) -> ScreenSize.createInstance(newSize));
    }

    Display(Screen screen) {
        this.screen = screen;
    }

    abstract public void draw() throws IOException;
}
