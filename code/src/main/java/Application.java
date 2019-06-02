import com.lpoo_32.view.*;
import java.io.IOException;

class Application {
    public static void main(String[] args) throws IOException {
        MenuAbstractFactory factory;
        if(args[0].contentEquals("lanterna")){
            factory = new TerminalMenuFactory();
        }
        else {
            factory = new SwingMenuFactory();
        }
        factory.getMenu().run();

    }
}
