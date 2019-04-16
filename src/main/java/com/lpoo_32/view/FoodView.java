package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.FoodModel;

public class FoodView implements ElementView {
    private final FoodModel food;

    FoodView(FoodModel food){
        this.food = food;
    }

    @Override
    public void draw(TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#7CFC00"));

        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        graphics.fillRectangle(
                new TerminalPosition(10, 10),
                new TerminalSize(1, 1),
                Symbols.HEART
        );
    }


}
