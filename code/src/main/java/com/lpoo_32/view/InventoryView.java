package com.lpoo_32.view;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.Inventory;

import java.awt.*;


public class InventoryView extends BoxView {

    private final Inventory inventory;
    private final String color;
    //private int height;

    public InventoryView(Inventory inventory, String hexColor) {
        super();
        this.inventory = inventory;
        this.color = hexColor;
    }


    @Override
    public void drawLanterna(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString(color));


        graphics.fillRectangle(new TerminalPosition(getColumn(70),
                getRows(60)), new TerminalSize(getColumn(getColumn(20)), 4),
                Symbols.BLOCK_SOLID);

        char a;
        if((a = getSymbol())!= ' ') {
            graphics.fillRectangle(new TerminalPosition(getColumn(80),
                            getRows(60) + 1), new TerminalSize(getColumn(getColumn(3)), 1),
                    a);
        }

        graphics.putString(getColumn(75), getRows(60) +2, getName());
        graphics.putString(getColumn(80), getRows(60) +3, getValue());
    }

    @Override
    void drawSwing(Graphics graphics) {
        drawSwing(graphics,20,40,55,color);
        if (!getValue().equals("") && this.inventory.getElement().getImage() != null) {
            drawImageInBoxSwing(this.inventory.getElement().getImage(), 55, graphics);
        }
    }

    @Override
    public String getValue() {

        if(inventory.getElement() == null)
            return "";

        return String.valueOf(inventory.getElement().getValue());
    }

    @Override
    public String getName() {
        if(inventory.getView() == null)
            return "backpack";

        return inventory.getView().getName();
    }


    char getSymbol() {
        if(inventory.getElement() == null)
            return ' ';

        return inventory.getView().getSymbol();
    }





}
