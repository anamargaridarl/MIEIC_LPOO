package com.lpoo_32.graphics;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class Game extends Display{
    public Game() throws IOException {
        super();
    }

    @Override
    public void draw() throws IOException {
        this.screen.clear();
        TextGraphics graphics = this.screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#48D1CC"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(30, 30), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(new TerminalPosition(20, 20), "@");
        this.screen.refresh();
    }
}
