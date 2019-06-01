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

public class PlayerView extends BoxView {

    private final PlayerModel player;
    private final String color;
    private BufferedImage weapon;

    public PlayerView(PlayerModel player, String color) {
        super(player);
        this.player = player;
        this.color = color;
        URL resource = getClass().getResource("/sword.png");
        try {
            weapon = ImageIO.read(resource);
        } catch (IOException e) {
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
                        getRows(80)), new TerminalSize(getColumn(getColumn(20)), 4),
                Symbols.BLOCK_SOLID);


        graphics.putString(getColumn(75), getRows(80) + 2, getName());
        graphics.putString(getColumn(80), getRows(80) + 3, getValue());


    }

    @Override
    void drawSwing(Graphics graphics) {
        this.drawSwing(graphics,
                this.getPlayer().getPosition().getSwingX(),
                this.getPlayer().getPosition().getSwingY()
        );
        drawSwing(graphics,150,170,188,color);
        if (weapon != null && !getValue().equals("")) {
            drawImageInBoxSwing(weapon,180,graphics);
        }
    }

    public PlayerModel getPlayer() {
        return player;
    }


    @Override
    public String getValue() {

        if (this.player.getWeapon() == null)
            return "";

        return String.valueOf(this.player.getWeapon().getValue());
    }


    @Override
    public String getName() {

        if (this.player.getWeapon() == null)
            return " ";
        else
            return "Weapon";

    }


}
