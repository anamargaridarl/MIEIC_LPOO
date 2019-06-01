package com.lpoo_32.model;

import com.lpoo_32.exceptions.*;

public class WeaponModel extends CatchableElement {

    public WeaponModel(Position pos, int value) {
        super(pos, value, "sword.png");
    }

    @Override
    public boolean interact(PlayerModel player) throws HealthOVF, HungerOVF, ThirstOVF {
        player.getHealth().decreaseValue(10);
        return true;
    }

    public void interactMonster(MonsterModel monster) {
        monster.decreaseValue(this.getValue());
    }

}
