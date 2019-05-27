package com.lpoo_32.view;

import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.OutOfBoundaries;
import com.lpoo_32.model.Elements;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.model.Position;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.IOException;


public class MenuSwingGUI extends JFrame {


    private JPanel root;
    private String[] choices = { "Game", "Instructions", "Exit" };
    JList<String> list = new JList(choices);


    MouseListener mouseListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent mouseEvent) {
            JList theList = (JList) mouseEvent.getSource();
            if (mouseEvent.getClickCount() == 2) {
                int index = theList.locationToIndex(mouseEvent.getPoint());
                if (index >= 0) {
                    Object o = theList.getModel().getElementAt(index);
                    System.out.println("Double-clicked on: " + o.toString());
                }
            }
        }
    };

    public void manageList()
    {
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setSelectedIndex(3);
        list.setFixedCellHeight(100);
        list.setFixedCellWidth(50);
        this.getContentPane().add(list);
        list.addListSelectionListener((listSelectionEvent) -> {
                System.out.print("First index: " + listSelectionEvent.getFirstIndex());
                System.out.print(", Last index: " + listSelectionEvent.getLastIndex());
                boolean adjust = listSelectionEvent.getValueIsAdjusting();
                System.out.println(", Adjusting? " + adjust);
                if (!adjust) {
                    JList list = (JList) listSelectionEvent.getSource();

                    int[] selections = list.getSelectedIndices();
                    Object[] selectionValues = list.getSelectedValues();
                    for (int i = 0, n = selections.length; i < n; i++) {
                        if (i == 0) {
                            System.out.print("  Selections: ");
                        }
                        System.out.print(selections[i] + "/" + selectionValues[i] + " ");
                        if(selections[i] == 0)
                        {
                            Elements elements = new Elements();
                            PlayerModel model = null;
                            try {
                                model = new PlayerModel(new Position(2,2, Game.width/4, Game.height/4, 0));
                            } catch (OutOfBoundaries outOfBoundaries) {
                                outOfBoundaries.printStackTrace();
                            }
                            JFrame frame = new JFrame();
                            GameController game = null;
                            try {
                                game = new GameController(
                                        elements,
                                        model,
                                        new GameSwing(frame, model, elements)
                                );
                            } catch (OutOfBoundaries outOfBoundaries) {
                                outOfBoundaries.printStackTrace();
                            }
                            this.list.removeMouseListener(mouseListener);
                            frame.addKeyListener(new SwingKeyboard(game));
                            this.addKeyListener(new SwingKeyboard(game));
                            root.addKeyListener(new SwingKeyboard(game));
                            this.setVisible(false);
                            try {
                                game.run();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                            this.setVisible(true);
                            this.pack();
                            this.manageList();
                        System.out.println("Stopping game");
                        }
                        else if(selections[i] == 1)
                        {
                            this.setVisible(false);
                            HelpMenuGUI help = new HelpMenuGUI(this);
                            help.setVisible(true);
                        }
                        else if (selections[i] == 2){
                            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

                        }

                    }
                    System.out.println();
                }
        });
        list.addMouseListener(mouseListener);
    }


    public MenuSwingGUI()
    {
        this.root = new JPanel();
        add(root);
        setTitle("Menu");
        setSize(400,330);
        manageList();
    }



}
