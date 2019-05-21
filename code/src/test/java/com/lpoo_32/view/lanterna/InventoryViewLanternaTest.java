package com.lpoo_32.view.lanterna;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.lpoo_32.model.FoodModel;
import com.lpoo_32.model.Inventory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class InventoryViewLanternaTest {
    private Inventory inventory;
    private TextGraphics textGraphics;
    private InventoryViewLanterna inventoryViewLanterna;

    @Before
    public void initializeValues(){
        this.inventory = Mockito.mock(Inventory.class);
        this.textGraphics = Mockito.mock(TextGraphics.class);
        this.inventoryViewLanterna = new InventoryViewLanterna(inventory, "#000000");
        TerminalSize terminalSize = Mockito.mock(TerminalSize.class);
        ScreenSize.createInstance(terminalSize);
        Mockito.when(terminalSize.getRows()).thenReturn(100);
        Mockito.when(terminalSize.getColumns()).thenReturn(100);
    }

    @Test
    public void drawTest(){
        this.inventoryViewLanterna.draw(this.textGraphics);
        verify(textGraphics, atLeastOnce()).setForegroundColor(any(TextColor.class));
        verify(textGraphics, atLeastOnce()).setBackgroundColor(any(TextColor.class));
        verify(textGraphics, atLeastOnce()).fillRectangle(any(TerminalPosition.class), any(TerminalSize.class), any(char.class));
    }

    @Test
    public void getterTests(){
        Mockito.when(inventory.getView()).thenReturn(new FoodViewLanterna(null));
        assertEquals("Food", this.inventory.getView().getName());
        Mockito.when(inventory.getView()).thenReturn(new WaterViewLanterna(null));
        assertEquals("Water", this.inventory.getView().getName());

        Mockito.when(inventory.getView()).thenReturn(new FoodViewLanterna(null));
        Mockito.when(inventory.getElement()).thenReturn(new FoodModel(0, null));
        assertEquals(Symbols.HEART, inventoryViewLanterna.getSymbol());
    }


}