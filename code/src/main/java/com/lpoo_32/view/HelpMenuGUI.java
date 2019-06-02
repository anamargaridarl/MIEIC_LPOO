package com.lpoo_32.view;

import com.lpoo_32.controller.ExceptionableRunnable;
import net.miginfocom.swing.MigLayout;

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
    private JFrame mainScreen;
    private JTable table;
    private boolean running = true;

    String[] columnNames = {"Actions",
            "Explanation",};
    Object[] [] data = {
            {"Arrows", "Move Around"
                    },
            {"Collect Items", "F"},
            {"Use Item from Inventory", "E"
                    },
            {"Use Item from Ground", "T"},
            {"Move elements in inventory", "1/2",
                    },
            {"Attack Monsters", "A/S/D/w",
            }
    };


    public HelpMenuGUI(JFrame mainScreen) {
        this.mainScreen = mainScreen;
        table = new JTable(data, columnNames);
        URL resource = getClass().getResource("/" + "background.png");
        try{
            this.background = ImageIO.read(resource);
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
        this.table.paint(this.buffer);
        this.mainScreen.getGraphics().drawImage(this.image, 0, 0, null);
    }

    public void stopDrawing() {
        running = false;
    }
}