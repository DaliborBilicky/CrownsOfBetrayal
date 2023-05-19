package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.graphics.ui.Button;
import dalibor.crownsofbetrayal.graphics.ui.StateButton;
import dalibor.crownsofbetrayal.states.CurrentState;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Menu extends State {
    private final Button[] buttons;

    public Menu(CurrentState currentState, int windowWidth, int windowHeight, double scale) {
        super(currentState,
            new ImageReader().getBufferedImage("res/bg/menu.png"),
            windowWidth, windowHeight, scale);
        this.buttons = new Button[3];
        this.setButtons();
    }

    private void setButtons() {
        System.out.println(this.getScale());
        for (int i = 0; i < this.buttons.length; i++) {
            this.buttons[i] = new StateButton(
                this.getWindowWidth() / 2,
                (int)(this.getWindowHeight() * (0.47 + (0.175 * i))),
                (int)(520 / this.getScale()),
                (int)(100 / this.getScale()));
            if (this.buttons[i] instanceof StateButton) {
                ((StateButton)this.buttons[i]).setCurrentState(this.getCurrentState());
            }
        }
        ((StateButton)this.buttons[0]).setState(States.GAME_MENU);
        ((StateButton)this.buttons[1]).setState(States.GAME_MENU);
        ((StateButton)this.buttons[2]).setState(States.EXIT);
    }

    @Override
    public void draw(Graphics2D g2D) {
        super.draw(g2D);
        for (Button button : this.buttons) {
            button.draw(g2D);
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
        for (Button button : this.buttons) {
            if (button.getButtonBounds().contains(event.getX(), event.getY())) {
                if (button instanceof StateButton) {
                    ((StateButton)button).applyState();
                }
                button.setMouseIn(false);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        for (Button button : this.buttons) {
            button.setMouseIn(false);
            if (button.getButtonBounds().contains(event.getX(), event.getY())) {
                button.setMouseIn(true);
            }
        }
    }
}
