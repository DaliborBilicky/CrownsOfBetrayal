package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.graphics.ui.Button;
import dalibor.crownsofbetrayal.states.CurrentState;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Inventory extends State {
    private final Button[][] buttons;

    public Inventory(CurrentState currentState, int windowWidth, int windowHeight) {
        super(currentState, new ImageReader().getBufferedImage("res/bg/inventory.png"), windowWidth, windowHeight);
        this.buttons = new Button[6][8];
        this.setButtons();
    }

    private void setButtons() {
        for (int i = 0; i < this.buttons.length; i++) {
            for (int j = 0; j < this.buttons[i].length; j++) {
                this.buttons[i][j] = new Button(
                    (int)(this.getWindowWidth() * 0.45 + (120 * j)),
                    (int)(this.getWindowHeight() * 0.25 + (120 * i)),
                    120,
                    120);
            }
        }
    }

    @Override
    public void draw(Graphics2D g2D) {
        super.draw(g2D);
        for (Button[] buttons : this.buttons) {
            for (Button button : buttons) {
                button.draw(g2D);
            }
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
        for (Button[] buttons : this.buttons) {
            for (Button button : buttons) {
                button.setMouseIn(false);
                if (button.getButtonBounds().contains(event.getX(), event.getY())) {
                    button.setMouseIn(true);
                }
            }
        }
    }
}
