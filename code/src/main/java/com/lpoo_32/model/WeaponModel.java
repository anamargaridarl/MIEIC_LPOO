package com.lpoo_32.model;

import com.lpoo_32.exceptions.*;

public class WeaponModel extends CatchableElement {

    public WeaponModel(Position pos, int value) {
        super(pos, value);
    }

    @Override
    public void interact(PlayerModel player) throws HealthOVF, HungerRestored, HungerOVF, ThirstRestored, ThirstOVF {
            player.getHealth().decreaseValue(10);
    }

    public void interactMonster(MonsterModel monster) {
        monster.decreaseValue(this.getValue());
    }

}
