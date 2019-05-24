import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.lpoo_32.view.Menu;
import com.lpoo_32.view.swing.GameSwing;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        if(args[0].contentEquals("lanterna")){
            try{
                Menu menu = new Menu(new DefaultTerminalFactory().createTerminal());
                menu.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            GameSwing game = new GameSwing();
        }

    }
}
