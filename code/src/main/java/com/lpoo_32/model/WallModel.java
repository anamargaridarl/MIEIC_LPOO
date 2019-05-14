package com.lpoo_32.model;

public class WallModel extends InteractableElement {
    WallModel(Position pos) {
        super(pos);
    }

    @Override
    public boolean interact(PlayerModel player) {
        return false;
    }
}
