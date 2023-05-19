package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.characters.enemies.Enemy;
import dalibor.crownsofbetrayal.characters.enemies.Ghost;
import dalibor.crownsofbetrayal.characters.enemies.Jellyfish;
import dalibor.crownsofbetrayal.characters.enemies.Scarecrow;
import dalibor.crownsofbetrayal.characters.enemies.Snowman;
import dalibor.crownsofbetrayal.characters.enemies.Thief;
import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.graphics.ui.EnemyButton;
import dalibor.crownsofbetrayal.main.Game;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Dungeon extends State {
    private final Random random;
    private EnemyButton[] enemyButtons;
    private Enemy[] enemies;
    private int numberOfEnemies;

    public Dungeon(Game game) {
        super(game, new ImageReader().getBufferedImage("res/bg/dungeon.png"));
        this.random = new Random();
    }

    public void setDungeon() {
        this.numberOfEnemies = this.random.nextInt(1, 6);
        this.enemyButtons = new EnemyButton[this.numberOfEnemies];
        this.enemies = new Enemy[this.numberOfEnemies];
        for (int i = 0; i < this.numberOfEnemies; i++) {
            this.enemyButtons[i] = new EnemyButton(
                (int)(this.getWindowWidth() * 0.165 + (325 * i)),
                (int)(this.getWindowHeight() * 0.26),
                (int)(275 / this.getScale()));
            Enemy enemy = this.getRandomEnemy();
            enemy.setImageLocation(
                (int)(this.getWindowWidth() * 0.165 + (325 * i)),
                (int)(this.getWindowHeight() * 0.26),
                (int)(275 / this.getScale())
            );
            this.enemies[i] = enemy;
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
        for (int i = 0; i < this.numberOfEnemies; i++) {
            this.enemies[i].drawEnemy(g2D);
            this.enemyButtons[i].draw(g2D);
        }
    }

    @Override
    public void update() {
        if (!this.getPlayer().isOnMove()) {
            for (Enemy enemy : this.enemies) {
                this.getPlayer().takeDamage(enemy.dealDamage());
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
        }
        for (int i = 0; i < this.numberOfEnemies; i++) {
            if (this.enemyButtons[i].getButtonBounds()
                .contains(event.getX(), event.getY())) {
                this.enemies[i].takeDamage(this.getPlayer().dealDamage());
                this.getPlayer().setOnMove(false);
                this.enemies[i].setTakingDamage(true);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        for (int i = 0; i < this.numberOfEnemies; i++) {
            this.enemyButtons[i].setMouseIn(false);
            if (this.enemyButtons[i].getButtonBounds()
                .contains(event.getX(), event.getY())) {
                this.enemyButtons[i].setMouseIn(true);
            } else {
                this.enemies[i].setTakingDamage(false);
            }
        }
    }
}
