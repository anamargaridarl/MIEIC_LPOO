package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.PlayerModel;

import java.awt.*;

public class PlayerView extends ElementView {

    private final PlayerModel player;

    public PlayerView(PlayerModel player){
        super("stickman.png");
        this.player = player;
    }

    @Override
    public void drawLanterna(TextGraphics graphics) {
        this.drawLanterna(
                graphics,
                "#91c474",
                "#000000",
                this.player.getPosition().getTerminalPosition(),
                new TerminalSize(1, 1),
                Symbols.DIAMOND
        );

        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#91c474"));

        graphics.fillRectangle(new TerminalPosition(getColumn(70),
                        getRows()), new TerminalSize(getColumn(getColumn(20)), 4),
                Symbols.BLOCK_SOLID);


        graphics.putString(getColumn(75), getRows() +2, getName());
        graphics.putString(getColumn(80), getRows() +3, getValue());


    }

    @Override
    void drawSwing(Graphics graphics) {
        this.drawSwing(graphics,
                this.getPlayer().getPosition().getSwingX(),
                this.getPlayer().getPosition().getSwingY()
        );
    }

    public PlayerModel getPlayer()
    {
        return player;
    }

    private int getRows() {
        return ScreenSize.instance().getRows(80);
    }

    private int getColumn(int columns) {
        return ScreenSize.instance().getColumn(columns);
    }


    private String getValue() {

        if(this.player.getWeapon() == null)
            return "";

        return String.valueOf(this.player.getWeapon().getValue());
    }


    private String getName() {

        if(this.player.getWeapon() == null)
            return " ";
        else
            return "Weapon";

    }




    }
