package com.lpoo_32.view;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.Inventory;

import java.awt.*;


public class InventoryView extends ElementView {

    private Inventory inventory;
    private final String color;
    //private int height;

    public InventoryView(Inventory inventory, String hexColor) {
        super("bed.png");
        this.inventory = inventory;
        this.color = hexColor;
    }


    @Override
    public void drawLanterna(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString(color));


        graphics.fillRectangle(new TerminalPosition(getColumn(70),
                getRows()), new TerminalSize(getColumn(getColumn(20)), 4),
                Symbols.BLOCK_SOLID);

        char a;
        if((a = getSymbol())!= ' ') {
            graphics.fillRectangle(new TerminalPosition(getColumn(80),
                            getRows() + 1), new TerminalSize(getColumn(getColumn(3)), 1),
                    a);
        }

        graphics.putString(getColumn(75), getRows() +2, getName());
        graphics.putString(getColumn(80), getRows() +3, getValue());
    }

    @Override
    void drawSwing(Graphics graphics) {
        graphics.setColor(Color.decode(this.color));
        graphics.fillRect(GameSwing.getWidth() + 20, 100, 90, 100);
        graphics.setColor(Color.BLACK);
        graphics.drawString(getName(), GameSwing.getWidth() + 40, 110);
        graphics.drawString(getValue(), GameSwing.getWidth() + 40, 130);
//        graphics.drawImage(this.inventory.getElement())
        //TODO: Image of item

    }

    private String getValue() {

        if(inventory.getElement() == null)
            return "";

        return String.valueOf(inventory.getElement().getValue());
    }

    private String getName() {
        if(inventory.getView() == null)
            return "backpack";

        return inventory.getView().getName();
    }


    char getSymbol() {
        if(inventory.getElement() == null)
            return ' ';

        return inventory.getView().getSymbol();
    }


    private int getRows() {
        return ScreenSize.instance().getRows(60);
    }

    private int getColumn(int columns) {
        return ScreenSize.instance().getColumn(columns);
    }


}
