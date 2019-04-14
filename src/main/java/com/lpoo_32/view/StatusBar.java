package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.Status;

public class StatusBar implements Drawable{
    private final Status status;
    private final TextColor color;

    StatusBar(Status status, String hexColor){
        this.status = status;
        this.color = TextColor.Factory.fromString(hexColor);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(color);
        graphics.fillRectangle(new TerminalPosition(ScreenSize.instance().getColumn(60),
                                ScreenSize.instance().getRows(10)),
                                new TerminalSize(this.status.getValue(), 1),
                                Symbols.BLOCK_SOLID);
        graphics.fillRectangle(new TerminalPosition(ScreenSize.instance().getColumn(60 + this.status.getValue()),
                                ScreenSize.instance().getRows(10)),
                                new TerminalSize(this.status.getValue(), 1),
                                Symbols.BLOCK_SPARSE);
    }
}
