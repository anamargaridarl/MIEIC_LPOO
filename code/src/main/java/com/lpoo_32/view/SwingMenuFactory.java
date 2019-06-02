package com.lpoo_32.view;

import java.io.IOException;

public class SwingMenuFactory implements MenuAbstractFactory {
    @Override
    public Runnable getMenu() {
        return new MenuSwing();
    }
}
