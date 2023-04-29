package dalibor.survivalGame.listeners;

import dalibor.survivalGame.graphics.window.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameMouseListener implements MouseListener {
    private final Panel panel;

    public GameMouseListener(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (this.panel.getGame().getCurrentGameState().getState()) {
            case MENU -> this.panel.getGame().getMenu().mosePressed(e);
            case PLAYING_GAME ->
                this.panel.getGame().getPlayingGame().mosePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (this.panel.getGame().getCurrentGameState().getState()) {
            case MENU -> this.panel.getGame().getMenu().moseReleased(e);
            case PLAYING_GAME ->
                this.panel.getGame().getPlayingGame().moseReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
