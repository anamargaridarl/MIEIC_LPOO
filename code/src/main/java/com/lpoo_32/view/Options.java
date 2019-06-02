package com.lpoo_32.view;

import java.awt.*;

public class Options {
    public void drawSwing(Graphics graphics){
        graphics.setColor(Color.decode("#e4394a"));
        graphics.drawString("Game", 250, 200);
        graphics.drawString("Help", 250, 300);
        graphics.drawString("Exit", 250, 400);
    }
}
