package com.lpoo_32.view;

import java.io.IOException;

public abstract class Game {
    protected int index;

    public Game() {
        this.index = 0;
    }

    abstract void draw() throws IOException;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
