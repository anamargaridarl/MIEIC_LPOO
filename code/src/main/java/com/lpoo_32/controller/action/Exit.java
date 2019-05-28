package com.lpoo_32.controller.action;

import com.lpoo_32.exceptions.*;

public class Exit implements ActionEvent {
    @Override
    public void execute() throws ScreenClose {
        throw new ScreenClose();
    }
}
