package com.lpoo_32.controller.action;

import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.Elements;
import com.lpoo_32.model.PlayerModel;

public class Use implements ActionEvent {
    private final GameController controller;

    public Use(GameController controller){
        this.controller = controller;
    }

    @Override
    public void execute() throws HungerOVF, Bedtime, ThirstOVF, HealthOVF, ThirstRestored, HungerRestored {
        PlayerModel player = controller.getPlayer();
        Elements elements = controller.getElements();
        if(!controller.isWeaponUseDirect(player.getPosition())) {
            if (controller.isCatchable(player.getPosition())) {
                elements.getModel(player.getPosition()).interact(player);
                controller.removeElementProps(elements.getModel(player.getPosition()));
            }
        }
    }
}
