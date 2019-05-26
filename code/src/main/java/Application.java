import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.OutOfBoundaries;
import com.lpoo_32.model.Elements;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.model.Position;
import com.lpoo_32.view.*;
import com.lpoo_32.view.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Application {
    public static void main(String[] args) throws OutOfBoundaries, IOException {
        if(args[0].contentEquals("lanterna")){
            try{
                Menu menu = new Menu(new DefaultTerminalFactory().createTerminal());
                menu.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
                MenuSwing menu = new MenuSwing();
                menu.run();
        }

    }
}
