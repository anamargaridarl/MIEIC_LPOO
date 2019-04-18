package com.lpoo_32.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;

import java.io.IOException;

public class Menu extends Display {
    ActionListBox listBox;
    BasicWindow window;
    MultiWindowTextGUI gui;
    Panel panel;

    public Menu() throws IOException {
        super();
        TerminalSize size = new TerminalSize(10, 3);
        this.listBox = new ActionListBox(size);
        this.panel = new Panel();
        this.window = new BasicWindow();
        this.gui = new MultiWindowTextGUI(this.screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.MAGENTA));
        this.addOptions();
        this.window.setComponent(panel);
        this.gui.addWindowAndWait(window);
    }

    @Override
    public void draw() throws IOException {
        this.gui.updateScreen();
    }

    private void addOptions(){
        this.listBox.addItem("Meias", () -> System.out.println("MEIAAAAS")).addTo(this.panel);
        this.listBox.addItem("Lmao", () -> System.out.println("Lmaoooo")).addTo(this.panel);
    }
}
