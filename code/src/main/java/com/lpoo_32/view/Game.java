package com.lpoo_32.view;

import java.io.IOException;

public abstract class Game {
    public static final int width = 60;
    public static final int height = 50;
    protected int index;

    public Game() {
        this.index = 0;
    }

    abstract public void draw() throws IOException;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
