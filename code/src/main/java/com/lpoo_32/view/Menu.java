package com.lpoo_32.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.terminal.Terminal;
import com.lpoo_32.controller.GameController;
import com.lpoo_32.controller.SecondaryMenuKeyListener;
import com.lpoo_32.controller.MenuKeyListener;
import com.lpoo_32.exceptions.OutOfBoundaries;
import com.lpoo_32.model.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Menu extends Display implements Runnable {
    //    protected WindowBasedTextGUI gui;
    protected List<ElementView> props;
    Elements elements;
    private ActionListBox listBox;
    private MultiWindowTextGUI gui;
    private Panel mainPanel;

    public Menu(Terminal terminal) throws IOException {
        super(terminal);
        Panel mainPanel = createMainMenu();
        this.addOptions();
        this.gui = new MultiWindowTextGUI(this.screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.GREEN));
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
        table.addComand("Q", "Move Back / Exit program");
        table.addComand("Z", "Quit runnable");
        table.addComand("T", "Use food/water from the ground");
        table.addComand("F", "Pick food/water into the inventory");
        table.addComand("E", "Use current element in the inventory");
        table.addComand("1/2", "Move Left/Right in the inventory");



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

    public void run() {
        try {
            this.draw();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addOptions(){

        this.listBox.addItem("Start", ()-> {
            try {
                PlayerModel model = new PlayerModel(new Position(2,2, Game.width/4, Game.height/4, 0));
                Elements elements = new Elements();
                GameController gameController = new GameController(
                                                elements,
                                                model,
                                                new GameLanterna(this.screen, model, elements)
                                                );
                TerminalKeyboard keyboard = new TerminalKeyboard(screen, gameController);
                gameController.run();
                keyboard.stopKeyboard();
            } catch (IOException | OutOfBoundaries e) {
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
