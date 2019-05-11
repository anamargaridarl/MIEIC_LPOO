package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.MonsterModel;

public class MonsterView extends InteractableElementView {

    private final MonsterModel monster;

    public MonsterView(MonsterModel monster){
        super(monster);
        this.monster = monster;
    }

    @Override
    public void draw(TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#91c474"));

        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        graphics.fillRectangle(
                this.monster.getPos().getTerminalPosition(),
                new TerminalSize(1, 1),
                Symbols.FACE_BLACK
        );
    }

    public MonsterModel getMonster()
    {
        return monster;
    }

}
