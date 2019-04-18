package com.lpoo_32.view;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class Display {
    protected Screen screen;
//    protected WindowBasedTextGUI gui;
    protected List<ElementView> props;
    protected Terminal terminal;

    Display() throws IOException {

        this.terminal = new DefaultTerminalFactory().createTerminal();
        this.screen = new TerminalScreen(terminal);

        this.screen.startScreen();
        screen.doResizeIfNecessary();
        this.props = new LinkedList<>();

        ScreenSize.createInstance(this.screen.getTerminalSize());
        terminal.addResizeListener((terminal1, newSize) -> ScreenSize.createInstance(newSize));
    }

    Display(Screen screen) throws IOException {
        this.screen = screen;
        this.props = new LinkedList<>();
    }

    abstract public void run() throws IOException;
    abstract public void draw() throws IOException;
}
