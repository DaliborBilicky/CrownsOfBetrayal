package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.graphics.ui.Button;
import dalibor.crownsofbetrayal.graphics.ui.ItemButton;
import dalibor.crownsofbetrayal.main.Game;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Inventory extends State {
    private final Button[][] inventoryButtons;
    private final Button[] gearButtons;

    public Inventory(Game game) {
        super(game, new ImageReader().getBufferedImage("res/bg/inventory.png"));
        this.inventoryButtons = new Button[4][6];
        this.gearButtons = new Button[2];
        this.setButtons();
    }

    private void setButtons() {
        for (int i = 0; i < this.inventoryButtons.length; i++) {
            for (int j = 0; j < this.inventoryButtons[i].length; j++) {
                this.inventoryButtons[i][j] = new ItemButton(
                    (int)(this.getWindowWidth() * 0.44 +
                        (175 / this.getScale() * j)),
                    (int)(this.getWindowHeight() * 0.28 +
                        (175 / this.getScale() * i)),
                    (int)(175 / this.getScale()),
                    (int)(175 / this.getScale()));
            }
        }
        for (int i = 0; i < this.gearButtons.length; i++) {
            this.gearButtons[i] = new ItemButton(
                (int)(this.getWindowWidth() * 0.14 +
                    (320 / this.getScale() * i)),
                (int)(this.getWindowHeight() * 0.404),
                (int)(165 / this.getScale()),
                (int)(165 / this.getScale())
            );
        }
    }

    @Override
    public void draw(Graphics2D g2D) {
        super.draw(g2D);
        for (Button[] buttonsList : this.inventoryButtons) {
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
        if (event.getX() < 100 / this.getScale() &&
            event.getY() < 100 / this.getScale()) {
            this.getCurrentState().setState(States.GAME_MENU);
        }
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        for (Button[] buttonsList : this.inventoryButtons) {
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
