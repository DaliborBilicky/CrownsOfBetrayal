package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.characters.enemies.Enemy;
import dalibor.crownsofbetrayal.characters.enemies.Ghost;
import dalibor.crownsofbetrayal.characters.enemies.Jellyfish;
import dalibor.crownsofbetrayal.characters.enemies.Scarecrow;
import dalibor.crownsofbetrayal.characters.enemies.Snowman;
import dalibor.crownsofbetrayal.characters.enemies.Thief;
import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.graphics.ui.EnemyButton;
import dalibor.crownsofbetrayal.graphics.ui.ItemButton;
import dalibor.crownsofbetrayal.items.weapons.Bow;
import dalibor.crownsofbetrayal.main.Game;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
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
    private final ItemButton[][] itemButtons;
    private int numberOfEnemies;

    public Dungeon(Game game) {
        super(game, new ImageReader()
            .getBufferedImage("res/bg/dungeon.png"));
        this.random = new Random();
        this.enemyButtons = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.itemButtons = new ItemButton[2][6];
        this.setItemButtons();
    }

    private void setItemButtons() {
        for (int i = 0; i < this.itemButtons.length; i++) {
            for (int j = 0; j < this.itemButtons[i].length; j++) {
                this.itemButtons[i][j] = new ItemButton(
                    (int)(this.getWindowWidth() * 0.2 +
                        ((200 / this.getScale()) * j)),
                    (int)(this.getWindowHeight() * 0.62 +
                        ((200 / this.getScale()) * i)),
                    (int)(200 / this.getScale()),
                    (int)(200 / this.getScale())
                );
            }
        }
    }

    public void setDungeon() {
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
        if (1 <= num && num < 20) {
            return new Ghost();
        } else if (20 <= num && num < 40) {
            return new Snowman();
        } else if (40 <= num && num < 60) {
            return new Scarecrow();
        } else if (60 <= num && num < 80) {
            return new Thief();
        } else {
            return new Jellyfish();
        }
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
        if (!this.enemies.isEmpty()) {
            for (int i = 0; i < this.numberOfEnemies; i++) {
                this.enemies.get(i).drawEnemy(g2D);
                this.enemyButtons.get(i).draw(g2D);
            }
        }
        for (ItemButton[] itemButtonsList : this.itemButtons) {
            for (ItemButton itemButton : itemButtonsList) {
                itemButton.draw(g2D);
            }
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
                    if (this.enemies.get(i).getHealth() <= 0) {
                        this.enemies.remove(this.enemies.get(i));
                        this.enemyButtons.remove(this.enemyButtons.get(i));
                        this.numberOfEnemies--;
                    }
                }
            }
            this.getPlayer().setOnMove(true);
        }
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
            this.getCurrentState().setState(States.WORLD_MAP);
            this.enemyButtons.clear();
            this.enemies.clear();
            if (this.getPlayer().getHealth() <= 0) {
                this.getPlayer().resetHealth();
            } else {
                int tempSupplies = this.getPlayer().getSupplies();
                this.getPlayer().setSupplies(tempSupplies - 10);
            }
        }
        if (this.getPlayer().getHealth() > 0) {
            if (!this.enemies.isEmpty()) {
                for (int i = 0; i < this.numberOfEnemies; i++) {
                    if (this.enemyButtons.get(i).getButtonBounds()
                        .contains(event.getX(), event.getY()) &&
                        !this.enemies.isEmpty()) {
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
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        if (this.getPlayer().getHealth() > 0) {
            if (!this.enemies.isEmpty()) {
                for (int i = 0; i < this.numberOfEnemies; i++) {
                    this.enemyButtons.get(i).setMouseIn(false);
                    if (this.enemyButtons.get(i).getButtonBounds()
                        .contains(event.getX(), event.getY())) {
                        this.enemyButtons.get(i).setMouseIn(true);
                    } else {
                        this.enemies.get(i).setTakingDamage(false);
                    }
                }
            }
            for (ItemButton[] itemButtonsList : this.itemButtons) {
                for (ItemButton itemButton : itemButtonsList) {
                    itemButton.setMouseIn(false);
                    if (itemButton.getButtonBounds()
                        .contains(event.getX(), event.getY())) {
                        itemButton.setMouseIn(true);
                    }
                }
            }
        }
    }
}
