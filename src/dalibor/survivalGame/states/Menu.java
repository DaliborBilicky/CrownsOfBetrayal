package dalibor.survivalGame.states;

import dalibor.survivalGame.graphics.draw.Canvas;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Menu implements GameState {
    private final CurrentGameState currentGameState;
    private final Canvas canvas;

    public Menu(CurrentGameState currentGameState, Canvas canvas) {
        this.currentGameState = currentGameState;
        this.canvas = canvas;
    }

    @Override
    public void draw(Graphics2D g2D) {
        this.canvas.setGraphics(g2D);
        //this.canvas.drawMenuBG();
    }

    @Override
    public void update() {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_BACK_SPACE -> System.exit(0);
            case KeyEvent.VK_ENTER ->
                this.currentGameState.setState(State.PLAYING_GAME);
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {

    }

    @Override
    public void mosePressed(MouseEvent event) {

    }

    @Override
    public void moseReleased(MouseEvent event) {

    }
}
