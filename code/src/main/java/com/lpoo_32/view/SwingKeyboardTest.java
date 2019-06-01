package com.lpoo_32.view;

import com.lpoo_32.controller.GameController;

import javax.swing.*;

import java.awt.event.ActionEvent;

import static com.lpoo_32.view.EventType.MOVEUP;

public class SwingKeyboardTest {
    private static final String MOVE_UP = "MOVE_UP";

    public SwingKeyboardTest(JFrame frame, GameController game) {
        JPanel panel = (JPanel) frame.getContentPane();
        panel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("UP"), MOVE_UP);
        panel.getActionMap().put(MOVE_UP, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                game.processKey(MOVEUP);
            }
        });
    }
}
