package com.lpoo_32.controller.action;

import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.PlayerModel;

public class InventoryUse implements ActionEvent {
    private final GameController controller;

    public InventoryUse(GameController controller){
        this.controller = controller;
    }

    @Override
    public void execute() throws UpScreen, HungerOVF, Bedtime, ThirstOVF, HealthOVF, ThirstRestored, HungerRestored, DownScreen, LeftScreen, RightScreen, ScreenClose {
        PlayerModel player = controller.getPlayer();
        if (player.getInventory().getElement() != null) {
            if (!controller.changeWeaponInventory()) {
                player.getInventory().getElement().interact(player);
                player.getInventory().removeElement();
            }

        }
    }
}
