package com.lpoo_32.Controller;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.concurrent.atomic.AtomicBoolean;

public class SecondaryMenuKeyListener implements WindowListener {

    private final MultiWindowTextGUI gui;
    private final Window window;

    public SecondaryMenuKeyListener(MultiWindowTextGUI gui, Window window){
        this.gui = gui;
        this.window = window;
    }
    @Override
    public void onResized(Window window, TerminalSize oldSize, TerminalSize newSize) {

    }

    @Override
    public void onMoved(Window window, TerminalPosition oldPosition, TerminalPosition newPosition) {

    }

    @Override
    public void onInput(Window basePane, KeyStroke keyStroke, AtomicBoolean deliverEvent) {
        if(keyStroke.getKeyType().equals(KeyType.Character)){
            if(keyStroke.getCharacter() == 'q')
                this.gui.removeWindow(this.window);
        }
    }

    @Override
    public void onUnhandledInput(Window basePane, KeyStroke keyStroke, AtomicBoolean hasBeenHandled) {

    }
}
