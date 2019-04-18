package com.lpoo_32.view;

import java.io.IOException;

public class ScreenHandler implements Runnable {
    private final Display display;

    ScreenHandler(Display display){
        this.display = display;
    }
    @Override
    public void run() {
//        try{
//            this.display.draw();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("LMAOOOO");
    }
}
