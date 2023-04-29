package dalibor.survivalGame.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface GameState {
    void draw(Graphics2D g2D);

    void update();

    void keyPressed(KeyEvent event);

    void keyReleased(KeyEvent event);

    void mosePressed(MouseEvent event);

    void moseReleased(MouseEvent event);

}
