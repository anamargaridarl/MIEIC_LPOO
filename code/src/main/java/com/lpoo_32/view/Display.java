package com.lpoo_32.view;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;
import com.lpoo_32.exceptions.*;

import java.io.IOException;

public abstract class Display {
    Screen screen;
    private Terminal terminal;

    Display(Terminal terminal) throws IOException {

        this.terminal = terminal;
        this.screen = new TerminalScreen(terminal);

        this.screen.startScreen();
        screen.doResizeIfNecessary();

        ScreenSize.createInstance(this.screen.getTerminalSize());
        terminal.addResizeListener((terminal1, newSize) -> ScreenSize.createInstance(newSize));
    }

    Display(Screen screen) {
        this.screen = screen;
    }

    abstract public void draw() throws IOException, HungerOVF, HungerRestored, ThirstOVF, HealthOVF, ThirstRestored, UpScreen, LeftScreen, RightScreen, DownScreen;
}
