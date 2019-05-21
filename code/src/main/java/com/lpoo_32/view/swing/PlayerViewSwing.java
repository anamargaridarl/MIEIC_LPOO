package com.lpoo_32.view.swing;

import com.lpoo_32.model.PlayerModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class PlayerViewSwing extends SwingComponent {
    private PlayerModel player;

    PlayerViewSwing(PlayerModel player){
        this.player = player;
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics, "stickman.png");
    }

    public PlayerModel getPlayer() {
        return player;
    }
}
