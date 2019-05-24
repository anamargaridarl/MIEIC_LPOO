package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.WallModel;

import java.awt.*;

public class WallView extends InteractableElementView{

    public WallView(WallModel element) {
        super(element);
    }

    @Override
    void drawLanterna(TextGraphics graphics) {
        this.drawLanterna(
                graphics,
                "#91c474",
                "#000000",
                this.getElement().getPos().getTerminalPosition(),
                new TerminalSize(1, 1),
                '#'
        );
    }

    @Override
    void drawSwing(Graphics graphics) {
        this.drawSwing(graphics, "wall.png");
    }
}
