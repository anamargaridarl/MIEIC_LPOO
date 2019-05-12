package com.lpoo_32.view;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.exceptions.*;
import com.lpoo_32.model.InteractableElement;

public interface ElementView  {
    void draw(TextGraphics graphics) throws HungerOVF, UpScreen, ThirstOVF, HealthOVF, ThirstRestored, HungerRestored, LeftScreen, RightScreen, DownScreen;

}
