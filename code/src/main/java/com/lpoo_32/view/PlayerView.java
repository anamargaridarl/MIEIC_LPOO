package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.InteractableElement;
import com.lpoo_32.model.PlayerModel;

public class PlayerView implements ElementView {

    private final PlayerModel player;

    public PlayerView(PlayerModel player){
        this.player = player;
    }

    @Override
    public void draw(TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#91c474"));

        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        graphics.fillRectangle(
                this.player.getPosition().getTerminalPosition(),
                new TerminalSize(1, 1),
                Symbols.DIAMOND
        );


    }


    private int getRows() {
        return ScreenSize.instance().getRows(26);
    }

    private int getColumn(int columns) {
        return ScreenSize.instance().getColumn(columns);
    }

    public PlayerModel getPlayer()
    {
        return player;
    }
}
