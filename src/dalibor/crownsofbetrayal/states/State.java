package dalibor.crownsofbetrayal.states;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public abstract class State {
    private final CurrentState currentState;
    private final BufferedImage bgImage;
    private final int windowWidth;
    private final int windowHeight;

    public State(CurrentState currentState, BufferedImage bgImage, int windowWidth, int windowHeight) {
        this.currentState = currentState;
        this.bgImage = bgImage;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public void draw(Graphics2D g2D) {
        g2D.drawImage(this.bgImage, 0, 0, null);
    }

    public abstract void update();

    public abstract void mosePressed(MouseEvent event);

    public abstract void moseReleased(MouseEvent event);

    public abstract void mouseClicked(MouseEvent event);

    public abstract void mouseMoved(MouseEvent event);

    public int getWindowWidth() {
        return this.windowWidth;
    }

    public int getWindowHeight() {
        return this.windowHeight;
    }

    public BufferedImage getBgImage() {
        return this.bgImage;
    }

    public CurrentState getCurrentState() {
        return this.currentState;
    }
}
