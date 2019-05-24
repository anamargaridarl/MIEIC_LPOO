package com.lpoo_32.view.swing;

import com.lpoo_32.model.PlayerModel;

import javax.swing.*;

public class GameSwing {
    public GameSwing(){
        JFrame frame = new JFrame("Hello World");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
        frame.setVisible(true);
        FoodViewSwing food = new FoodViewSwing(null);
        food.paintComponent(frame.getGraphics());
    }
}
