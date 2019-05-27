package com.lpoo_32.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class HelpMenuGUI extends JFrame {

    private JTable table;
    private JPanel root;
    JButton button1;

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

    public HelpMenuGUI() {
        table = new JTable(data, columnNames);
        button1 = new JButton("GoBack");
        add(root);
        setTitle("Menu");
        setSize(400,330);
        this.getContentPane().add(table);
        //this.getContentPane().add(button1);
    }






}