package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.PlayerModel;

public class PlayerView implements ElementView {

    private final PlayerModel player;

    PlayerView(PlayerModel player){
        this.player = player;
    }

    @Override
    public void draw(TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#7CFC00"));

        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        graphics.fillRectangle(
                new TerminalPosition(player.getPosition().getX(), player.getPosition().getY()),
                new TerminalSize(1, 1),
                Symbols.DIAMOND
        );
    }

    public PlayerModel getPlayer()
    {
        return player;
    }
}
