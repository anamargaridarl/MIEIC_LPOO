package com.lpoo_32.view;

import com.lpoo_32.controller.ExceptionableRunnable;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HelpMenuGUI implements ExceptionableRunnable {

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
        this.mainScreen.getGraphics().clearRect(0, 0, GameSwing.ScreenWidth, GameSwing.ScreenHeight);
        this.table.paint(this.mainScreen.getGraphics());
    }

    public void stopDrawing() {
        running = false;
    }
}