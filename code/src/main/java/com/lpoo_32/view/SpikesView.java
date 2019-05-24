package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.FoodModel;
import com.lpoo_32.model.InteractableElement;
import com.lpoo_32.model.SpikesModel;
import com.lpoo_32.view.InteractableElementView;

import java.awt.*;

public class SpikesView extends InteractableElementView {

    private SpikesModel spikes;

    public SpikesView(SpikesModel spikes){
        super(spikes);
        this.spikes = spikes;
    }

    @Override
    public void drawLanterna(TextGraphics graphics) {
        this.drawLanterna(
                graphics,
                "#91c474",
                "#000000",
                this.spikes.getPos().getTerminalPosition(),
                new TerminalSize(1, 1),
                Symbols.SPADES
        );
    }

    @Override
    void drawSwing(Graphics graphics) {
        this.drawSwing(graphics, "spikes.png");
    }

    public SpikesModel getSpikes()
    {
        return spikes;
    }



}
