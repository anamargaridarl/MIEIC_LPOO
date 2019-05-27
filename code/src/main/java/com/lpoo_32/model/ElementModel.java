package com.lpoo_32.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public abstract class ElementModel {
    private BufferedImage image;

    ElementModel(String name){
        URL resource = getClass().getResource("/" + name);
        try{
            image = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}
