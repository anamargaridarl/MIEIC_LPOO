package com.lpoo_32.view;

import com.lpoo_32.controller.ExceptionableRunnable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class HelpMenuGUI implements ExceptionableRunnable {

    private Graphics buffer;
    private Image image;
    private BufferedImage background;
    private BufferedImage table;
    private JFrame mainScreen;
    private boolean running = true;

    public HelpMenuGUI(JFrame mainScreen) {
        this.mainScreen = mainScreen;
        URL background = getClass().getResource("/" + "background.png");
        try{
            this.background = ImageIO.read(background);
        } catch (IOException e) {
            e.printStackTrace();
        }
        URL resource = getClass().getResource("/" + "table.png");
        try{
            this.table = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.image = this.mainScreen.createImage(GameSwing.ScreenWidth, GameSwing.ScreenHeight);
        this.buffer = image.getGraphics();
    }


    @Override
    public void run() {
        while (running){
            try {
                this.draw();
                Thread.sleep(1000/30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void draw() {
        this.buffer.clearRect(0, 0, GameSwing.ScreenWidth, GameSwing.ScreenHeight);
        this.buffer.drawImage(background, 0, 0, null);
        this.buffer.drawImage(table, 300, 300, null);
        this.mainScreen.getGraphics().drawImage(this.image, 0, 0, null);
    }

    public void stopDrawing() {
        running = false;
    }
}