package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.graphics.ui.StateButton;
import dalibor.crownsofbetrayal.states.CurrentState;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class WorldMap extends State {
    private final StateButton[] stateButtons;

    public WorldMap(CurrentState currentState, int windowWidth, int windowHeight) {
        super(currentState, new ImageReader().getBufferedImage("res/bg/world_map.png"), windowWidth, windowHeight);
        this.stateButtons = new StateButton[5];
        this.setButtons();
    }

    private void setButtons() {
        this.stateButtons[0] = new StateButton(
            (int)(this.getWindowWidth() * 0.12),
            (int)(this.getWindowHeight() * 0.69),
            350,
            50);
        this.stateButtons[1] = new StateButton(
            (int)(this.getWindowWidth() * 0.285),
            (int)(this.getWindowHeight() * 0.395),
            250,
            50);
        this.stateButtons[2] = new StateButton(
            (int)(this.getWindowWidth() * 0.66),
            (int)(this.getWindowHeight() * 0.595),
            260,
            50);
        this.stateButtons[3] = new StateButton(
            (int)(this.getWindowWidth() * 0.68),
            (int)(this.getWindowHeight() * 0.23),
            350,
            50);
        this.stateButtons[4] = new StateButton(
            (int)(this.getWindowWidth() * 0.88),
            (int)(this.getWindowHeight() * 0.14),
            250,
            50);
    }

    @Override
    public void draw(Graphics2D g2D) {
        super.draw(g2D);
        for (StateButton stateButton : this.stateButtons) {
            stateButton.draw(g2D);
        }
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
        if (event.getX() < 100 && event.getY() < 100) {
            this.getCurrentState().setState(States.GAME_MENU);
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
