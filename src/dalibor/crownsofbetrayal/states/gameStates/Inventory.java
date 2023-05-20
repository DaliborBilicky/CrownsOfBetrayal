package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.characters.CapacityOverflowException;
import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.graphics.ui.Button;
import dalibor.crownsofbetrayal.graphics.ui.ItemButton;
import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.Wearable;
import dalibor.crownsofbetrayal.items.armor.Shield;
import dalibor.crownsofbetrayal.items.weapons.Weapon;
import dalibor.crownsofbetrayal.main.Game;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class Inventory extends State {
    private final Item[][] inventory;
    private final Button[][] inventoryButtons;
    private final Button[] gearButtons;

    public Inventory(Game game) {
        super(game, new ImageReader().getBufferedImage("res/bg/inventory.png"));
        this.inventory = new Item[4][6];
        this.inventoryButtons = new Button[4][6];
        this.gearButtons = new Button[2];
        this.setButtons();
        this.fillInventory();
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
                (int)(165 / this.getScale()));
        }
    }

    private void fillInventory() {
        for (int i = 0; i < this.inventory.length; i++) {
            for (int j = 0; j < this.inventory[i].length; j++) {
                Item tempItem = this.getPlayer().getItemFromInventory(
                    (j * this.inventory.length) + i);
                tempItem.setI(i);
                tempItem.setJ(j);
                this.inventory[i][j] = tempItem;
            }
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
        this.getPlayer().getWeapon().draw(g2D,
            (int)(this.getWindowWidth() * 0.14),
            (int)(this.getWindowHeight() * 0.404),
            (int)(165 / this.getScale()));
        this.getPlayer().getShield().draw(g2D,
            (int)(this.getWindowWidth() * 0.307),
            (int)(this.getWindowHeight() * 0.404),
            (int)(165 / this.getScale()));

        for (int i = 0; i < this.inventory.length; i++) {
            for (int j = 0; j < this.inventory[i].length; j++) {
                g2D.drawImage(this.inventory[i][j].getImage(),
                    (int)((this.getWindowWidth() * 0.44 +
                        (175 / this.getScale() * j)) - 87.5),
                    (int)((this.getWindowHeight() * 0.28 +
                        (175 / this.getScale() * i)) - 87.5),
                    (int)(175 / this.getScale()),
                    (int)(175 / this.getScale()),
                    null);
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

        for (int i = 0; i < this.inventoryButtons.length; i++) {
            for (int j = 0; j < this.inventoryButtons[i].length; j++) {
                if (this.inventory[i][j] instanceof Wearable) {
                    if (this.inventoryButtons[i][j].getButtonBounds()
                        .contains(event.getX(), event.getY())) {
                        Item selectedItem = this.inventory[i][j];
                        if (selectedItem instanceof Weapon &&
                            !(this.getPlayer().getWeapon() instanceof Weapon)) {
                            this.getPlayer().setWeapon(selectedItem);
                            this.getPlayer().removeItemFromInventory(i, j);
                        }
                        if (selectedItem instanceof Shield &&
                            !(this.getPlayer().getShield() instanceof Shield)) {
                            this.getPlayer().setShield(selectedItem);
                            this.getPlayer().removeItemFromInventory(i, j);
                        }
                        this.fillInventory();
                    }
                }
            }
        }
        for (int i = 0; i < this.gearButtons.length; i++) {
            try {
                if (this.gearButtons[i].getButtonBounds()
                    .contains(event.getX(), event.getY())) {
                    if (i == 0) {
                        this.getPlayer().putItemInInventory(
                            this.getPlayer().getWeapon());
                        this.getPlayer().setWeapon(new Item());
                    } else {
                        this.getPlayer().putItemInInventory(
                            this.getPlayer().getShield());
                        this.getPlayer().setShield(new Item());
                    }
                    this.fillInventory();
                }
            } catch (CapacityOverflowException e) {
                JOptionPane.showMessageDialog(
                    null,
                    e.getMessage());
            }

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
