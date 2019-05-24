package com.lpoo_32.view;

import com.lpoo_32.model.Elements;
import com.lpoo_32.model.PlayerModel;

import javax.swing.*;
import java.awt.*;

public class GameSwing extends Game{
    private final PlayerModel player;
    private final Elements elements;
    private Graphics graphics;

    public GameSwing(DisplayProps props, PlayerModel player, Elements elements){
        super();
        this.player = player;
        this.elements = elements;
        JFrame frame = props.getFrame();
        frame.setVisible(true);
        this.graphics = frame.getGraphics();
    }

    @Override
    void draw() {
        graphics.dispose();
    }
}
