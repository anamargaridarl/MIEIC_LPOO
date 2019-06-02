package com.lpoo_32.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


class MenuSwingGUI extends JFrame {


    private final Selector selector;
    private final Options options = new Options();
    private final Image image;
    private final Graphics buffer;
    private BufferedImage background;

    public void draw(){
        buffer.clearRect(0, 0, GameSwing.ScreenWidth, GameSwing.ScreenHeight);
        buffer.drawImage(background, 0, 0, GameSwing.ScreenWidth, GameSwing.ScreenHeight, null);
        selector.drawSwing(buffer);
        options.drawSwing(buffer);
        getGraphics().drawImage(image, 0, 0, null);
    }


    public MenuSwingGUI(Selector selector)
    {
        this.selector = selector;
        JPanel root = new JPanel();
        this.setVisible(true);
        setTitle("Menu");
        add(root);
        setSize(GameSwing.ScreenWidth,    GameSwing.ScreenHeight);
        this.image = createImage(GameSwing.ScreenWidth, GameSwing.ScreenHeight);
        this.buffer = image.getGraphics();
        this.addKeyListener(new MenuSwingKeyboard(this.selector));
        URL resource = getClass().getResource("/" + "background.png");
        try{
            this.background = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
