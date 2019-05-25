package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.DoorModel;

import java.awt.*;

public class DoorView extends InteractableElementView{
    public DoorView(DoorModel element) {
        super(element, "door.png");
    }

    @Override
    void drawLanterna(TextGraphics graphics) {
        this.drawLanterna(
                graphics,
                "#91c474",
                "#000000",
                this.getElement().getPos().getTerminalPosition(),
                new TerminalSize(1, 1),
                Symbols.BLOCK_SPARSE
        );
    }

    @Override
    void drawSwing(Graphics graphics) {
        this.drawSwing(graphics,
                this.getElement().getPos().getSwingX(),
                this.getElement().getPos().getSwingY()
        );
    }
}
