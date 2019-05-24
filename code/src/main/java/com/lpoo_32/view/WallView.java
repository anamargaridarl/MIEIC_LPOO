package com.lpoo_32.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.WallModel;

public class WallView extends InteractableElementView{

    public WallView(WallModel element) {
        super(element);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#91c474"));

        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        graphics.fillRectangle(
                this.getElement().getPos().getTerminalPosition(),
                new TerminalSize(1, 1),
                '#'
        );
    }
}
