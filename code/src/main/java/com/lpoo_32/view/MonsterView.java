package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.MonsterModel;

import java.awt.*;

public class MonsterView extends InteractableElementView {

    private final MonsterModel monster;

    public MonsterView(MonsterModel monster){
        super(monster, "bed.png");
        this.monster = monster;
    }

    @Override
    public void drawLanterna(TextGraphics graphics) throws HungerOVF, UpScreen, ThirstOVF, HealthOVF, ThirstRestored, HungerRestored, LeftScreen, RightScreen, DownScreen, Bedtime {

        this.monster.updateMove(this);


        graphics.setBackgroundColor(TextColor.Factory.fromString("#91c474"));

        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        graphics.fillRectangle(
                this.monster.getPos().getTerminalPosition(),
                new TerminalSize(1, 1),
                Symbols.FACE_BLACK
        );
    }

    @Override
    void drawSwing(Graphics graphics) throws HungerOVF, UpScreen, ThirstOVF, HealthOVF, ThirstRestored, HungerRestored, LeftScreen, RightScreen, DownScreen {
        this.drawSwing(
                graphics,
                this.monster.getPos().getSwingX(),
                this.monster.getPos().getSwingY()
                );
    }

    public MonsterModel getMonster()
    {
        return monster;
    }

}
