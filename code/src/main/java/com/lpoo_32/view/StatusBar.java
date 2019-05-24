package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.Status;

import java.awt.*;

public class StatusBar extends ElementView {
    private final Status status;
    private final TextColor color;
    private int height;


    StatusBar(Status status, String hexColor, int height){
        this.status = status;
        this.color = TextColor.Factory.fromString(hexColor);
        this.height = height;
    }

    int getScreenPercen(){
        return (int) ((double) this.status.getValue()/100 * 40);
    }

    @Override
    public void drawLanterna(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(color);
        graphics.fillRectangle(new TerminalPosition(getColumn(60),
                        getRows()),
                new TerminalSize(getColumn(this.getScreenPercen() + 1), 1),
                Symbols.BLOCK_SOLID);
        graphics.fillRectangle(new TerminalPosition(getColumn(60 + this.getScreenPercen()),
                        getRows()),
                new TerminalSize(getColumn(40 - this.getScreenPercen()), 1),
                Symbols.BLOCK_SPARSE);
    }

    @Override
    void drawSwing(Graphics graphics) {

    }

    private int getRows() {
        return ScreenSize.instance().getRows(height);
    }

    private int getColumn(int columns) {
        return ScreenSize.instance().getColumn(columns);
    }
}
