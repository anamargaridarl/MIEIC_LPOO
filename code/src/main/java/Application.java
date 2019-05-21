import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.lpoo_32.view.lanterna.Menu;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try{
            Menu menu = new Menu(new DefaultTerminalFactory().createTerminal());
            menu.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
