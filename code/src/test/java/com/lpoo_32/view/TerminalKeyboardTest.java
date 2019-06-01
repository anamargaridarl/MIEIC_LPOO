package com.lpoo_32.view;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.lpoo_32.controller.GameController;
import com.lpoo_32.controller.action.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TerminalKeyboardTest {

   @Test
    public void testEvent() throws IOException {
       Screen screen = Mockito.mock(Screen.class);
       ActionEvent action;
       TerminalKeyboard terminalKeyboard = new TerminalKeyboard(screen, Mockito.mock(GameController.class));
       Mockito.when(screen.readInput()).thenReturn(new KeyStroke(KeyType.ArrowRight));
       action = terminalKeyboard.getActionEvent();
       assertEquals(action.getClass(), MoveRight.class);
       Mockito.when(screen.readInput()).thenReturn(new KeyStroke(KeyType.ArrowUp));
       action = terminalKeyboard.getActionEvent();
       assertEquals(action.getClass(), MoveUp.class);
       Mockito.when(screen.readInput()).thenReturn(new KeyStroke(KeyType.ArrowDown));
       action = terminalKeyboard.getActionEvent();
       assertEquals(action.getClass(), MoveDown.class);
       Mockito.when(screen.readInput()).thenReturn(new KeyStroke(KeyType.ArrowLeft));
       action = terminalKeyboard.getActionEvent();
       assertEquals(action.getClass(), MoveLeft.class);
       Mockito.when(screen.readInput()).thenReturn(new KeyStroke('z', false, false));
       action = terminalKeyboard.getActionEvent();
       assertEquals(action.getClass(), Exit.class);
       Mockito.when(screen.readInput()).thenReturn(new KeyStroke('f', false, false));
       action = terminalKeyboard.getActionEvent();
       assertEquals(action.getClass(), Store.class);
       Mockito.when(screen.readInput()).thenReturn(new KeyStroke('t', false, false));
       action = terminalKeyboard.getActionEvent();
       assertEquals(action.getClass(), Use.class);
       Mockito.when(screen.readInput()).thenReturn(new KeyStroke('1', false, false));
       action = terminalKeyboard.getActionEvent();
       assertEquals(action.getClass(), LeftInventory.class);
       Mockito.when(screen.readInput()).thenReturn(new KeyStroke('2', false, false));
       action = terminalKeyboard.getActionEvent();
       assertEquals(action.getClass(), RightInventory.class);
       Mockito.when(screen.readInput()).thenReturn(new KeyStroke('e', false, false));
       action = terminalKeyboard.getActionEvent();
       assertEquals(action.getClass(), InventoryUse.class);
       Mockito.when(screen.readInput()).thenReturn(new KeyStroke('w', false, false));
       action = terminalKeyboard.getActionEvent();
       assertEquals(action.getClass(), AttackUp.class);
       Mockito.when(screen.readInput()).thenReturn(new KeyStroke('s', false, false));
       action = terminalKeyboard.getActionEvent();
       assertEquals(action.getClass(), AttackDown.class);
       Mockito.when(screen.readInput()).thenReturn(new KeyStroke('a', false, false));
       action = terminalKeyboard.getActionEvent();
       assertEquals(action.getClass(), AttackLeft.class);
       Mockito.when(screen.readInput()).thenReturn(new KeyStroke('d', false, false));
       action = terminalKeyboard.getActionEvent();
       assertEquals(action.getClass(), AttackRight.class);
   }
}
