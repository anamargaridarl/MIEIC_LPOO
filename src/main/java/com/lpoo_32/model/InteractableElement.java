package com.lpoo_32.model;

import com.lpoo_32.exceptions.StatusOverflow;

public interface InteractableElement extends ElementModel{
    void interact(PlayerModel player) throws StatusOverflow;
}
