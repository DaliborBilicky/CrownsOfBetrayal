package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.graphics.ui.Button;
import dalibor.crownsofbetrayal.graphics.ui.ItemButton;
import dalibor.crownsofbetrayal.graphics.ui.QuestButton;
import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.NoItem;
import dalibor.crownsofbetrayal.items.Sellable;
import dalibor.crownsofbetrayal.main.Game;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Pub extends State {
    private static final int SHOP_CAPACITY = 8;
    private final Button[] buttons;
    private final ItemButton[][] inventoryButtons;
    private final ItemButton[][] shopButtons;
    private final Item[][] pubInventory;
    private final ArrayList<Item> shop;
    private final Item[][] shopInventory;

    public Pub(Game game) {
        super(game, new ImageReader().getBufferedImage("res/bg/pub.png"));
        this.buttons = new Button[4];
        this.inventoryButtons = new ItemButton[4][3];
        this.shopButtons = new ItemButton[4][2];
        this.pubInventory = new Item[4][3];
        this.shopInventory = new Item[4][2];
        this.shop = new ArrayList<>();
        this.setButtons();
        this.fillPubInventory();
    }

    private void setButtons() {
        for (int i = 0; i < this.inventoryButtons.length; i++) {
            for (int j = 0; j < this.inventoryButtons[i].length; j++) {
                this.inventoryButtons[i][j] = new ItemButton(
                    (int)(this.getWindowWidth() * 0.48 +
                        (175 / this.getScale() * j)),
                    (int)(this.getWindowHeight() * 0.27 +
                        (175 / this.getScale() * i)),
                    (int)(175 / this.getScale()),
                    (int)(175 / this.getScale()));
            }
        }
        for (int i = 0; i < this.shopButtons.length; i++) {
            for (int j = 0; j < this.shopButtons[i].length; j++) {
                this.shopButtons[i][j] = new ItemButton(
                    (int)(this.getWindowWidth() * 0.805 +
                        (175 / this.getScale() * j)),
                    (int)(this.getWindowHeight() * 0.27 +
                        (175 / this.getScale() * i)),
                    (int)(175 / this.getScale()),
                    (int)(175 / this.getScale()));
            }
        }

        for (int i = 0; i < this.buttons.length - 1; i++) {
            this.buttons[i] = new QuestButton(
                (int)(this.getWindowWidth() * 0.228),
                (int)(this.getWindowHeight() * (0.27 + (0.15 * i))),
                (int)(610 / this.getScale()),
                (int)(150 / this.getScale()));
        }
        this.buttons[3] = new ItemButton(
            (int)(this.getWindowWidth() * 0.228),
            (int)(this.getWindowHeight() * 0.8),
            (int)(610 / this.getScale()),
            (int)(150 / this.getScale()));
    }

    public void fillPubInventory() {
        for (int i = 0; i < this.pubInventory.length; i++) {
            for (int j = 0; j < this.pubInventory[i].length; j++) {
                Item tempItem = this.getPlayer().getItemFromInventory(
                    (j * this.pubInventory.length) + i);
                if (tempItem instanceof Sellable) {
                    tempItem.setI(i);
                    tempItem.setJ(j);
                    this.pubInventory[i][j] = tempItem;
                }
            }
        }
        for (int i = 0; i < this.shopInventory.length; i++) {
            for (int j = 0; j < this.shopInventory[i].length; j++) {
                Item tempItem = new NoItem();
                if (!this.shop.isEmpty()) {
                    if ((j * this.shopInventory.length) + i <
                        this.shop.size()) {
                        tempItem = this.shop.get(
                            (j * this.shopInventory.length) + i);
                    }
                    if (tempItem instanceof Sellable) {
                        tempItem.setI(i);
                        tempItem.setJ(j);
                        this.shopInventory[i][j] = tempItem;
                    }
                } else {
                    tempItem.setI(i);
                    tempItem.setJ(j);
                    this.shopInventory[i][j] = tempItem;
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2D) {
        super.draw(g2D);
        for (Button button : this.buttons) {
            button.draw(g2D);
        }
        for (ItemButton[] buttonsList : this.inventoryButtons) {
            for (ItemButton button : buttonsList) {
                button.draw(g2D);
            }
        }

        for (ItemButton[] buttonsList : this.shopButtons) {
            for (ItemButton button : buttonsList) {
                button.draw(g2D);
            }
        }

        for (int i = 0; i < this.pubInventory.length; i++) {
            for (int j = 0; j < this.pubInventory[i].length; j++) {
                this.pubInventory[i][j].draw(g2D,
                    (int)(this.getWindowWidth() * 0.48 +
                        (175 / this.getScale() * j)),
                    (int)(this.getWindowHeight() * 0.27 +
                        (175 / this.getScale() * i)),
                    (int)(175 / this.getScale()));
            }
        }

        for (int i = 0; i < this.shopInventory.length; i++) {
            for (int j = 0; j < this.shopInventory[i].length; j++) {
                this.shopInventory[i][j].draw(g2D,
                    (int)(this.getWindowWidth() * 0.805 +
                        (175 / this.getScale() * j)),
                    (int)(this.getWindowHeight() * 0.27 +
                        (175 / this.getScale() * i)),
                    (int)(175 / this.getScale()));
            }
        }
        g2D.setFont(new Font("Viner Hand ITC", Font.BOLD, 60));
        g2D.setColor(Color.WHITE);
        g2D.drawString(
            String.valueOf(this.getPlayer().getGoldCoins()),
            (int)(this.getWindowWidth() * 0.67),
            (int)(this.getWindowHeight() * 0.9));
        g2D.drawString(
            String.valueOf(this.getPlayer().getSupplies()),
            (int)(this.getWindowWidth() * 0.33),
            (int)(this.getWindowHeight() * 0.815));
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
                if (this.inventoryButtons[i][j].getButtonBounds()
                    .contains(event.getX(), event.getY())) {
                    Item selectedItem = this.pubInventory[i][j];
                    this.getPlayer().removeItemFromInventory(i, j);
                    this.getPlayer().gainGold(selectedItem.getGoldValue());
                    this.shop.add(selectedItem);
                    this.fillPubInventory();
                }
            }
        }

        for (int i = 0; i < this.shopButtons.length; i++) {
            for (int j = 0; j < this.shopButtons[i].length; j++) {
                if (this.shopButtons[i][j].getButtonBounds()
                    .contains(event.getX(), event.getY())) {
                    Item selectedItem = this.shopInventory[i][j];
                    if (this.getPlayer().getGoldCoins() -
                        selectedItem.getGoldValue() * 2 > 0) {
                        this.getPlayer().putItemToInventory(selectedItem);
                        this.getPlayer().looseGold(
                            selectedItem.getGoldValue() * 2);
                        this.shop.remove(selectedItem);
                        this.fillPubInventory();
                    }
                }
            }
        }

        for (int i = 0; i < this.buttons.length; i++) {
            if (this.buttons[i].getButtonBounds()
                .contains(event.getX(), event.getY())) {
                if (i == this.buttons.length - 1) {
                    if (this.getPlayer().getGoldCoins() - 15 > 0 &&
                        !this.getPlayer().isSuppliesFull()) {
                        int tempSupplies = this.getPlayer().getSupplies();
                        this.getPlayer().setSupplies(tempSupplies + 25);
                        this.getPlayer().looseGold(15);
                    }
                }
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
        for (ItemButton[] buttonsList : this.inventoryButtons) {
            for (ItemButton button : buttonsList) {
                button.setMouseIn(false);
                if (button.getButtonBounds().contains(event.getX(), event.getY())) {
                    button.setMouseIn(true);
                }
            }
        }

        for (ItemButton[] buttonsList : this.shopButtons) {
            for (ItemButton button : buttonsList) {
                button.setMouseIn(false);
                if (button.getButtonBounds().contains(event.getX(), event.getY())) {
                    button.setMouseIn(true);
                }
            }
        }
    }

}
