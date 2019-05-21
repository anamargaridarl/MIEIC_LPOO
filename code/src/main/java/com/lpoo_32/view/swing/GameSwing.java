package com.lpoo_32.view.swing;

import javax.swing.*;

public class GameSwing {
    public GameSwing(){
        JFrame frame = new JFrame("Hello World");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

        JLabel label = new JLabel("Hello meias");
        frame.getContentPane().add(new PlayerViewSwing(null));
        frame.pack();
        frame.setVisible(true);
    }
}
