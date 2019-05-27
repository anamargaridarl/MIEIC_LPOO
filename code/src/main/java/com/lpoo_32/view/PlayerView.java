package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.PlayerModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class PlayerView extends ElementView {

    private final PlayerModel player;
    private final String color;
    private BufferedImage weapon;

    public PlayerView(PlayerModel player, String color){
        super(player);
        this.player = player;
        this.color = color;
        URL resource = getClass().getResource("/sword.png");
        try {
            weapon = ImageIO.read(resource);
        }catch (IOException e){
            System.out.println("Failed to load weapon!");
        }
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
        graphics.setForegroundColor(TextColor.Factory.fromString(color));

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
        graphics.setColor(Color.decode(color));
        graphics.fillRect(GameSwing.getWidth() + 150, 100, 90, 100);
        graphics.setColor(Color.BLACK);
        graphics.drawString(getName(), GameSwing.getWidth() + 170, 110);
        graphics.drawString(getValue(), GameSwing.getWidth() + 188, 130);
        if(weapon != null && !getValue().equals("")){
            graphics.drawImage(weapon, GameSwing.getWidth() + 180, 150,30, 30, null);
        }
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
