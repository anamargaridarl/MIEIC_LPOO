package com.lpoo_32.view.lanterna;

import com.googlecode.lanterna.gui2.table.DefaultTableCellRenderer;
import com.googlecode.lanterna.gui2.table.Table;

public class KeyTable extends Table<String> {
    KeyTable(){
        //TODO: How to style a Table?
        super("Key", "Command");
        DefaultTableCellRenderer<String> renderer = (DefaultTableCellRenderer<String>) this.getTableCellRenderer();
    }

    public void addComand(String key, String command){
        this.getTableModel().addRow(key, command);
    }

}
