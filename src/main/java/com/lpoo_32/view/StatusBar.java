package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.Status;

public class StatusBar implements ElementView{
    private final Status status;
    private final TextColor color;

    StatusBar(Status status, String hexColor){
        this.status = status;
        this.color = TextColor.Factory.fromString(hexColor);
    }

    int getScreenPercen(){
        return (int)Math.floor((double) this.status.getValue()/100 * 40);
    }

    @Override
    public void draw(TextGraphics graphics) {
        System.out.println("Status:" + ScreenSize.instance().getColumn(60) + " - " + ScreenSize.instance().getRows(50));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(color);
        graphics.fillRectangle(new TerminalPosition(getColumn(60),
                        getRows()),
                new TerminalSize(getColumn(this.getScreenPercen()), 1),
                Symbols.BLOCK_SOLID);
        graphics.fillRectangle(new TerminalPosition(getColumn(60 + this.getScreenPercen() - 1),
                        getRows()),
                new TerminalSize(getColumn(100 - this.getScreenPercen()), 1),
                Symbols.BLOCK_SPARSE);
    }

    private int getRows() {
        return ScreenSize.instance().getRows(10);
    }

    private int getColumn(int columns) {
        return ScreenSize.instance().getColumn(columns);
    }
}
