package com.lpoo_32.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.ElementModel;

import java.awt.*;

public abstract class ElementView  {
    private ElementModel element;
    ElementView(ElementModel element){
        this.element = element;
    }

    ElementView(){
    }
    void drawLanterna(TextGraphics graphics, String backgroundColor, String foregroundColor, TerminalPosition pos, TerminalSize size, char symbol){
        graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));

        graphics.setForegroundColor(TextColor.Factory.fromString(foregroundColor));

        graphics.fillRectangle(
                pos,
                size,
                symbol
        );
    }
    abstract void drawLanterna(TextGraphics graphics) throws HungerOVF, UpScreen, ThirstOVF, HealthOVF, ThirstRestored, HungerRestored, LeftScreen, RightScreen, DownScreen, Bedtime;

    abstract void drawSwing(Graphics graphics) throws HungerOVF, UpScreen, ThirstOVF, HealthOVF, ThirstRestored, HungerRestored, LeftScreen, RightScreen, DownScreen, Bedtime;

    void drawSwing(Graphics graphics, int x, int y){

        graphics.drawImage(element.getImage(), x, y + 20, 30, 30, null);
    }

}
