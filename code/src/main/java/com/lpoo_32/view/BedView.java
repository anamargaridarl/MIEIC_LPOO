package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.BedModel;

public class BedView extends InteractableElementView {
    public BedView(BedModel element) {
        super(element);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.fillRectangle(
                this.getElement().getPos().getTerminalPosition(),
                new TerminalSize(1, 1),
                Symbols.BOLD_FROM_NORMAL_SINGLE_LINE_VERTICAL
        );
    }
}
