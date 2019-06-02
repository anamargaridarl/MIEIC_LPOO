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
    private Graphics buffer;
    private Image bufferImage;

    public GameSwing(JFrame frame, PlayerModel player, Elements elements){
        super(player);
        this.elements = elements;
        Component component = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        System.out.println(component);
        this.graphics = frame.getGraphics();
        this.setInitialProps();
        bufferImage = frame.createImage(ScreenWidth, ScreenHeight);
        buffer = bufferImage.getGraphics();
    }

    @Override
    public void draw() throws HungerOVF, ThirstOVF, ThirstRestored, RightScreen, DownScreen, LeftScreen, HealthOVF, HungerRestored, UpScreen, Bedtime {
        buffer.clearRect(0, 0, ScreenWidth, ScreenHeight);
        buffer.drawRect(0, 0, getWidth(), getHeight());
        int initialX = getIndex() %3 * Game.width/4;
        int initialY = getIndex() /3 * Game.height/4;


        for(int i = initialX; i < initialX + Game.width/4; i++){
            for(int j = initialY; j < initialY + Game.height/4; j++){
                if(this.elements.getViewByCoord(i, j) != null){
                    this.elements.getViewByCoord(i, j).drawSwing(buffer);
                }
            }
        }

        for(ElementView drawable: this.generalView)
            drawable.drawSwing(buffer);

        buffer.clearRect(this.player.getPlayer().getPosition().getSwingX(), this.player.getPlayer().getPosition().getSwingY() + 20, 30, 30);
        this.player.drawSwing(buffer);
        graphics.drawImage(bufferImage, 0, 0, null);
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
        this.generalView.add(new InventoryView(this.player.getPlayer().getInventory(), "#91c474"));
    }
}
