package dalibor.survivalGame.states;

import dalibor.survivalGame.graphics.draw.Canvas;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PlayingGame implements GameState {
    private final CurrentGameState currentGameState;
    private final Canvas canvas;
    private int frames;
    private int updates;

    public PlayingGame(CurrentGameState currentGameState, Canvas canvas, int gameWidth, int gameHeight) {
        this.currentGameState = currentGameState;
        this.canvas = canvas;
    }


    public void setFPS(int frames, int updates) {
        this.frames = frames;
        this.updates = updates;
    }

    @Override
    public void draw(Graphics2D g2D) {
        this.canvas.setGraphics(g2D);
        this.canvas.drawFPS(this.frames, this.updates);
    }

    @Override
    public void update() {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_BACK_SPACE ->
                this.currentGameState.setState(State.MENU);

        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        switch (event.getKeyCode()) {

        }
    }

    @Override
    public void mosePressed(MouseEvent event) {

    }

    @Override
    public void moseReleased(MouseEvent event) {

    }
}
