package com.lpoo_32.model;

public class WallModel extends InteractableElement {
    public WallModel(Position pos) {
        super(pos, 0);
    }

    @Override
    public boolean interact(PlayerModel player) {
        return false;
    }
}
