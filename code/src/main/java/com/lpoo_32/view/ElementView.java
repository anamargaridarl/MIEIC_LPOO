package com.lpoo_32.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.InteractableElement;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public abstract class ElementView  {
    void drawLanterna(TextGraphics graphics, String backgroundColor, String foregroundColor, TerminalPosition pos, TerminalSize size, char symbol){
        graphics.setBackgroundColor(TextColor.Factory.fromString(backgroundColor));

        graphics.setForegroundColor(TextColor.Factory.fromString(foregroundColor));

        graphics.fillRectangle(
                pos,
                size,
                symbol
        );
    }
    abstract void drawLanterna(TextGraphics graphics);
    abstract void drawSwing(Graphics graphics);

    void drawSwing(Graphics graphics, String name){
        URL resource = getClass().getResource("/" + name);
        BufferedImage image;
        try{
            image = ImageIO.read(resource);
            graphics.drawImage(image, 0, 40,40, 40, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
