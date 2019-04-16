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

    private int getScreenPercen(){
        return (int)Math.ceil((double) this.status.getValue()/100 * 40);
    }

    @Override
    public void draw(TextGraphics graphics) {
        System.out.print("Status Bar: ");
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(color);
        graphics.fillRectangle(new TerminalPosition(ScreenSize.instance().getColumn(60),
                                ScreenSize.instance().getRows(10)),
                                new TerminalSize(ScreenSize.instance().getColumn(this.getScreenPercen()), 1),
                                Symbols.BLOCK_SOLID);
        graphics.fillRectangle(new TerminalPosition(ScreenSize.instance().getColumn(60 + this.getScreenPercen()),
                                ScreenSize.instance().getRows(10)),
                                new TerminalSize(ScreenSize.instance().getColumn(100 - this.getScreenPercen()), 1),
                                Symbols.BLOCK_SPARSE);
    }
}
