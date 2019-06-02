package com.lpoo_32.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpMenuGUI extends JFrame {

    private JTable table;
    private JPanel root;

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
        this.root = new JPanel();
        table = new JTable(data, columnNames);
        this.setLayout(new MigLayout("wrap 300"));
        add(root);
        setTitle("Menu");
        setSize(400,330);
        this.getContentPane().add(table, "span 300");
        JButton button = new JButton();
        button.addActionListener(actionEvent -> {
            mainScreen.setVisible(true);
            this.setVisible(false);
        });
        this.getContentPane().add(button, "wrap 300");
    }






}