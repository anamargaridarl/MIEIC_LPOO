package com.lpoo_32.view;

import com.lpoo_32.model.Elements;
import com.lpoo_32.model.PlayerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameSwing extends Game{
    private final PlayerView player;
    private final Elements elements;
    private Graphics graphics;
    public static int ScreenWidth = 1366;
    public static int ScreenHeight = 768;


    public GameSwing(JFrame frame, PlayerModel player, Elements elements){
        super();
        this.player = new PlayerView(player);
        this.elements = elements;
        frame.setVisible(true);
        frame.setFocusable(true);
        this.graphics = frame.getGraphics();
    }

    @Override
    public void draw() {
        graphics.clearRect(0, 0, ScreenWidth, ScreenHeight);
        graphics.drawRect(0, 0, (ScreenWidth*Game.width)/100 + 30, (ScreenHeight*Game.height)/100 + 35);

        int initialX = getIndex() %3 * Game.width/4;
        int initialY = getIndex() /3 * Game.height/4;

        this.player.drawSwing(graphics);

        for(int i = initialX; i < initialX + Game.width/4; i++){
            for(int j = initialY; j < initialY + Game.height/4; j++){
                if(this.elements.getViewByCoord(i, j) != null){
                    this.elements.getViewByCoord(i, j).drawSwing(graphics);
                }
            }
        }

    }
}
