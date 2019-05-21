package com.lpoo_32.view.lanterna;

import com.googlecode.lanterna.screen.Screen;

public  class DisplayProps {
    private final Screen screen;

    DisplayProps(Screen screen){
        this.screen = screen;
    }

    public Screen getScreen() {
        return screen;
    }
}
