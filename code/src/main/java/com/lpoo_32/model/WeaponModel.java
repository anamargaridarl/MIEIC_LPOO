package com.lpoo_32.model;

import com.lpoo_32.exceptions.*;

public class WeaponModel extends CatchableElement {

    MonsterModel lastMonster;
    WeaponModel(Position pos, int value) {
        super(pos, value);
    }

    public void setLastMonster(MonsterModel monster)
    {
        this.lastMonster = monster;
    }

    @Override
    public void interact(PlayerModel player) throws HealthOVF, HungerOVF,ThirstOVF {
        player.getHealth().decreaseValue(this.lastMonster.getValue());
    }


    public void interactMonster(MonsterModel monster) {
        monster.decreaseValue(this.getValue());
    }

}
