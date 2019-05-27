package com.lpoo_32.model;

public class DoorModel extends InteractableElement{

    public DoorModel(Position pos) {
        super(pos, 0, "door.png");
    }

    @Override
    public boolean interact(PlayerModel player) {
        return true;
    }
}
