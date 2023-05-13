package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.graphics.ui.Button;
import dalibor.crownsofbetrayal.graphics.ui.StateButton;
import dalibor.crownsofbetrayal.states.CurrentState;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class GameMenu extends State {
    private final Button[] buttons;

    public GameMenu(CurrentState currentState, int windowWidth, int windowHeight) {
        super(currentState, new ImageReader().getBufferedImage("res/bg/game_menu.png"), windowWidth, windowHeight);
        this.buttons = new Button[6];
        this.setButtons();
    }

    private void setButtons() {
        for (int i = 0; i < this.buttons.length / 2; i++) {
            this.buttons[i] = new StateButton(
                (int)(this.getWindowWidth() * 0.275),
                (int)(this.getWindowHeight() * (0.33 + (0.22 * i))),
                700,
                150);
            if (this.buttons[i] instanceof StateButton) {
                ((StateButton)this.buttons[i]).setCurrentState(this.getCurrentState());
            }
        }
        for (int i = this.buttons.length / 2; i < this.buttons.length; i++) {
            this.buttons[i] = new StateButton(
                (int)(this.getWindowWidth() * 0.73),
                (int)(this.getWindowHeight() *
                    (0.33 + (0.22 * (i - this.buttons.length / 2)))),
                700,
                150);
            if (this.buttons[i] instanceof StateButton) {
                ((StateButton)this.buttons[i]).setCurrentState(this.getCurrentState());
            }
        }
        ((StateButton)this.buttons[0]).setState(States.PUB);
        ((StateButton)this.buttons[1]).setState(States.SHOP);
        ((StateButton)this.buttons[2]).setState(States.QUESTS);
        ((StateButton)this.buttons[3]).setState(States.INVENTORY);
        ((StateButton)this.buttons[4]).setState(States.CRAFTING);
        ((StateButton)this.buttons[5]).setState(States.WORLD_MAP);
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
        if (event.getX() < 100 && event.getY() < 100) {
            this.getCurrentState().setState(States.MENU);
        }
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
