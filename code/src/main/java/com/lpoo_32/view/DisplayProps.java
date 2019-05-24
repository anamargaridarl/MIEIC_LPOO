package com.lpoo_32.view;

import com.googlecode.lanterna.screen.Screen;

import javax.swing.*;

public  class DisplayProps {
    private final Screen screen;
    private final JFrame frame;

    DisplayProps(Screen screen, JFrame frame){
        this.screen = screen;
        this.frame = frame;
    }

    public Screen getScreen() {
        return screen;
    }

    public JFrame getFrame() {
        return frame;
    }
}
