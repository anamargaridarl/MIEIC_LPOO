package com.lpoo_32.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.table.Table;
import com.lpoo_32.Controller.SecondaryMenuKeyListener;
import com.lpoo_32.Controller.MenuKeyListener;

import java.io.IOException;
import java.util.Arrays;

public class Menu extends Display {
    private ActionListBox listBox;
    private MultiWindowTextGUI gui;
    private Panel mainPanel;

    public Menu() throws IOException {
        super();
        Panel mainPanel = createMainMenu();
        this.addOptions();
        this.gui = new MultiWindowTextGUI(this.screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.MAGENTA));
        Window mainWindow = createWindow(mainPanel);
        mainWindow.addWindowListener(new MenuKeyListener());
        this.gui.addWindowAndWait(mainWindow);
    }

    private Window createWindow(Panel mainPanel) {
        BasicWindow window =  new BasicWindow();
        window.setComponent(mainPanel);
        window.setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.FIT_TERMINAL_WINDOW));
        return window;
    }

    private Panel createMainMenu() {
        TerminalSize size = new TerminalSize(20, 5);
        this.listBox = new ActionListBox(size);
        this.mainPanel = new Panel();
        return getPanel(mainPanel);
    }

    private Panel createHintMenu(){
        TerminalSize size = new TerminalSize(20, 5);
        TextBox textBox = new TextBox(size, "You move around with your arrow keys");
        KeyTable table = new KeyTable();
        table.addComand("Arrows", "Movement");
        table.addComand("Q", "Move Back");
        table.addComand("P", "Take you own Health(why would you do that?!)");
        Panel hintPanel = new Panel();
        hintPanel.addComponent(table);
        return getPanel(hintPanel);
    }

    private Panel getPanel(Panel panel) {
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
        this.listBox.addItem("Meias", () -> System.out.println("MEIAAAAS")).addTo(this.mainPanel);
        this.listBox.addItem("Start", ()-> {
            try {
                Display  game = new Game(this.screen);
                game.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).addTo(this.mainPanel);
        this.listBox.addItem("Help", ()->{
            Window window = this.createWindow(this.createHintMenu());
            window.addWindowListener(new SecondaryMenuKeyListener(this.gui, window));
            this.gui.addWindow(window);
        });
    }
}
