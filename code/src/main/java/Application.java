import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.HungerOVF;
import com.lpoo_32.exceptions.OutOfBoundaries;
import com.lpoo_32.exceptions.ThirstOVF;
import com.lpoo_32.model.Elements;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.model.Position;
import com.lpoo_32.view.*;

import javax.swing.*;
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
            Elements elements = new Elements();
            PlayerModel model = new PlayerModel(new Position(2,2, Game.width/4, Game.height/4, 0));
            GameController game = new GameController(
                    elements,
                    model,
                    new GameSwing(new JFrame(), model, elements),
                    () -> null
            );
            game.run();
        }

    }
}
