package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.graphics.ui.Button;
import dalibor.crownsofbetrayal.graphics.ui.ItemButton;
import dalibor.crownsofbetrayal.graphics.ui.QuestButton;
import dalibor.crownsofbetrayal.graphics.ui.StateButton;
import dalibor.crownsofbetrayal.states.CurrentState;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Pub extends State {
    private final Button[] buttons;
    private final ItemButton[][] itemButtons;

    public Pub(CurrentState currentState, int windowWidth, int windowHeight) {
        super(currentState,
            new ImageReader().getBufferedImage("res/bg/pub.png"),
            windowWidth, windowHeight);
        this.buttons = new Button[4];
        this.itemButtons = new ItemButton[6][7];
        this.setButtons();
    }

    private void setButtons() {
        for (int i = 0; i < this.itemButtons.length; i++) {
            for (int j = 0; j < this.itemButtons[i].length; j++) {
                if (j < this.itemButtons[i].length * 0.6) {
                    this.itemButtons[i][j] = new ItemButton(
                        (int)(this.getWindowWidth() * 0.48 + (120 * j)),
                        (int)(this.getWindowHeight() * 0.25 + (120 * i)),
                        120,
                        120);
                } else {
                    this.itemButtons[i][j] = new ItemButton(
                        (int)(this.getWindowWidth() * 0.47 + (120 * j) + 100),
                        (int)(this.getWindowHeight() * 0.25 + (120 * i)),
                        120,
                        120);
                }
            }
        }
        for (int i = 0; i < this.buttons.length - 1; i++) {
            this.buttons[i] = new QuestButton(
                (int)(this.getWindowWidth() * 0.228),
                (int)(this.getWindowHeight() * (0.27 + (0.15 * i))),
                610,
                150);
        }
        this.buttons[3] = new ItemButton(
            (int)(this.getWindowWidth() * 0.228),
            (int)(this.getWindowHeight() * 0.80),
            610,
            150);
    }

    @Override
    public void draw(Graphics2D g2D) {
        super.draw(g2D);
        for (Button button : this.buttons) {
            button.draw(g2D);
        }
        for (ItemButton[] buttonsList : this.itemButtons) {
            for (ItemButton button : buttonsList) {
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
        for (Button button : this.buttons) {
            if (button instanceof StateButton) {
                ((StateButton)button).applyState();
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
        for (ItemButton[] buttonsList : this.itemButtons) {
            for (ItemButton button : buttonsList) {
                button.setMouseIn(false);
                if (button.getButtonBounds().contains(event.getX(), event.getY())) {
                    button.setMouseIn(true);
                }
            }
        }
    }

}
