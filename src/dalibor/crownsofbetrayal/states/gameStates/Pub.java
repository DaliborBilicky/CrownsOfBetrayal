package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.graphics.ui.Button;
import dalibor.crownsofbetrayal.graphics.ui.ItemButton;
import dalibor.crownsofbetrayal.graphics.ui.QuestButton;
import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.NoItem;
import dalibor.crownsofbetrayal.items.Sellable;
import dalibor.crownsofbetrayal.main.Game;
import dalibor.crownsofbetrayal.quests.Quest;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import dalibor.crownsofbetrayal.tools.ImageReader;
import dalibor.crownsofbetrayal.tools.RandomGenerator;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


/**
 * Specialny druh statu kde si vie hrac predat a nakupit itemy, dokupit zasoby a
 * vybrat questy
 */
public class Pub extends State {
    private final Button[] buttons;
    private final ItemButton[][] inventoryButtons;
    private final ItemButton[][] shopButtons;
    private final Item[][] pubInventory;
    private final ArrayList<Item> shop;
    private final Item[][] shopInventory;
    private final Quest[] pubQuests;

    /**
     * Natvrdo nastavene pozadie
     *
     * @param game hlavna trieda
     */
    public Pub(Game game) {
        super(game, new ImageReader().getBufferedImage("res/bg/pub.png"));
        this.buttons = new Button[4];
        this.inventoryButtons = new ItemButton[4][3];
        this.shopButtons = new ItemButton[4][2];
        this.pubInventory = new Item[4][3];
        this.shopInventory = new Item[4][2];
        this.shop = new ArrayList<>();
        this.pubQuests = new Quest[3];
        this.setButtons();
        this.fillPubInventory();
    }

    /**
     * Nastavi tlacidlam spravne hodnoty
     */
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

    /**
     * Naplnenie inventara
     */
    public void fillPubInventory() {
        ArrayList<Item> tempItems = new ArrayList<>();
        for (int i = 0; i < this.getPlayer().getInventoryCapacity(); i++) {
            Item tempItem = this.getPlayer().getItemFromInventory(i);
            if (tempItem instanceof Sellable) {
                tempItems.add(tempItem);
            }
        }
        for (int i = 0; i < this.pubInventory.length; i++) {
            for (int j = 0; j < this.pubInventory[i].length; j++) {
                Item tempItem = tempItems.get(
                    (j * this.pubInventory.length) + i);
                if (tempItem instanceof Sellable) {
                    this.pubInventory[i][j] = tempItem;
                }
            }
        }
        for (int i = 0; i < this.shopInventory.length; i++) {
            for (int j = 0; j < this.shopInventory[i].length; j++) {
                Item tempItem = new NoItem();
                if ((j * this.shopInventory.length) + i <
                    this.shop.size()) {
                    tempItem = this.shop.get(
                        (j * this.shopInventory.length) + i);
                }
                if (tempItem instanceof Sellable) {
                    this.shopInventory[i][j] = tempItem;
                }
            }
        }
    }

    public void generateQuests() {
        RandomGenerator rG = new RandomGenerator();
        for (int i = 0; i < this.pubQuests.length; i++) {
            this.pubQuests[i] = rG.getQuest(this.getPlayer());
        }
    }

    /**
     * Naplnenie obchodu
     */
    public void fillShop() {
        RandomGenerator rG = new RandomGenerator();
        this.shop.clear();
        for (int i = 0; i < rG.getNumOfItems(); i++) {
            Item item = rG.getItemForShop();
            this.shop.add(item);
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
        g2D.setFont(new Font("Viner Hand ITC", Font.BOLD, 60));
        g2D.setColor(Color.RED);
        for (int i = 0; i < this.pubInventory.length; i++) {
            for (int j = 0; j < this.pubInventory[i].length; j++) {
                Item item = this.pubInventory[i][j];
                item.draw(g2D,
                    (int)(this.getWindowWidth() * 0.48 +
                        (175 / this.getScale() * j)),
                    (int)(this.getWindowHeight() * 0.27 +
                        (175 / this.getScale() * i)),
                    (int)(175 / this.getScale()));
                if (item instanceof Sellable &&
                    ((Sellable)item).getPrice() > 0) {
                    g2D.drawString(String.valueOf(((Sellable)item).getPrice()),
                        (int)(this.getWindowWidth() * 0.5 +
                            (175 / this.getScale() * j)),
                        (int)(this.getWindowHeight() * 0.33 +
                            (175 / this.getScale() * i)));
                }
            }
        }

        for (int i = 0; i < this.shopInventory.length; i++) {
            for (int j = 0; j < this.shopInventory[i].length; j++) {
                Item item = this.shopInventory[i][j];
                item.draw(g2D,
                    (int)(this.getWindowWidth() * 0.805 +
                        (175 / this.getScale() * j)),
                    (int)(this.getWindowHeight() * 0.27 +
                        (175 / this.getScale() * i)),
                    (int)(175 / this.getScale()));
                if (item instanceof Sellable &&
                    ((Sellable)item).getPrice() > 0) {
                    g2D.drawString(String.valueOf(
                            ((Sellable)item).getPrice() * 2),
                        (int)(this.getWindowWidth() * 0.82 +
                            (175 / this.getScale() * j)),
                        (int)(this.getWindowHeight() * 0.33 +
                            (175 / this.getScale() * i)));
                }
            }
        }
        g2D.setColor(Color.WHITE);
        g2D.drawString(
            String.valueOf(this.getPlayer().getGoldCoins()),
            (int)(this.getWindowWidth() * 0.67),
            (int)(this.getWindowHeight() * 0.9));
        g2D.drawString(
            String.valueOf(this.getPlayer().getSupplies()),
            (int)(this.getWindowWidth() * 0.33),
            (int)(this.getWindowHeight() * 0.815));

        g2D.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
        for (int i = 0; i < this.pubQuests.length; i++) {
            g2D.drawString(this.pubQuests[i].getDescription(),
                (int)(this.getWindowWidth() * 0.08),
                (int)(this.getWindowHeight() * 0.27 + (165 * i)));
        }
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
                if (this.inventoryButtons[i][j].getButtonBounds()
                    .contains(event.getX(), event.getY())) {
                    Item selectedItem = this.pubInventory[i][j];
                    if (selectedItem instanceof Sellable) {
                        this.getPlayer().removeItemFromInventory(selectedItem);
                        this.getPlayer().gainGold(
                            ((Sellable)selectedItem).getPrice());
                        this.shop.add(selectedItem);
                        this.fillPubInventory();
                    }
                }
            }
        }

        for (int i = 0; i < this.shopButtons.length; i++) {
            for (int j = 0; j < this.shopButtons[i].length; j++) {
                if (this.shopButtons[i][j].getButtonBounds()
                    .contains(event.getX(), event.getY())) {
                    Item selectedItem = this.shopInventory[i][j];
                    if (selectedItem instanceof Sellable) {
                        if (this.getPlayer().getGoldCoins() -
                            ((Sellable)selectedItem).getPrice() * 2 >= 0) {
                            this.getPlayer().putItemToInventory(selectedItem);
                            this.getPlayer().looseGold(
                                ((Sellable)selectedItem).getPrice() * 2);
                            this.shop.remove(selectedItem);
                            this.fillPubInventory();
                        }
                    }
                }
            }
        }

        for (int i = 0; i < this.buttons.length; i++) {
            if (this.buttons[i].getButtonBounds()
                .contains(event.getX(), event.getY())) {
                if (i == this.buttons.length - 1) {
                    if (this.getPlayer().getGoldCoins() - 15 >= 0 &&
                        !this.getPlayer().isSuppliesFull()) {
                        int tempSupplies = this.getPlayer().getSupplies();
                        this.getPlayer().setSupplies(tempSupplies + 25);
                        this.getPlayer().looseGold(15);
                    }
                } else {
                    this.getPlayer().setNewQuest(this.pubQuests[i]);
                    this.pubQuests[i] = new Quest();
                }
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
