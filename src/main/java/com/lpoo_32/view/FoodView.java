package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.CatchableElement;
import com.lpoo_32.model.FoodModel;
import com.lpoo_32.model.InteractableElement;

public class FoodView extends InteractableElementView {
    private final FoodModel food;

    FoodView(FoodModel food){
        super(food);
        this.food = food;
    }

    @Override
    public void draw(TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#7CFC00"));

        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        graphics.fillRectangle(
                this.food.getPos().getTerminalPosition(),
                new TerminalSize(1, 1),
                Symbols.HEART
        );
    }

    public FoodModel getFood()
    {
        return food;
    }


    @Override
    public CatchableElement getElement() {
        return food;
    }
}
