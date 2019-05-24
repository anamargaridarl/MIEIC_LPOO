package com.lpoo_32.view;

import com.lpoo_32.view.KeyTable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KeyTableTest {
    @Test
    public void addCommand(){
        KeyTable table = new KeyTable();
        table.addComand("key", "Command");
        assertEquals("key", table.getTableModel().getCell(0, 0));
        assertEquals("Command", table.getTableModel().getCell(1, 0));
    }

}