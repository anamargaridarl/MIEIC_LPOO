package com.lpoo_32.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.*;

import java.io.IOException;
import java.util.*;


public class GameLanterna extends Game{

    private Elements elements;
    private TextGraphics graphics;
    private Screen screen;

    public GameLanterna(Screen screen, PlayerModel player, Elements elements) {
        super(player);
        this.screen = screen;
        this.elements = elements;
        this.graphics =  this.screen.newTextGraphics();
        this.setInitialProps();
    }


    @Override
    public void draw() throws IOException, HungerOVF, ThirstOVF, ThirstRestored, RightScreen, DownScreen, LeftScreen, HealthOVF, HungerRestored, UpScreen, Bedtime {
        this.screen.clear();


        graphics.setBackgroundColor(TextColor.Factory.fromString("#91c474"));
        graphics.fillRectangle(new TerminalPosition(0, 0),
                                new TerminalSize(ScreenSize.instance().getColumn(60),
                                ScreenSize.instance().getRows(50)), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        int initialX = getIndex() %3 * Game.width/4;
        int initialY = getIndex() /3 * Game.height/4;

        for(int i = initialX; i < initialX + Game.width/4; i++){
            for(int j = initialY; j < initialY + Game.height/4; j++){
                if(this.elements.getViewByCoord(i, j) != null){
                    this.elements.getViewByCoord(i, j).drawLanterna(graphics);
                }
            }
        }

        for(ElementView drawable: this.generalView)
            drawable.drawLanterna(graphics);

        this.screen.refresh();
    }


    void setInitialProps(){
        this.generalView.add(new StatusBar(player.getPlayer().getHealth(), "#990000", 10));
        this.generalView.add(new StatusBar(player.getPlayer().getFood(), "#3CB371", 14));
        this.generalView.add(new StatusBar(player.getPlayer().getWater(), "#66ccff", 18));
        this.generalView.add(this.player);
        this.generalView.add(new InventoryView(this.player.getPlayer().getInventory(), "#91c474"));
    }
}
