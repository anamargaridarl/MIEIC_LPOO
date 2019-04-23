package com.lpoo_32.view;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.FoodModel;
import com.lpoo_32.model.Inventory;
import com.lpoo_32.model.WaterModel;


public class InventoryView implements ElementView {

    private Inventory inventory;
    private final TextColor color;
    private TextGraphics graphics;
    //private int height;

    public InventoryView(Inventory inventory, String hexColor, TextGraphics graphics) {
        this.inventory = inventory;
        this.color = TextColor.Factory.fromString(hexColor);
        this.graphics = graphics;
        //this.height = height;

    }


    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(color);


        graphics.fillRectangle(new TerminalPosition(getColumn(70),
                getRows()), new TerminalSize(getColumn(getColumn(20)), 4),
                Symbols.BLOCK_SOLID);

        char a;
        if((a = getSimbols())!= ' ') {
            graphics.fillRectangle(new TerminalPosition(getColumn(80),
                            getRows() + 1), new TerminalSize(getColumn(getColumn(3)), 1),
                    a);
        }

        graphics.putString(getColumn(75), getRows() +2, getName());
        graphics.putString(getColumn(80), getRows() +3, getValue());





    }

    private String getValue() {

        if(inventory.getElement() == null)
            return "";

        return String.valueOf(inventory.getElement().getValue());
    }

    private String getName() {
        if(inventory.getElement() == null)
            return "";
        if(inventory.getElement() instanceof FoodModel)
            return "Food";
        if(inventory.getElement() instanceof WaterModel)
            return  "Water";

        return "";
    }


    private char getSimbols() {
        if(inventory.getElement() == null)
            return ' ';
        if(inventory.getElement() instanceof FoodModel)
            return  Symbols.HEART;
        if(inventory.getElement() instanceof WaterModel)
            return  Symbols.HEART; //TO DO: what symbol to use for water

        return ' ';
    }


    private int getRows() {
        return ScreenSize.instance().getRows(26);
    }

    private int getColumn(int columns) {
        return ScreenSize.instance().getColumn(columns);
    }


}
