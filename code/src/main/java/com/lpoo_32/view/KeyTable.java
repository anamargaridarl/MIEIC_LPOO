package com.lpoo_32.view;

import com.googlecode.lanterna.gui2.table.DefaultTableCellRenderer;
import com.googlecode.lanterna.gui2.table.Table;

class KeyTable extends Table<String> {
    KeyTable(){
        //TODO: How to style a Table?
        super("Key", "Command");
        DefaultTableCellRenderer<String> renderer = (DefaultTableCellRenderer<String>) this.getTableCellRenderer();
    }

    public void addComand(String key, String command){
        this.getTableModel().addRow(key, command);
    }

}
