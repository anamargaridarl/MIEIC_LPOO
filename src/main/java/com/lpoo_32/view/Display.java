package com.lpoo_32.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalResizeListener;
import com.lpoo_32.model.Elements;

import javax.lang.model.element.Element;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class Display {
    protected Screen screen;
//    protected WindowBasedTextGUI gui;
    protected List<ElementView> props;
    protected Elements elements;

    Display() throws IOException {

        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        this.screen = new TerminalScreen(terminal);

        this.screen.startScreen();
        screen.doResizeIfNecessary();
        this.props = new LinkedList<>();
        this.elements = new Elements();

        ScreenSize.createInstance(this.screen.getTerminalSize());
        terminal.addResizeListener((terminal1, newSize) -> ScreenSize.createInstance(newSize));
    }

    abstract public void run() throws IOException;
    abstract public void draw() throws IOException;
}
