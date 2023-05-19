package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.graphics.ui.Button;
import dalibor.crownsofbetrayal.graphics.ui.ItemButton;
import dalibor.crownsofbetrayal.main.Game;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Crafting extends State {
    private final Button[][] buttons;
    private final Button[] craftingButtons;

    public Crafting(Game game) {
        super(game, new ImageReader().getBufferedImage("res/bg/crafting.png"));
        this.buttons = new Button[2][6];
        this.craftingButtons = new Button[3];
        this.setButtons();
    }

    private void setButtons() {
        for (int i = 0; i < this.buttons.length; i++) {
            for (int j = 0; j < this.buttons[i].length; j++) {
                this.buttons[i][j] = new ItemButton(
                    (int)(this.getWindowWidth() * 0.19 +
                        (240 / this.getScale() * j)),
                    (int)(this.getWindowHeight() * 0.50 +
                        (240 / this.getScale() * i)),
                    (int)(240 / this.getScale()),
                    (int)(240 / this.getScale()));
            }
        }
        for (int i = 0; i < this.craftingButtons.length - 1; i++) {
            this.craftingButtons[i] = new ItemButton(
                (int)(this.getWindowWidth() * 0.237 +
                    (460 / this.getScale() * i)),
                (int)(this.getWindowHeight() * 0.22),
                (int)(340 / this.getScale()),
                (int)(180 / this.getScale())
            );
        }
        this.craftingButtons[2] = new ItemButton(
            (int)(this.getWindowWidth() * 0.763),
            (int)(this.getWindowHeight() * 0.22),
            (int)(340 / this.getScale()),
            (int)(180 / this.getScale())
        );
    }

    @Override
    public void draw(Graphics2D g2D) {
        super.draw(g2D);
        for (Button[] buttonsList : this.buttons) {
            for (Button button : buttonsList) {
                button.draw(g2D);
            }
        }
        for (Button button : this.craftingButtons) {
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
        if (event.getX() < 100 / this.getScale() &&
            event.getY() < 100 / this.getScale()) {
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
        for (Button button : this.craftingButtons) {
            button.setMouseIn(false);
            if (button.getButtonBounds().contains(event.getX(), event.getY())) {
                button.setMouseIn(true);
            }
        }
    }
}
