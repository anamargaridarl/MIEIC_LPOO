package com.lpoo_32.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.FoodModel;
import com.lpoo_32.model.Inventory;
import com.lpoo_32.model.WaterModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class InventoryViewTest {
    private Inventory inventory;
    private TextGraphics textGraphics;
    private InventoryView inventoryView;

    @Before
    public void initializeValues(){
        this.inventory = Mockito.mock(Inventory.class);
        this.textGraphics = Mockito.mock(TextGraphics.class);
        this.inventoryView = new InventoryView(inventory, "#000000");
        TerminalSize terminalSize = Mockito.mock(TerminalSize.class);
        ScreenSize.createInstance(terminalSize);
        Mockito.when(terminalSize.getRows()).thenReturn(100);
        Mockito.when(terminalSize.getColumns()).thenReturn(100);
    }

    @Test
    public void drawTest(){
        this.inventoryView.draw(this.textGraphics);
        verify(textGraphics, atLeastOnce()).setForegroundColor(any(TextColor.class));
        verify(textGraphics, atLeastOnce()).setBackgroundColor(any(TextColor.class));
        verify(textGraphics, atLeastOnce()).fillRectangle(any(TerminalPosition.class), any(TerminalSize.class), any(char.class));
    }

    @Test
    public void getterTests(){
        Mockito.when(inventory.getElement()).thenReturn(Mockito.mock(FoodModel.class));
        assertEquals("Food", this.inventoryView.getName());
        Mockito.when(inventory.getElement()).thenReturn(Mockito.mock(WaterModel.class));
        assertEquals("Water", this.inventoryView.getName());

        Mockito.when(inventory.getElement()).thenReturn(Mockito.mock(FoodModel.class));
        assertEquals(Symbols.HEART, inventoryView.getSymbol());
    }


}