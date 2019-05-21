package com.lpoo_32.view.lanterna;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.FoodModel;
import com.lpoo_32.model.InteractableElement;
import com.lpoo_32.model.SpikesModel;
import com.lpoo_32.view.InteractableElementView;

public class SpikesView extends InteractableElementView {

    private SpikesModel spikes;

    public SpikesView(SpikesModel spikes){
        super(spikes);
        this.spikes = spikes;
    }

    @Override
    public void draw(TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#91c474"));

        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        graphics.fillRectangle(
                this.spikes.getPos().getTerminalPosition(),
                new TerminalSize(1, 1),
                Symbols.SPADES
        );
    }

    public SpikesModel getSpikes()
    {
        return spikes;
    }



}
