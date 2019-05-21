package com.lpoo_32.view.lanterna;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.Status;
import com.lpoo_32.view.ElementView;

public class StatusBar implements ElementView {
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
    public void draw(TextGraphics graphics) {
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

    private int getRows() {
        return ScreenSize.instance().getRows(height);
    }

    private int getColumn(int columns) {
        return ScreenSize.instance().getColumn(columns);
    }
}
