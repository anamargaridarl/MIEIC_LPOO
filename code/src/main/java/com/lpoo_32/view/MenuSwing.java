package com.lpoo_32.view;

public class MenuSwing implements Runnable{

    MenuSwingGUI gui;

    public MenuSwing()
    {
    gui = new MenuSwingGUI();
    }

    public void run()
    {
        gui.setVisible(true);
    }

}
