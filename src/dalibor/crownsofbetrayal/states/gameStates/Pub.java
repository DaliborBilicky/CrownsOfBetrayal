package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.graphics.ui.Button;
import dalibor.crownsofbetrayal.graphics.ui.ItemButton;
import dalibor.crownsofbetrayal.graphics.ui.QuestButton;
import dalibor.crownsofbetrayal.states.CurrentState;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Pub extends State {
    private final Button[] buttons;

    public Pub(CurrentState currentState, int windowWidth, int windowHeight) {
        super(currentState, new ImageReader().getBufferedImage("res/bg/pub.png"), windowWidth, windowHeight);
        this.buttons = new Button[4];
        this.setButtons();
    }

    private void setButtons() {
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
        for (Button button : this.buttons) {
            button.setMouseIn(false);
            if (button.getButtonBounds().contains(event.getX(), event.getY())) {
                button.setMouseIn(true);
            }
        }
    }

}
