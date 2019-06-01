package com.lpoo_32.view;

import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.Elements;
import com.lpoo_32.model.HealthStatus;
import com.lpoo_32.model.PlayerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class GameSwing extends Game{
    private final Elements elements;
    private Graphics graphics;
    public static int ScreenWidth = 1366;
    public static int ScreenHeight = 768;
    private JPanel frame;

    public GameSwing(JPanel frame, PlayerModel player, Elements elements){
        super(player);
        this.elements = elements;
        this.frame = frame;
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.removeAll();
        Component component = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        System.out.println(component);
        this.graphics = frame.getGraphics();
        this.setInitialProps();
    }

    @Override
    public void draw() throws HungerOVF, ThirstOVF, ThirstRestored, RightScreen, DownScreen, LeftScreen, HealthOVF, HungerRestored, UpScreen, Bedtime {
        graphics.clearRect(0, 0, ScreenWidth, ScreenHeight);
        graphics.drawRect(0, 0, getWidth(), getHeight());

        int initialX = getIndex() %3 * Game.width/4;
        int initialY = getIndex() /3 * Game.height/4;


        for(int i = initialX; i < initialX + Game.width/4; i++){
            for(int j = initialY; j < initialY + Game.height/4; j++){
                if(this.elements.getViewByCoord(i, j) != null){
                    this.elements.getViewByCoord(i, j).drawSwing(graphics);
                }
            }
        }

        for(ElementView drawable: this.generalView)
            drawable.drawSwing(graphics);

        frame.setVisible(true);
    }

    static public int getHeight() {
        return (ScreenHeight* Game.height)/100 + 35;
    }

    static public int getWidth() {
        return (ScreenWidth* Game.width)/100 + 30;
    }

    void setInitialProps(){
        this.generalView.add(new StatusBar(player.getPlayer().getHealth(), "#990000", 30));
        this.generalView.add(new StatusBar(player.getPlayer().getFood(), "#3CB371", 55));
        this.generalView.add(new StatusBar(player.getPlayer().getWater(), "#66ccff", 80));
        this.generalView.add(this.player);
        this.generalView.add(new InventoryView(this.player.getPlayer().getInventory(), "#91c474"));
    }
}
