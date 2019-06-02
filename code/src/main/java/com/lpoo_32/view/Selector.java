package com.lpoo_32.view;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.Position;

import java.awt.*;
import java.io.IOException;

public class Selector extends ElementView {
    private final MenuSwing menuSwing;
    private int x = 300;
    private int y = 30;
    private int index = 0;

    Selector(String name, MenuSwing menuSwing) {
        super(name);
        this.menuSwing = menuSwing;
    }

    @Override
    void drawLanterna(TextGraphics graphics) {

    }

    @Override
    void drawSwing(Graphics graphics) {
        this.drawSwing(graphics, x, y + index * 100);
    }

    public void moveUp() {
        if(this.index > 0){
            this.index--;
        }
    }

    public void moveDown() {
        if(this.index < 2){
            this.index++;
        }
    }

    public void enter() throws IOException, OutOfBoundaries {
        switch (this.index){
            case 0:
                menuSwing.initiateGame();
        }
    }
}
