package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.BedModel;

import java.awt.*;

public class BedView extends InteractableElementView {
    public BedView(BedModel element) {
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
                Symbols.BOLD_FROM_NORMAL_SINGLE_LINE_VERTICAL
        );
    }

    @Override
    void drawSwing(Graphics graphics) {
        this.drawSwing(graphics, "bed.png");
    }
}
