package com.lpoo_32.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.lpoo_32.Controller.KeyStrokeListener;

import java.io.IOException;
import java.util.Arrays;

public class Menu extends Display {
    private ActionListBox listBox;
    private MultiWindowTextGUI gui;
    private Panel panel;

    public Menu() throws IOException {
        super();
        Panel mainPanel = createMainMenu();
        BasicWindow window = new BasicWindow();
        this.gui = new MultiWindowTextGUI(this.screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.MAGENTA));
        this.addOptions();
        window.setComponent(mainPanel);
        window.setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.FIT_TERMINAL_WINDOW));
        window.addWindowListener(new KeyStrokeListener());
        this.gui.addWindowAndWait(window);
    }

    private Panel createMainMenu() {
        TerminalSize size = new TerminalSize(20, 5);
        this.listBox = new ActionListBox(size);
        this.panel = new Panel();
        panel.setLayoutManager(new BorderLayout());
        panel.setLayoutData(BorderLayout.Location.CENTER);
        Panel mainPanel = new Panel();
        mainPanel.setLayoutManager(new BorderLayout());
        mainPanel.setPreferredSize(this.screen.getTerminalSize());
        mainPanel.addComponent(panel.withBorder(Borders.doubleLine("Don't Die")));
        mainPanel.setLayoutData(BorderLayout.Location.CENTER);
        return mainPanel;
    }

    @Override
    public void draw() throws IOException {
        this.gui.updateScreen();
    }

    @Override
    public void run() throws IOException {
        this.draw();
    }

    private void addOptions(){
        this.listBox.addItem("Meias", () -> System.out.println("MEIAAAAS")).addTo(this.panel);
        this.listBox.addItem("Start", ()-> {
            try {
                Display  game = new Game(this.screen);
                game.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).addTo(this.panel);
    }
}
