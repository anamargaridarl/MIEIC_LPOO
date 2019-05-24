package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.InteractableElement;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.view.ElementView;

import java.awt.*;

public class PlayerView extends ElementView {

    private final PlayerModel player;

    public PlayerView(PlayerModel player){
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

    }

    @Override
    void drawSwing(Graphics graphics) {
        this.drawSwing(graphics, "stickman.png");
    }

    public PlayerModel getPlayer()
    {
        return player;
    }
}
