package dalibor.crownsOfBetrayal.states.gameStates;

import dalibor.crownsOfBetrayal.graphics.ImageReader;
import dalibor.crownsOfBetrayal.graphics.ui.Button;
import dalibor.crownsOfBetrayal.graphics.ui.LabelButton;
import dalibor.crownsOfBetrayal.states.CurrentState;
import dalibor.crownsOfBetrayal.states.State;
import dalibor.crownsOfBetrayal.states.States;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class GameMenu extends State {
    private final LabelButton[] buttons;

    public GameMenu(CurrentState currentState, int windowWidth, int windowHeight) {
        super(currentState, new ImageReader().getBufferedImage("res/bg/game_menu.png"), windowWidth, windowHeight);
        this.buttons = new LabelButton[6];
        this.setButtons();
    }

    private void setButtons() {
        this.buttons[0] = new LabelButton(
            (int) (this.getWindowWidth() * 0.275),
            (int) (this.getWindowHeight() * 0.33),
            700,
            150,
            States.PUB,
            this.getCurrentState());
        this.buttons[1] = new LabelButton(
            (int) (this.getWindowWidth() * 0.275),
            (int) (this.getWindowHeight() * 0.55),
            700,
            150,
            States.SHOP,
            this.getCurrentState());
        this.buttons[2] = new LabelButton(
            (int) (this.getWindowWidth() * 0.275),
            (int) (this.getWindowHeight() * 0.76),
            700,
            150,
            States.QUESTS,
            this.getCurrentState());
        this.buttons[3] = new LabelButton(
            (int) (this.getWindowWidth() * 0.73),
            (int) (this.getWindowHeight() * 0.33),
            700,
            150,
            States.INVENTORY,
            this.getCurrentState());
        this.buttons[4] = new LabelButton(
            (int) (this.getWindowWidth() * 0.73),
            (int) (this.getWindowHeight() * 0.55),
            700,
            150,
            States.CRAFTING,
            this.getCurrentState());
        this.buttons[5] = new LabelButton(
            (int) (this.getWindowWidth() * 0.73),
            (int) (this.getWindowHeight() * 0.76),
            700,
            150,
            States.WORLD_MAP,
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
        if (event.getX() < 100 && event.getY() < 100) {
            this.getCurrentState().setState(States.MENU);
        }
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
