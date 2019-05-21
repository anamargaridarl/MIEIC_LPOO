package com.lpoo_32.view.lanterna;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.CatchableElement;
import com.lpoo_32.model.FoodModel;

public class FoodView extends CatchableView {
    private final FoodModel food;

    public FoodView(FoodModel food){
        super(food);
        this.food = food;
    }


    @Override
    public char getSymbol() {
        return Symbols.HEART;
    }

    @Override
    public String getName() {
        return "Food";}

    @Override
    public void draw(TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#91c474"));

        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        graphics.fillRectangle(
                this.food.getPos().getTerminalPosition(),
                new TerminalSize(1, 1),
                this.getSymbol()
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
