package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.graphics.ui.StateButton;
import dalibor.crownsofbetrayal.main.Game;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class WorldMap extends State {
    private final StateButton[] stateButtons;

    public WorldMap(Game game) {
        super(game,
            new ImageReader().getBufferedImage("res/bg/world_map.png"));
        this.stateButtons = new StateButton[5];
        this.setButtons();
    }

    private void setButtons() {
        this.stateButtons[0] = new StateButton(
            (int)(this.getWindowWidth() * 0.12),
            (int)(this.getWindowHeight() * 0.69),
            (int)(350 / this.getScale()),
            (int)(50 / this.getScale()));
        this.stateButtons[1] = new StateButton(
            (int)(this.getWindowWidth() * 0.285),
            (int)(this.getWindowHeight() * 0.395),
            (int)(250 / this.getScale()),
            (int)(50 / this.getScale()));
        this.stateButtons[2] = new StateButton(
            (int)(this.getWindowWidth() * 0.66),
            (int)(this.getWindowHeight() * 0.595),
            (int)(260 / this.getScale()),
            (int)(50 / this.getScale()));
        this.stateButtons[3] = new StateButton(
            (int)(this.getWindowWidth() * 0.68),
            (int)(this.getWindowHeight() * 0.23),
            (int)(350 / this.getScale()),
            (int)(50 / this.getScale()));
        this.stateButtons[4] = new StateButton(
            (int)(this.getWindowWidth() * 0.88),
            (int)(this.getWindowHeight() * 0.14),
            (int)(250 / this.getScale()),
            (int)(50 / this.getScale()));
    }

    @Override
    public void draw(Graphics2D g2D) {
        super.draw(g2D);
        for (StateButton stateButton : this.stateButtons) {
            stateButton.draw(g2D);
        }
        g2D.setFont(new Font("Viner Hand ITC", Font.BOLD, 45));
        g2D.setColor(Color.WHITE);
        g2D.drawString(
            String.valueOf(this.getPlayer().getSupplies()),
            (int)(this.getWindowWidth() * 0.25),
            (int)(this.getWindowHeight() * 0.22));
    }

    @Override
    public void update() {

    }

    @Override
    public void mosePressed(MouseEvent event) {

    }

    @Override
    public void moseReleased(MouseEvent event) {

    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (event.getX() < 100 / this.getScale() &&
            event.getY() < 100 / this.getScale()) {
            this.getCurrentState().setState(States.GAME_MENU);
        }
        for (StateButton button : this.stateButtons) {
            if (button.getButtonBounds().contains(event.getX(), event.getY())) {
                this.getCurrentState().setState(States.DUNGEON);
                this.getGame().getDungeon().setDungeon();
                int tempSupplies = this.getPlayer().getSupplies();
                this.getPlayer().setSupplies(tempSupplies - 10);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        for (StateButton button : this.stateButtons) {
            button.setMouseIn(false);
            if (button.getButtonBounds().contains(event.getX(), event.getY())) {
                button.setMouseIn(true);
            }
        }
    }
}
