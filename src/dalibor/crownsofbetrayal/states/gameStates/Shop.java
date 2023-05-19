package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.graphics.ui.Button;
import dalibor.crownsofbetrayal.main.Game;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Shop extends State {
    private final Button[][] buttons;

    public Shop(Game game) {
        super(game,
            new ImageReader().getBufferedImage("res/bg/shop.png"));
        this.buttons = new Button[5][10];
        this.setButtons();
    }

    private void setButtons() {
        for (int i = 0; i < this.buttons.length; i++) {
            for (int j = 0; j < this.buttons[i].length; j++) {
                if (j < this.buttons[i].length / 2) {
                    this.buttons[i][j] = new Button(
                        (int)(this.getWindowWidth() * 0.10 +
                            (150 / this.getScale() * j)),
                        (int)(this.getWindowHeight() * 0.25 +
                            (150 / this.getScale() * i)),
                        (int)(150 / this.getScale()),
                        (int)(150 / this.getScale()));
                } else {
                    this.buttons[i][j] = new Button(
                        (int)(this.getWindowWidth() * 0.10 +
                            (150 / this.getScale() * j) +
                            175 / this.getScale()),
                        (int)(this.getWindowHeight() * 0.25 +
                            (150 / this.getScale() * i)),
                        (int)(150 / this.getScale()),
                        (int)(150 / this.getScale()));
                }
            }
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
    }
}
