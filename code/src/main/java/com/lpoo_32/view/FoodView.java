package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.CatchableElement;
import com.lpoo_32.model.FoodModel;

import java.awt.*;

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
    public void drawLanterna(TextGraphics graphics) {
        this.drawLanterna(
                graphics,
                "#91c474",
                "#000000",
                this.food.getPos().getTerminalPosition(),
                new TerminalSize(1, 1),
                this.getSymbol()
        );
    }

    @Override
    void drawSwing(Graphics graphics) {
        super.drawSwing(graphics, "drumstick.png");
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
