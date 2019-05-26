package com.lpoo_32.view;

import com.lpoo_32.controller.GameController;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.model.Position;

public interface ElementFactory {
    InteractableElementView getElement(ElementType element, Position pos, GameController controller, PlayerModel player);
}
