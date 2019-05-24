package com.lpoo_32.view.swing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class SwingComponent extends JComponent {

    public void paintComponent(Graphics graphics, String name) {
        super.paintComponent(graphics);
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
