package com.lpoo_32.model;

import com.lpoo_32.exceptions.*;

public class BedModel extends InteractableElement {
    public BedModel(Position pos) {
        super(pos, 0);
    }

    @Override
    public boolean interact(PlayerModel player) throws Bedtime {
        throw new Bedtime();
    }
}
