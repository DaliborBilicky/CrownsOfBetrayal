package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.graphics.ui.Button;
import dalibor.crownsofbetrayal.graphics.ui.ItemButton;
import dalibor.crownsofbetrayal.states.CurrentState;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Inventory extends State {
    private final Button[][] buttons;
    private final Button[] gearButtons;

    public Inventory(CurrentState currentState, int windowWidth, int windowHeight) {
        super(currentState, new ImageReader().getBufferedImage("res/bg/inventory.png"), windowWidth, windowHeight);
        this.buttons = new Button[6][8];
        this.gearButtons = new Button[2];
        this.setButtons();
    }

    private void setButtons() {
        for (int i = 0; i < this.buttons.length; i++) {
            for (int j = 0; j < this.buttons[i].length; j++) {
                this.buttons[i][j] = new ItemButton(
                    (int)(this.getWindowWidth() * 0.45 + (120 * j)),
                    (int)(this.getWindowHeight() * 0.25 + (120 * i)),
                    120,
                    120);
            }
        }
        for (int i = 0; i < this.gearButtons.length; i++) {
            this.gearButtons[i] = new ItemButton(
                (int)(this.getWindowWidth() * 0.14 + (320 * i)),
                (int)(this.getWindowHeight() * 0.404),
                165,
                165
            );
        }
    }

    @Override
    public void draw(Graphics2D g2D) {
        super.draw(g2D);
        for (Button[] buttonsList : this.buttons) {
            for (Button button : buttonsList) {
                button.draw(g2D);
            }
        }
        for (Button button : this.gearButtons) {
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
            this.getCurrentState().setState(States.GAME_MENU);
        }
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        for (Button[] buttonsList : this.buttons) {
            for (Button button : buttonsList) {
                button.setMouseIn(false);
                if (button.getButtonBounds().contains(event.getX(), event.getY())) {
                    button.setMouseIn(true);
                }
            }
        }
        for (Button button : this.gearButtons) {
            button.setMouseIn(false);
            if (button.getButtonBounds().contains(event.getX(), event.getY())) {
                button.setMouseIn(true);
            }
        }
    }
}
