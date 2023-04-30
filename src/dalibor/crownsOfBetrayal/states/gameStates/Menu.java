package dalibor.crownsOfBetrayal.states.gameStates;

import dalibor.crownsOfBetrayal.graphics.ImageReader;
import dalibor.crownsOfBetrayal.graphics.ui.Button;
import dalibor.crownsOfBetrayal.graphics.ui.LabelButton;
import dalibor.crownsOfBetrayal.states.CurrentState;
import dalibor.crownsOfBetrayal.states.State;
import dalibor.crownsOfBetrayal.states.States;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Menu extends State {
    private final LabelButton[] buttons;

    public Menu(CurrentState currentState, int windowWidth, int windowHeight) {
        super(currentState, new ImageReader().getBufferedImage("res/bg/menu.png"), windowWidth, windowHeight);
        this.buttons = new LabelButton[3];
        this.setButtons();
    }

    private void setButtons() {
        this.buttons[0] = new LabelButton(
            this.getWindowWidth() / 2,
            (int) (this.getWindowHeight() * 0.47),
            520,
            100,
            States.GAME_MENU,
            this.getCurrentState());
        this.buttons[1] = new LabelButton(
            this.getWindowWidth() / 2,
            (int) (this.getWindowHeight() * 0.645),
            520,
            100,
            States.GAME_MENU,
            this.getCurrentState());
        this.buttons[2] = new LabelButton(
            this.getWindowWidth() / 2,
            (int) (this.getWindowHeight() * 0.82),
            520,
            100,
            States.EXIT,
            this.getCurrentState());
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
        for (LabelButton button : this.buttons) {
            if (button.getButtonBounds().contains(event.getX(), event.getY())) {
                button.applyState();
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
