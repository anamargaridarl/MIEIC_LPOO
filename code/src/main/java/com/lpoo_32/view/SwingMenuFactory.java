package com.lpoo_32.view;

public class SwingMenuFactory implements MenuAbstractFactory {
    @Override
    public Runnable getMenu() {
        return new MenuSwing();
    }
}
