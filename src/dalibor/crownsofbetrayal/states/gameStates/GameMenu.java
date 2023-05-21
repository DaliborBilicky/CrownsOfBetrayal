package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.graphics.ui.Button;
import dalibor.crownsofbetrayal.graphics.ui.StateButton;
import dalibor.crownsofbetrayal.main.Game;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import dalibor.crownsofbetrayal.tools.ImageReader;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class GameMenu extends State {
    private final StateButton[] buttons;

    public GameMenu(Game game) {
        super(game, new ImageReader().getBufferedImage("res/bg/game_menu.png"));
        this.buttons = new StateButton[4];
        this.setButtons();
    }

    private void setButtons() {
        for (int i = 0; i < this.buttons.length / 2; i++) {
            this.buttons[i] = new StateButton(
                (int)(this.getWindowWidth() * 0.275),
                (int)(this.getWindowHeight() * (0.39 + (0.26 * i))),
                (int)(700 / this.getScale()),
                (int)(150 / this.getScale()));
            this.buttons[i].setCurrentState(this.getCurrentState());
        }
        for (int i = this.buttons.length / 2; i < this.buttons.length; i++) {
            this.buttons[i] = new StateButton(
                (int)(this.getWindowWidth() * 0.73),
                (int)(this.getWindowHeight() *
                    (0.39 + (0.26 * (i - this.buttons.length / 2)))),
                (int)(700 / this.getScale()),
                (int)(150 / this.getScale()));
            this.buttons[i].setCurrentState(this.getCurrentState());
        }
        this.buttons[0].setState(States.PUB);
        this.buttons[1].setState(States.QUESTS);
        this.buttons[2].setState(States.INVENTORY);
        this.buttons[3].setState(States.WORLD_MAP);
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
        if (event.getX() < 100 / this.getScale() &&
            event.getY() < 100 / this.getScale()) {
            this.getCurrentState().setState(States.MENU);
        }
        for (StateButton button : this.buttons) {
            if (button.getButtonBounds().contains(event.getX(), event.getY())) {
                button.applyState();
                this.getGame().getInventory().fillInventory();
                this.getGame().getDungeon().fillInventory();
                if (button.getState().equals(States.PUB)) {
                    this.getGame().getPub().fillShop();
                }
                this.getGame().getPub().fillPubInventory();
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
