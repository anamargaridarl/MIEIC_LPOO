package com.lpoo_32.controller.action;

import com.lpoo_32.model.PlayerModel;

public class LeftInventory implements ActionEvent {
    private final PlayerModel player;

    public LeftInventory(PlayerModel player){
        this.player = player;
    }

    @Override
    public void execute() {
        this.player.getInventory().moveLeft();
    }
}
