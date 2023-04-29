package dalibor.crownsOfBetrayal.listeners;

import dalibor.crownsOfBetrayal.graphics.window.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameMouseListener implements MouseListener {
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
            case PUP -> this.panel.getGame().getPup().mouseClicked(e);
            case SHOP -> this.panel.getGame().getShop().mouseClicked(e);
            case WORLD_MAP ->
                this.panel.getGame().getWorldMap().mouseClicked(e);
            case QUESTS -> this.panel.getGame().getQuests().mouseClicked(e);
            case SKILL_SHOP ->
                this.panel.getGame().getSkillShop().mouseClicked(e);
            case SETTINGS -> this.panel.getGame().getSettings().mouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (this.panel.getGame().getCurrentState().getState()) {
            case MENU -> this.panel.getGame().getMenu().mosePressed(e);
            case GAME_MENU -> this.panel.getGame().getGameMenu().mosePressed(e);
            case INVENTORY ->
                this.panel.getGame().getInventory().mosePressed(e);
            case PUP -> this.panel.getGame().getPup().mosePressed(e);
            case SHOP -> this.panel.getGame().getShop().mosePressed(e);
            case WORLD_MAP -> this.panel.getGame().getWorldMap().mosePressed(e);
            case QUESTS -> this.panel.getGame().getQuests().mosePressed(e);
            case SKILL_SHOP ->
                this.panel.getGame().getSkillShop().mosePressed(e);
            case SETTINGS -> this.panel.getGame().getSettings().mosePressed(e);
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
            case PUP -> this.panel.getGame().getPup().moseReleased(e);
            case SHOP -> this.panel.getGame().getShop().moseReleased(e);
            case WORLD_MAP ->
                this.panel.getGame().getWorldMap().moseReleased(e);
            case QUESTS -> this.panel.getGame().getQuests().moseReleased(e);
            case SKILL_SHOP ->
                this.panel.getGame().getSkillShop().moseReleased(e);
            case SETTINGS -> this.panel.getGame().getSettings().moseReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
