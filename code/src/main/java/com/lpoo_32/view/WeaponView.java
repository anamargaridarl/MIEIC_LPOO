package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.CatchableElement;
import com.lpoo_32.model.WeaponModel;

import java.awt.*;

public class WeaponView extends CatchableView {

    private final WeaponModel weapon;

    public WeaponView(WeaponModel weaponModel) {
        super(weaponModel, "sword.png");
        weapon = weaponModel;
    }

    @Override
    public CatchableElement getElement() {
        return weapon;
    }

    @Override
    public char getSymbol() {
        return Symbols.CLUB;
    }

    @Override
    public String getName() {
        return "Weapon";
    }

    @Override
    public void drawLanterna(TextGraphics graphics) {
        this.drawLanterna(
                graphics,
                "#91c474",
                "#000000",
                this.weapon.getPos().getTerminalPosition(),
                new TerminalSize(1, 1),
                this.getSymbol()
                );
    }

    @Override
    void drawSwing(Graphics graphics) {
        this.drawSwing(
                graphics,
                this.weapon.getPos().getSwingX(),
                this.weapon.getPos().getSwingY()
        );
    }
}
