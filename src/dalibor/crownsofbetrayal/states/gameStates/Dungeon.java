package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.characters.enemies.Enemy;
import dalibor.crownsofbetrayal.characters.enemies.Snowman;
import dalibor.crownsofbetrayal.graphics.ui.EnemyButton;
import dalibor.crownsofbetrayal.graphics.ui.ItemButton;
import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.NoItem;
import dalibor.crownsofbetrayal.items.Usable;
import dalibor.crownsofbetrayal.items.weapons.Bow;
import dalibor.crownsofbetrayal.main.Game;
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

public class Dungeon extends State {
    private final Random random;
    private final ArrayList<EnemyButton> enemyButtons;
    private final ArrayList<Enemy> enemies;
    private final ItemButton[] itemButtons;
    private final ArrayList<Item> usableItems;
    private int numberOfEnemies;

    public Dungeon(Game game) {
        super(game, new ImageReader()
            .getBufferedImage("res/bg/dungeon.png"));
        this.random = new Random();
        this.enemyButtons = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.itemButtons = new ItemButton[6];
        this.usableItems = new ArrayList<>();
        this.setItemButtons();
    }


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

    public void setDungeon() {
        for (int i = 0; i < this.getPlayer().getInventoryCapacity(); i++) {
            Item tempItem = this.getPlayer().getItemFromInventory(i);
            if (tempItem instanceof Usable) {
                this.usableItems.add(tempItem);
            }
        }
        this.numberOfEnemies = this.random.nextInt(1, 6);
        for (int i = 0; i < this.numberOfEnemies; i++) {
            this.enemyButtons.add(new EnemyButton(
                (int)(this.getWindowWidth() * 0.165 + (325 * i)),
                (int)(this.getWindowHeight() * 0.26),
                (int)(275 / this.getScale())));
            Enemy enemy = this.getRandomEnemy();
            enemy.setImageLocation(
                (int)(this.getWindowWidth() * 0.165 + (325 * i)),
                (int)(this.getWindowHeight() * 0.26),
                (int)(275 / this.getScale())
            );
            this.enemies.add(enemy);
        }
    }

    private Enemy getRandomEnemy() {
        int num = this.random.nextInt(1, 100);
        //        if (1 <= num && num < 20) {
        //            return new Ghost();
        //        } else if (20 <= num && num < 40) {
        //            return new Snowman();
        //        } else if (40 <= num && num < 60) {
        //            return new Scarecrow();
        //        } else if (60 <= num && num < 80) {
        //            return new Thief();
        //        } else {
        //            return new Jellyfish();
        //        }
        return new Snowman();
    }

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

        for (int i = 0; i < this.itemButtons.length; i++) {
            Item item = this.usableItems.get(i);
            if (item instanceof Usable && !(item instanceof NoItem)) {
                item.draw(g2D,
                    (int)(this.getWindowWidth() * 0.14 +
                        ((275 / this.getScale()) * i)),
                    (int)(this.getWindowHeight() * 0.45 +
                        ((275 / this.getScale()))),
                    (int)(275 / this.getScale()));
            }
        }
        if (!this.enemies.isEmpty()) {
            for (int i = 0; i < this.numberOfEnemies; i++) {
                this.enemies.get(i).drawEnemy(g2D);
                this.enemyButtons.get(i).draw(g2D);
            }
        } else {
            g2D.setColor(new Color(0, 0, 0, 150));
            g2D.fillRect(0, 0, this.getGame().getWidth(), this.getGame().getHeight());
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

    @Override
    public void update() {
        if (!this.getPlayer().isOnMove()) {
            if (!this.enemies.isEmpty()) {
                for (int i = 0; i < this.numberOfEnemies; i++) {
                    this.getPlayer().takeDamage(
                        this.enemies.get(i).dealDamage());
                    this.enemies.get(i).makeSpecialAttack(this.getPlayer());
                    System.out.println(this.getPlayer().isOnMove());
                    if (this.enemies.get(i).getHealth() <= 0) {
                        this.enemies.remove(this.enemies.get(i));
                        this.enemyButtons.remove(this.enemyButtons.get(i));
                        this.numberOfEnemies--;
                    }
                }
            }
        } else {
            this.getPlayer().setOnMove(true);
        }
    }

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
            this.enemies.clear();
        }
        if (this.getPlayer().getHealth() > 0 && !this.enemies.isEmpty() &&
            !this.getPlayer().isStunned()) {
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
            for (int i = 0; i < this.itemButtons.length; i++) {
                if (this.itemButtons[i].getButtonBounds()
                    .contains(event.getX(), event.getY()) &&
                    !this.getPlayer().isInventoryEmpty()) {
                    Item selectedItem =
                        this.getPlayer().getItemFromInventory(i);
                    if (selectedItem instanceof Usable) {
                        ((Usable)selectedItem).use(this.getPlayer());
                        this.getPlayer().removeItemFromInventory(i);
                    }
                }
            }
        }
    }

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

    private void putWinningInInventory() {
        RandomGenerator rG = new RandomGenerator();
        for (int i = 0; i < rG.getNumOfItems(); i++) {
            this.getPlayer().putItemToInventory(rG.getItem());
        }
    }
}
