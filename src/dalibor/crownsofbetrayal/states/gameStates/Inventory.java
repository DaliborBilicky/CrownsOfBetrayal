package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.graphics.ui.Button;
import dalibor.crownsofbetrayal.graphics.ui.ItemButton;
import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.NoItem;
import dalibor.crownsofbetrayal.items.Wearable;
import dalibor.crownsofbetrayal.items.shields.Shield;
import dalibor.crownsofbetrayal.items.weapons.Weapon;
import dalibor.crownsofbetrayal.main.Game;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import dalibor.crownsofbetrayal.tools.ImageReader;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;


/**
 * Specialny druh statu kde si hrac vie pozriet ake itemy ma a ake ma nasadene
 */
public class Inventory extends State {
    private final Item[][] inventory;
    private final Button[][] inventoryButtons;
    private final Button[] gearButtons;

    /**
     * Natvrdo nastavene pozadie
     *
     * @param game hlavna trieda
     */
    public Inventory(Game game) {
        super(game, new ImageReader().getBufferedImage("res/bg/inventory.png"));
        this.inventory = new Item[4][6];
        this.inventoryButtons = new Button[4][6];
        this.gearButtons = new Button[2];
        this.setButtons();
        this.fillInventory();
    }

    /**
     * Nastavi tlacidlam spravne hodnoty
     */
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

    /**
     * Naplnenie inventara
     */
    public void fillInventory() {
        for (int i = 0; i < this.inventory.length; i++) {
            for (int j = 0; j < this.inventory[i].length; j++) {
                Item tempItem = this.getPlayer().getItemFromInventory(
                    (j * this.inventory.length) + i);
                this.inventory[i][j] = tempItem;
            }

        }
    }

    /**
     * Vykresluje vsetko co sa deje na obrazovke ak je Dungeon state aktivny
     *
     * @param g2D java trieda na vykreslovanie
     */
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
                this.inventory[i][j].draw(g2D,
                    (int)(this.getWindowWidth() * 0.44 +
                        (175 / this.getScale() * j)),
                    (int)(this.getWindowHeight() * 0.28 +
                        (175 / this.getScale() * i)),
                    (int)(175 / this.getScale()));
            }
        }
        g2D.setFont(new Font("Viner Hand ITC", Font.BOLD, 45));
        g2D.setColor(Color.WHITE);
        g2D.drawString(
            String.valueOf(this.getPlayer().getHealth()),
            (int)(this.getGame().getWidth() * 0.25),
            (int)(this.getGame().getHeight() * 0.56));
        g2D.drawString(
            String.valueOf(this.getPlayer().getDamage()),
            (int)(this.getGame().getWidth() * 0.25),
            (int)(this.getGame().getHeight() * 0.63));
        Item item = this.getPlayer().getShield();
        if (item instanceof Shield) {
            g2D.drawString(
                String.valueOf(((Shield)item).getProtection()),
                (int)(this.getGame().getWidth() * 0.25),
                (int)(this.getGame().getHeight() * 0.69));
        }
        g2D.drawString(
            String.valueOf(this.getPlayer().getGoldCoins()),
            (int)(this.getGame().getWidth() * 0.25),
            (int)(this.getGame().getHeight() * 0.75));
        g2D.drawString(
            String.valueOf(this.getPlayer().getSupplies()),
            (int)(this.getGame().getWidth() * 0.25),
            (int)(this.getGame().getHeight() * 0.82));
    }

    /**
     * Kontroluje ci hrac nema splneny quest
     */
    @Override
    public void update() {
        this.getPlayer().removeDoneQuests();
    }

    /**
     * Kontroluje ci sa dane tlacidlo pouzilo a ak hej tak vykona funkciu
     * tlacidla
     *
     * @param event event z mysky
     */
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
                            this.getPlayer().removeItemFromInventory(selectedItem);
                        }
                        if (selectedItem instanceof Shield &&
                            !(this.getPlayer().getShield() instanceof Shield)) {
                            this.getPlayer().setShield(selectedItem);
                            this.getPlayer().removeItemFromInventory(selectedItem);
                        }
                        this.fillInventory();
                    }
                }
            }
        }
        for (int i = 0; i < this.gearButtons.length; i++) {
            if (this.gearButtons[i].getButtonBounds()
                .contains(event.getX(), event.getY())) {
                if (i == 0) {
                    this.getPlayer().putItemToInventory(
                        this.getPlayer().getWeapon());
                    this.getPlayer().setWeapon(new NoItem());
                } else {
                    this.getPlayer().putItemToInventory(
                        this.getPlayer().getShield());
                    this.getPlayer().setShield(new NoItem());
                }
                this.fillInventory();
            }
        }
    }

    /**
     * Kontroluje ci kurzor je nad tlacidlom ak hej tak sa nastavy tlcidlo
     *
     * @param event event z mysky
     */
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
