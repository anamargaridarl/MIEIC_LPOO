import com.lpoo_32.graphics.Display;
import com.lpoo_32.graphics.Game;
import com.lpoo_32.graphics.Menu;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try{
            Display menu = new Game();
            menu.draw();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
