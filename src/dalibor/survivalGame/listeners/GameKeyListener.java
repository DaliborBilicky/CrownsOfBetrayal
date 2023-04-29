package dalibor.survivalGame.listeners;

import dalibor.survivalGame.graphics.window.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {
    private final Panel panel;

    public GameKeyListener(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (this.panel.getGame().getCurrentGameState().getState()) {
            case MENU -> this.panel.getGame().getMenu().keyPressed(e);
            case PLAYING_GAME ->
                this.panel.getGame().getPlayingGame().keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (this.panel.getGame().getCurrentGameState().getState()) {
            case MENU -> this.panel.getGame().getMenu().keyReleased(e);
            case PLAYING_GAME ->
                this.panel.getGame().getPlayingGame().keyReleased(e);
        }
    }
}
