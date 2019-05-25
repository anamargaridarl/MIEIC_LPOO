import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.lpoo_32.controller.GameController;
import com.lpoo_32.exceptions.OutOfBoundaries;
import com.lpoo_32.model.Elements;
import com.lpoo_32.model.PlayerModel;
import com.lpoo_32.model.Position;
import com.lpoo_32.view.Game;
import com.lpoo_32.view.GameSwing;
import com.lpoo_32.view.Menu;
import com.lpoo_32.view.SwingKeyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
            JFrame frame = new JFrame();
            frame.setFocusable(true);
            frame.setVisible(true);
            frame.getContentPane().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent keyEvent) {
                    System.out.println("Pressed key!");
                }

                @Override
                public void keyPressed(KeyEvent keyEvent) {
                    System.out.println("Pressed key!");
                }

                @Override
                public void keyReleased(KeyEvent keyEvent) {
                    System.out.println("Pressed key!");

                }
            });
            System.out.println("Added Key listener");
            GameController game = new GameController(
                    elements,
                    model,
                    new GameSwing(frame, model, elements)
                    );

            game.run();

        }

    }
}
