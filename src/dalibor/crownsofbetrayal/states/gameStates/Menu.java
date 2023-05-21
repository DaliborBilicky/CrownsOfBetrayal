package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.graphics.ui.Button;
import dalibor.crownsofbetrayal.graphics.ui.StateButton;
import dalibor.crownsofbetrayal.main.Game;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import dalibor.crownsofbetrayal.tools.ImageReader;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Menu extends State {
    private final Button[] buttons;

    public Menu(Game game) {
        super(game, new ImageReader().getBufferedImage("res/bg/menu.png"));
        this.buttons = new Button[2];
        this.setButtons();
    }

    private void setButtons() {
        for (int i = 0; i < this.buttons.length; i++) {
            this.buttons[i] = new StateButton(
                this.getWindowWidth() / 2,
                (int)(this.getWindowHeight() * (0.5 + (0.23 * i))),
                (int)(400 / this.getScale()),
                (int)(100 / this.getScale()));
            if (this.buttons[i] instanceof StateButton) {
                ((StateButton)this.buttons[i]).setCurrentState(this.getCurrentState());
            }
        }
        ((StateButton)this.buttons[0]).setState(States.GAME_MENU);
        ((StateButton)this.buttons[1]).setState(States.QUIT);
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
