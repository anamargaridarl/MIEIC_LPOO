package com.lpoo_32.view;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.lpoo_32.model.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Display {
    protected Screen screen;
    protected Elements elements;
    protected Terminal terminal;

    Display() throws IOException {

        this.terminal = new DefaultTerminalFactory().createTerminal();
        this.screen = new TerminalScreen(terminal);

        this.screen.startScreen();
        screen.doResizeIfNecessary();
        this.elements = new Elements();

        ScreenSize.createInstance(this.screen.getTerminalSize());
        terminal.addResizeListener((terminal1, newSize) -> ScreenSize.createInstance(newSize));
    }

    Display(Screen screen) {
        this.screen = screen;
        this.elements = new Elements();
    }

    abstract public void run() throws IOException;
    abstract public void draw() throws IOException;
}
