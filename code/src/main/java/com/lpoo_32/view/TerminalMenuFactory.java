package com.lpoo_32.view;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class TerminalMenuFactory implements MenuAbstractFactory {
    @Override
    public Runnable getMenu() throws IOException {
        return new Menu(new DefaultTerminalFactory().createTerminal());
    }
}
