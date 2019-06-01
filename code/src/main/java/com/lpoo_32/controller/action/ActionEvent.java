package com.lpoo_32.controller.action;

import com.lpoo_32.exceptions.*;

public interface ActionEvent {
    void execute() throws UpScreen, HungerOVF, Bedtime, ThirstOVF, HealthOVF, ThirstRestored, HungerRestored, DownScreen, LeftScreen, RightScreen, ScreenClose;
}
