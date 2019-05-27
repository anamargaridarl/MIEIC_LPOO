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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.IOException;


public class MenuSwingGUI extends JFrame {


    private JPanel root;
    String[] choices = { "Game", "Instructions", "Exit" };
    JList<String> list = new JList(choices);
    SharedListSelectionHandler sharedListSelectionHandler;


    class SharedListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent listSelectionEvent) {
            System.out.print("First index: " + listSelectionEvent.getFirstIndex());
            System.out.print(", Last index: " + listSelectionEvent.getLastIndex());
            boolean adjust = listSelectionEvent.getValueIsAdjusting();
            System.out.println(", Adjusting? " + adjust);
            if (!adjust) {
                JList list = (JList) listSelectionEvent.getSource();

                int selections[] = list.getSelectedIndices();
                Object selectionValues[] = list.getSelectedValues();
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
                        frame.addKeyListener(new SwingKeyboard(game));
                        try {
                            game.run();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            System.out.println("Stopping game");
                    }
                }
                System.out.println();
            }
        }
    };


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
        this.getContentPane().add(list);
        list.addListSelectionListener(sharedListSelectionHandler);
        list.addMouseListener(mouseListener);


    }


    public MenuSwingGUI()
    {
        add(root);
        setTitle("Menu");
        setSize(400,500);
        sharedListSelectionHandler = new SharedListSelectionHandler();
        manageList();
    }



}