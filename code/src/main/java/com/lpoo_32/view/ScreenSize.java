package com.lpoo_32.view;

import com.googlecode.lanterna.TerminalSize;

public class ScreenSize {
    private TerminalSize size;

    private static ScreenSize instance;

    private ScreenSize(TerminalSize size){
         this.size = size;
    }

    public static ScreenSize instance(){
        return instance;
    }

    public static void createInstance(TerminalSize size){
        System.out.println("New Instance" + size);
        instance = new ScreenSize(size);
    }

    public int getColumn(int columns){
        return (int) (this.size.getColumns() * ((double)columns / 100));
    }

    public int getRows(int rows){
        return (int) (this.size.getRows() * ((double)rows / 100));
    }
}
