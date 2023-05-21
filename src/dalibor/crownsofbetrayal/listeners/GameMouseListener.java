package dalibor.crownsofbetrayal.listeners;

import dalibor.crownsofbetrayal.graphics.window.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


/**
 * Trieda pre lepsiu pracu s MouseListenermy
 */
public class GameMouseListener implements MouseListener, MouseMotionListener {
    private final Panel panel;

    /**
     * @param panel panel v ktorom kontrolujem ci mouse listener zareagoval
     */
    public GameMouseListener(Panel panel) {
        this.panel = panel;
    }

    /**
     * Switch kde sa podla aktivneho statu vykona ina metod
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (this.panel.getGame().getCurrentState().getState()) {
            case MENU -> this.panel.getGame().getMenu().mouseClicked(e);
            case GAME_MENU ->
                this.panel.getGame().getGameMenu().mouseClicked(e);
            case INVENTORY ->
                this.panel.getGame().getInventory().mouseClicked(e);
            case PUB -> this.panel.getGame().getPub().mouseClicked(e);
            case WORLD_MAP ->
                this.panel.getGame().getWorldMap().mouseClicked(e);
            case QUESTS -> this.panel.getGame().getQuests().mouseClicked(e);
            case DUNGEON -> this.panel.getGame().getDungeon().mouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Switch kde sa podla aktivneho statu vykona ina metod
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        switch (this.panel.getGame().getCurrentState().getState()) {
            case MENU -> this.panel.getGame().getMenu().mouseMoved(e);
            case GAME_MENU -> this.panel.getGame().getGameMenu().mouseMoved(e);
            case INVENTORY -> this.panel.getGame().getInventory().mouseMoved(e);
            case PUB -> this.panel.getGame().getPub().mouseMoved(e);
            case WORLD_MAP -> this.panel.getGame().getWorldMap().mouseMoved(e);
            case QUESTS -> this.panel.getGame().getQuests().mouseMoved(e);
            case DUNGEON -> this.panel.getGame().getDungeon().mouseMoved(e);
        }
    }
}
