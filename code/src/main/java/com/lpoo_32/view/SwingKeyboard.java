package com.lpoo_32.view;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class SwingKeyboard implements KeyEventDispatcher {
    @Override
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        System.out.println(keyEvent.getKeyChar() + " Meias");
        switch (keyEvent.getKeyChar()){
            default:
                System.out.println(keyEvent.getKeyChar());
        }
        return false;
    }
}
