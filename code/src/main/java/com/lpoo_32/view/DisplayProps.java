package com.lpoo_32.view;

import com.googlecode.lanterna.screen.Screen;

public  class DisplayProps {
    private final Screen screen;

    public DisplayProps(Screen screen){
        this.screen = screen;
    }

    public Screen getScreen() {
        return screen;
    }
}
