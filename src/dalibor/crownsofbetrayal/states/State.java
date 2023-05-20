package dalibor.crownsofbetrayal.states;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.main.Game;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public abstract class State {
    private final CurrentState currentState;
    private final BufferedImage bgImage;
    private final int windowWidth;
    private final int windowHeight;
    private final double scale;
    private final Game game;
    private final Player player;

    public State(Game game, BufferedImage bgImage) {
        this.game = game;
        this.currentState = this.game.getCurrentState();
        this.bgImage = bgImage;
        this.windowWidth = this.game.getWidth();
        this.windowHeight = this.game.getHeight();
        this.scale = this.game.getScale();
        this.player = this.game.getPlayer();
    }

    public Game getGame() {
        return this.game;
    }

    public void draw(Graphics2D g2D) {
        g2D.drawImage(this.bgImage, 0, 0, this.windowWidth, this.windowHeight, null);
    }

    public abstract void update();

    public abstract void mouseClicked(MouseEvent event);

    public abstract void mouseMoved(MouseEvent event);

    public int getWindowWidth() {
        return this.windowWidth;
    }

    public int getWindowHeight() {
        return this.windowHeight;
    }

    public CurrentState getCurrentState() {
        return this.currentState;
    }

    public double getScale() {
        return this.scale;
    }

    public Player getPlayer() {
        return this.player;
    }
}
