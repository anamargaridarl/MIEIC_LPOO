package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.CatchableElement;
import com.lpoo_32.model.WaterModel;

import java.awt.*;

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
    public void drawLanterna(TextGraphics graphics) {
        this.drawLanterna(graphics,
                "#91c474",
                "#000000",
                this.water.getPos().getTerminalPosition(),
                new TerminalSize(1, 1),
                 this.getSymbol()
        );
    }

    @Override
    void drawSwing(Graphics graphics) {
        this.drawSwing(graphics, "water.png");
    }


}
