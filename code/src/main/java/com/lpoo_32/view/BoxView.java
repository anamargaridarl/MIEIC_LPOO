package com.lpoo_32.view;

import com.lpoo_32.model.PlayerModel;

import java.awt.*;
import java.awt.image.BufferedImage;


abstract class BoxView extends ElementView {


    BoxView(PlayerModel model) {
        super(model);
    }

    BoxView() {
        super();
    }


    protected abstract String getName();

    protected abstract String getValue();


    int getColumn(int columns) {
        return ScreenSize.instance().getColumn(columns);
    }

    int getRows(int rows) {
        return ScreenSize.instance().getRows(rows);
    }

    void drawSwing(Graphics graphics, int width1, int width2, int width3, String color)
    {
        graphics.setColor(Color.decode(color));
        graphics.fillRect(GameSwing.getWidth() + width1, 100, 90, 100);
        graphics.setColor(Color.BLACK);
        graphics.drawString(getName(), GameSwing.getWidth() + width2, 110);
        graphics.drawString(getValue(), GameSwing.getWidth() + width3, 130);
    }


    void drawImageInBoxSwing(BufferedImage image, int width1, Graphics graphics)
    {
        graphics.drawImage(image,
                GameSwing.getWidth() + width1,
                150, 30, 30, null);
    }



}