package com.lpoo_32.view;

import javax.swing.*;

public class GameSwing {
    public GameSwing(){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        new PlayerView(null).drawSwing(frame.getGraphics());
    }
}
