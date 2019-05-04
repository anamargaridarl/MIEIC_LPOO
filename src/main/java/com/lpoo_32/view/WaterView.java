package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.CatchableElement;
import com.lpoo_32.model.WaterModel;

public class WaterView extends CatchableView {
    private final WaterModel water;

    WaterView(WaterModel water){
        super(water);
        this.water = water;
    }

    @Override
    public CatchableElement getElement() {
        return water;
    }

    @Override
    public char getSymbol() {
        return Symbols.BULLET;
    }

    @Override
    public String getName() {
        return "Water";
    }

    @Override
    public void draw(TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#7CFC00"));

        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        graphics.fillRectangle(
                this.water.getPos().getTerminalPosition(),
                new TerminalSize(1, 1),
                this.getSymbol()
        );
    }


}
