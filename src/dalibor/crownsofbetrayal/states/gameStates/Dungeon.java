package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.characters.enemies.Enemy;
import dalibor.crownsofbetrayal.graphics.ui.EnemyButton;
import dalibor.crownsofbetrayal.graphics.ui.ItemButton;
import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.NoItem;
import dalibor.crownsofbetrayal.items.Usable;
import dalibor.crownsofbetrayal.items.weapons.Bow;
import dalibor.crownsofbetrayal.main.Game;
import dalibor.crownsofbetrayal.quests.KillingQuest;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import dalibor.crownsofbetrayal.tools.ImageReader;
import dalibor.crownsofbetrayal.tools.RandomGenerator;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Specialny druh statu kde sa odohrava bytka hrac vs nepriatel
 */
public class Dungeon extends State {
    private final Random random;
    private final ArrayList<EnemyButton> enemyButtons;
    private final ArrayList<Enemy> enemies;
    private final ItemButton[] itemButtons;
    private final ArrayList<Item> items;
    private int numberOfEnemies;

    /**
     * Natvrdo nastavene pozadie
     *
     * @param game hlavna trieda
     */
    public Dungeon(Game game) {
        super(game, new ImageReader()
            .getBufferedImage("res/bg/dungeon.png"));
        this.random = new Random();
        this.enemyButtons = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.itemButtons = new ItemButton[6];
        this.items = new ArrayList<>();
        this.setItemButtons();
    }


    /**
     * Nastavi tlacidlam spravne hodnoty
     */
    private void setItemButtons() {
        for (int i = 0; i < this.itemButtons.length; i++) {
            this.itemButtons[i] = new ItemButton(
                (int)(this.getWindowWidth() * 0.14 +
                    ((275 / this.getScale()) * i)),
                (int)(this.getWindowHeight() * 0.45 +
                    ((275 / this.getScale()))),
                (int)(275 / this.getScale()),
                (int)(275 / this.getScale())
            );
        }
    }

    /**
     * Pripravy dungeon aby sa dal pouzivat
     */
    public void setDungeon() {
        RandomGenerator rG = new RandomGenerator();
        this.numberOfEnemies = this.random.nextInt(1, 6);
        for (int i = 0; i < this.numberOfEnemies; i++) {
            this.enemyButtons.add(new EnemyButton(
                (int)(this.getWindowWidth() * 0.165 + (325 * i)),
                (int)(this.getWindowHeight() * 0.26),
                (int)(275 / this.getScale())));
            Enemy enemy = rG.getEnemy();
            enemy.setImageLocation(
                (int)(this.getWindowWidth() * 0.165 + (325 * i)),
                (int)(this.getWindowHeight() * 0.26),
                (int)(275 / this.getScale())
            );
            this.enemies.add(enemy);
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
        g2D.setFont(new Font("Viner Hand ITC", Font.BOLD, 60));
        g2D.setColor(Color.WHITE);
        g2D.drawString(
            String.valueOf(this.getPlayer().getHealth()),
            (int)(this.getWindowWidth() * 0.22),
            (int)(this.getWindowHeight() * 0.51));

        for (ItemButton itemButton : this.itemButtons) {
            itemButton.draw(g2D);
        }
        int index = 0;
        for (int i = 0; i < this.itemButtons.length; i++) {
            Item item = this.items.get(i);
            if (item instanceof Usable && !(item instanceof NoItem)) {
                item.draw(g2D,
                    (int)(this.getWindowWidth() * 0.14 +
                        ((275 / this.getScale()) * index)),
                    (int)(this.getWindowHeight() * 0.45 +
                        ((275 / this.getScale()))),
                    (int)(275 / this.getScale()));
                index++;
            }
        }

        if (!this.enemies.isEmpty() && this.numberOfEnemies > 0) {
            for (int i = 0; i < this.numberOfEnemies; i++) {
                this.enemies.get(i).drawEnemy(g2D);
                this.enemyButtons.get(i).draw(g2D);
            }
        }
        if (this.enemies.isEmpty() && this.getPlayer().getHealth() > 0) {
            g2D.setColor(new Color(0, 0, 0, 150));
            g2D.fillRect(0, 0, this.getGame().getWidth(),
                this.getGame().getHeight());
            g2D.setFont(new Font("Viner Hand ITC", Font.BOLD, 280));
            g2D.setColor(Color.BLACK);
            g2D.drawString(
                "You won!",
                (int)(this.getGame().getWidth() * 0.175),
                (int)(this.getGame().getHeight() * 0.51));
            g2D.setFont(new Font("Viner Hand ITC", Font.BOLD, 300));
            g2D.setColor(Color.RED);
            g2D.drawString(
                "You won!",
                (int)(this.getGame().getWidth() * 0.15),
                this.getGame().getHeight() / 2);
        }
        this.getPlayer().draw(g2D);

    }

    /**
     * Kontroluje ci je hrac na tahu, ci mozu nepriatelia zutocit a ci nepriatel
     * nezomrel
     */
    @Override
    public void update() {
        this.getPlayer().removeDoneQuests();
        Enemy killedEnemy = null;
        if (!this.getPlayer().isOnMove()) {
            if (!this.enemies.isEmpty()) {
                for (int i = 0; i < this.numberOfEnemies; i++) {
                    this.getPlayer().takeDamage(
                        this.enemies.get(i).getDamage());
                    this.enemies.get(i).makeSpecialAttack(this.getPlayer());
                    if (this.enemies.get(i).getHealth() <= 0) {
                        killedEnemy = this.enemies.get(i);
                        this.enemies.remove(killedEnemy);
                        this.enemyButtons.remove(this.enemyButtons.get(i));
                        this.numberOfEnemies--;
                    }
                }
                this.getPlayer().setOnMove(true);
            }
        }

        for (int i = 0; i < 10; i++) {
            if (this.getPlayer().getQuest(i) instanceof KillingQuest &&
                killedEnemy != null) {
                ((KillingQuest)this.getPlayer().getQuest(i))
                    .killedEnemy(killedEnemy);
            }
        }
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
            this.getCurrentState().setState(States.WORLD_MAP);
            this.enemyButtons.clear();
            if (this.getPlayer().getHealth() <= 0) {
                this.getPlayer().resetHealth();
                int tempSupplies = this.getPlayer().getSupplies();
                this.getPlayer().setSupplies(tempSupplies - 10);
            }

            if (!this.enemies.isEmpty()) {
                int tempSupplies = this.getPlayer().getSupplies();
                this.getPlayer().setSupplies(tempSupplies - 10);
            } else {
                this.putWinningInInventory();
            }
            this.getPlayer().resetDamage();
            this.getPlayer().setStunned(false);
            this.enemies.clear();
        }
        if (this.getPlayer().getHealth() > 0 && !this.enemies.isEmpty()) {
            for (int i = 0; i < this.numberOfEnemies; i++) {
                if (this.enemyButtons.get(i).getButtonBounds()
                    .contains(event.getX(), event.getY())) {
                    if (this.getPlayer().getWeapon() instanceof Bow &&
                        ((Bow)this.getPlayer().getWeapon())
                            .isDealingMultipleHits()) {
                        for (Enemy enemy : this.enemies) {
                            enemy.takeDamage(this.getPlayer().dealDamage());
                        }
                    } else {
                        this.enemies.get(i).takeDamage(
                            this.getPlayer().dealDamage());
                    }
                    this.enemies.get(i).setTakingDamage(true);
                    this.getPlayer().setOnMove(false);
                    break;
                }
            }
            if (!this.getPlayer().isStunned()) {
                for (int i = 0; i < this.itemButtons.length; i++) {
                    if (this.itemButtons[i].getButtonBounds()
                        .contains(event.getX(), event.getY()) &&
                        !this.getPlayer().isInventoryEmpty()) {
                        Item selectedItem = this.items.get(i);
                        if (selectedItem instanceof Usable) {
                            ((Usable)selectedItem).use(this.getPlayer());
                            this.getPlayer().removeItemFromInventory(selectedItem);
                            this.items.remove(selectedItem);
                            this.fillInventory();
                            this.getPlayer().setOnMove(false);
                        }
                    }
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
        if (this.getPlayer().getHealth() > 0 && !this.enemies.isEmpty()) {
            for (int i = 0; i < this.numberOfEnemies; i++) {
                this.enemyButtons.get(i).setMouseIn(false);
                if (this.enemyButtons.get(i).getButtonBounds()
                    .contains(event.getX(), event.getY())) {
                    this.enemyButtons.get(i).setMouseIn(true);
                } else {
                    this.enemies.get(i).setTakingDamage(false);
                }
            }
            for (ItemButton itemButton : this.itemButtons) {
                itemButton.setMouseIn(false);
                if (itemButton.getButtonBounds()
                    .contains(event.getX(), event.getY())) {
                    itemButton.setMouseIn(true);
                }
            }
        }
    }

    /**
     * Prida hracovy nahodne itemy ak vyhra proti nepriatelom
     */
    private void putWinningInInventory() {
        RandomGenerator rG = new RandomGenerator();
        for (int i = 0; i < rG.getNumOfItems(); i++) {
            this.getPlayer().putItemToInventory(rG.getItem());
        }
    }

    /**
     * Naplnenie dungeon inventara
     */
    public void fillInventory() {
        for (int i = 0; i < this.getPlayer().getInventoryCapacity(); i++) {
            Item selectedItem = this.getPlayer().getItemFromInventory(i);
            if (selectedItem instanceof Usable) {
                this.items.add(selectedItem);
            }
        }
    }

}
