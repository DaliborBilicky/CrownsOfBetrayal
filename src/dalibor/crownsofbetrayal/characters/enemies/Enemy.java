package dalibor.crownsofbetrayal.characters.enemies;

import dalibor.crownsofbetrayal.characters.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Enemy {
    private final BufferedImage imageRepresentation;
    private final int damage;
    private int health;
    private boolean takingDamage;
    private int x;
    private int y;
    private int size;
    private boolean dead;

    public Enemy(BufferedImage imageRepresentation, int maxHealth, int damage) {
        this.imageRepresentation = imageRepresentation;
        this.health = maxHealth;
        this.damage = damage;
        this.takingDamage = false;
        this.dead = false;
    }

    public void setImageLocation(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void drawEnemy(Graphics2D g2D) {
        g2D.drawImage(
            this.imageRepresentation,
            this.x - (this.size / 2),
            this.y - (this.size / 2),
            this.size,
            this.size,
            null);
        if (this.takingDamage) {
            g2D.setFont(new Font("Viner Hand ITC", Font.BOLD, 135));
            g2D.setColor(Color.RED);
            g2D.drawString(
                "HIT",
                this.x - (this.size / 2),
                this.y + (this.size / 6));
        }
    }

    public BufferedImage getImageRepresentation() {
        return this.imageRepresentation;
    }


    public int dealDamage() {
        return this.getDamage();
    }

    public int getDamage() {
        return this.damage;
    }

    public void takeDamage(int takenDamage) {
        if (this.health > 0) {
            this.health -= takenDamage;
        } else {
            this.dead = true;
        }

    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public abstract void makeSpecialAttack(Player player);

    public boolean isTakingDamage() {
        return this.takingDamage;
    }

    public void setTakingDamage(boolean takingDamage) {
        this.takingDamage = takingDamage;
    }

    public boolean isDead() {
        return this.dead;
    }
}
