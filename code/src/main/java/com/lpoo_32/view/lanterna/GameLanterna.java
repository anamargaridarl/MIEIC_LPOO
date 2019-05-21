package com.lpoo_32.view.lanterna;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.*;
import com.lpoo_32.view.ElementView;

import java.io.IOException;
import java.util.*;


public class GameLanterna extends Display{

    private Elements elements;
    private List<ElementView> generalView;
    private int index;
    private TextGraphics graphics;
    private PlayerViewLanterna player;
    public static final int width = 60;
    public static final int height = 50;

    public GameLanterna(DisplayProps props, PlayerModel player, Elements elements) {
        super(props.getScreen());
        this.generalView = new LinkedList<>();
        this.index = 0;
        this.player = new PlayerViewLanterna(player);
        this.elements = elements;
        this.graphics =  this.screen.newTextGraphics();
        this.setInitialProps();
    }


    @Override
    public void draw() throws IOException {
        this.screen.clear();


        graphics.setBackgroundColor(TextColor.Factory.fromString("#91c474"));
        graphics.fillRectangle(new TerminalPosition(0, 0),
                                new TerminalSize(ScreenSize.instance().getColumn(60),
                                ScreenSize.instance().getRows(50)), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        /*graphics.putString(new TerminalPosition(ScreenSize.instance().getColumn(20),
                            ScreenSize.instance().getRows(20)), "@");*/
        int initialX = index%3 * GameLanterna.width/4;
        int initialY = index/3 * GameLanterna.height/4;

        for(int i = initialX; i < initialX + GameLanterna.width/4; i++){
            for(int j = initialY; j < initialY + GameLanterna.height/4; j++){
                if(this.elements.getViewByCoord(i, j) != null){
                    this.elements.getViewByCoord(i, j).draw(graphics);
                }
            }
        }

        for(ElementView drawable: this.generalView)
            drawable.draw(graphics);

        this.screen.refresh();
    }

    private void setInitialProps(){
        this.generalView.add(new StatusBarLanterna(player.getPlayer().getHealth(), "#990000", 10));
        this.generalView.add(new StatusBarLanterna(player.getPlayer().getFood(), "#3CB371", 14));
        this.generalView.add(new StatusBarLanterna(player.getPlayer().getWater(), "#66ccff", 18));
        this.generalView.add(this.player);
        this.generalView.add(new InventoryViewLanterna(this.player.getPlayer().getInventory(), "#91c474"));
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
