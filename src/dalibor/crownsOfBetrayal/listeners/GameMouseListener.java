package dalibor.crownsOfBetrayal.listeners;

import dalibor.crownsOfBetrayal.graphics.window.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GameMouseListener implements MouseListener, MouseMotionListener {
    private final Panel panel;

    public GameMouseListener(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (this.panel.getGame().getCurrentState().getState()) {
            case MENU -> this.panel.getGame().getMenu().mouseClicked(e);
            case GAME_MENU ->
                this.panel.getGame().getGameMenu().mouseClicked(e);
            case INVENTORY ->
                this.panel.getGame().getInventory().mouseClicked(e);
            case PUB -> this.panel.getGame().getPub().mouseClicked(e);
            case SHOP -> this.panel.getGame().getShop().mouseClicked(e);
            case WORLD_MAP ->
                this.panel.getGame().getWorldMap().mouseClicked(e);
            case QUESTS -> this.panel.getGame().getQuests().mouseClicked(e);
            case CRAFTING -> this.panel.getGame().getCrafting().mouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (this.panel.getGame().getCurrentState().getState()) {
            case MENU -> this.panel.getGame().getMenu().mosePressed(e);
            case GAME_MENU -> this.panel.getGame().getGameMenu().mosePressed(e);
            case INVENTORY ->
                this.panel.getGame().getInventory().mosePressed(e);
            case PUB -> this.panel.getGame().getPub().mosePressed(e);
            case SHOP -> this.panel.getGame().getShop().mosePressed(e);
            case WORLD_MAP -> this.panel.getGame().getWorldMap().mosePressed(e);
            case QUESTS -> this.panel.getGame().getQuests().mosePressed(e);
            case CRAFTING -> this.panel.getGame().getCrafting().mosePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (this.panel.getGame().getCurrentState().getState()) {
            case MENU -> this.panel.getGame().getMenu().moseReleased(e);
            case GAME_MENU ->
                this.panel.getGame().getGameMenu().moseReleased(e);
            case INVENTORY ->
                this.panel.getGame().getInventory().moseReleased(e);
            case PUB -> this.panel.getGame().getPub().moseReleased(e);
            case SHOP -> this.panel.getGame().getShop().moseReleased(e);
            case WORLD_MAP ->
                this.panel.getGame().getWorldMap().moseReleased(e);
            case QUESTS -> this.panel.getGame().getQuests().moseReleased(e);
            case CRAFTING -> this.panel.getGame().getCrafting().moseReleased(e);
        }
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

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (this.panel.getGame().getCurrentState().getState()) {
            case MENU -> this.panel.getGame().getMenu().mouseMoved(e);
            case GAME_MENU -> this.panel.getGame().getGameMenu().mouseMoved(e);
            case INVENTORY -> this.panel.getGame().getInventory().mouseMoved(e);
            case PUB -> this.panel.getGame().getPub().mouseMoved(e);
            case SHOP -> this.panel.getGame().getShop().mouseMoved(e);
            case WORLD_MAP -> this.panel.getGame().getWorldMap().mouseMoved(e);
            case QUESTS -> this.panel.getGame().getQuests().mouseMoved(e);
            case CRAFTING -> this.panel.getGame().getCrafting().mouseMoved(e);
        }
    }
}
