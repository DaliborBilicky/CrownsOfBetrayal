package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.graphics.ui.Button;
import dalibor.crownsofbetrayal.graphics.ui.QuestButton;
import dalibor.crownsofbetrayal.states.CurrentState;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Quests extends State {
    private final QuestButton[][] questButtons;

    public Quests(CurrentState currentState, int windowWidth, int windowHeight) {
        super(currentState, new ImageReader().getBufferedImage("res/bg/quests.png"), windowWidth, windowHeight);
        this.questButtons = new QuestButton[5][2];
        this.setButtons();
    }

    private void setButtons() {
        for (int i = 0; i < this.questButtons.length; i++) {
            for (int j = 0; j < this.questButtons[i].length; j++) {
                this.questButtons[i][j] = new QuestButton(
                    (int)(this.getWindowWidth() * 0.077 + (890 * j)),
                    (int)(this.getWindowHeight() * 0.205 + ((150 + (i * 1.1)) * i)),
                    60,
                    60
                );
            }
        }
    }


    @Override
    public void draw(Graphics2D g2D) {
        super.draw(g2D);
        for (Button[] buttons : this.questButtons) {
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
        for (Button[] buttons : this.questButtons) {
            for (Button button : buttons) {
                button.setMouseIn(false);
                if (button.getButtonBounds().contains(event.getX(), event.getY())) {
                    button.setMouseIn(true);
                }
            }
        }
    }
}
