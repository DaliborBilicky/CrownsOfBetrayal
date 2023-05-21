package dalibor.crownsofbetrayal.characters.enemies;

import dalibor.crownsofbetrayal.characters.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


/**
 * Trieda sluzi ako predok pre specificky typ nepriatela.
 */
public abstract class Enemy {
    private final BufferedImage imageRepresentation;
    private final int damage;
    private int health;
    private boolean takingDamage;
    private int x;
    private int y;
    private int size;

    /**
     * @param imageRepresentation obrazok nepriatela
     * @param maxHealth           max zivoty nepriatela
     * @param damage              body utoku nepriatela
     */
    public Enemy(BufferedImage imageRepresentation, int maxHealth, int damage) {
        this.imageRepresentation = imageRepresentation;
        this.health = maxHealth;
        this.damage = damage;
        this.takingDamage = false;
    }

    /**
     * Vyhradene paramentre do metody aby sa nastavili iba vtedy ked je to potrebene
     *
     * @param x    x pozicia
     * @param y    y pozicia
     * @param size velkost obrazka
     */
    public void setImageLocation(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    /**
     * Metoda vykresli obrazok nepriatela a jeho zivoty
     *
     * @param g2D java trieda na kreslenie objektov
     */
    public void drawEnemy(Graphics2D g2D) {
        g2D.drawImage(
            this.imageRepresentation,
            this.x - (this.size / 2),
            this.y - (this.size / 2),
            this.size,
            this.size,
            null);
        g2D.setFont(new Font("Viner Hand ITC", Font.BOLD, 115));
        g2D.setColor(Color.BLACK);
        g2D.drawString(
            String.valueOf(this.health),
            (int)(this.x - (this.size * 0.45)),
            (int)(this.y + (this.size * 0.7)));
        g2D.setFont(new Font("Viner Hand ITC", Font.BOLD, 135));
        g2D.setColor(Color.RED);
        g2D.drawString(
            String.valueOf(this.health),
            this.x - (this.size / 2),
            (int)(this.y + (this.size * 0.7)));
        if (this.takingDamage) {
            g2D.drawString(
                "HIT",
                this.x - (this.size / 2),
                this.y + (this.size / 6));
        }
    }

    public int getDamage() {
        return this.damage;
    }

    /**
     * Odcita parameter od zivotov iba ak zivoty su v kladnych hodnotach
     *
     * @param takenDamage body utoku hraca na nepriatela
     */
    public void takeDamage(int takenDamage) {
        if (this.health >= 0) {
            this.health -= takenDamage;
        }
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @param player hrac na ktorom sa vykona specialny utok
     */
    public abstract void makeSpecialAttack(Player player);


    public void setTakingDamage(boolean takingDamage) {
        this.takingDamage = takingDamage;
    }
}
