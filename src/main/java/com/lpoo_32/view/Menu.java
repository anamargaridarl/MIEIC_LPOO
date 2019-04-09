package com.lpoo_32.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.ActionListBox;

import java.io.IOException;

public class Menu extends Display {
    ActionListBox listBox;
    public Menu() throws IOException {
        super();
        TerminalSize size = new TerminalSize(10, 3);
        this.listBox = new ActionListBox(size);
        this.addOptions();
    }

    @Override
    public void draw() {
//        listBox.draw(this.gui.);
    }

    private void addOptions(){
        this.listBox.addItem("Meias", () -> System.out.println("MEIAAAAS"));
    }
}
