package dalibor.crownsofbetrayal.characters;

import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.NoItem;
import dalibor.crownsofbetrayal.items.shields.StrongShield;
import dalibor.crownsofbetrayal.items.weapons.Saw;
import dalibor.crownsofbetrayal.items.weapons.Sword;
import dalibor.crownsofbetrayal.items.weapons.Weapon;
import dalibor.crownsofbetrayal.main.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Player {
    private static final int INVENTORY_CAPACITY = 24;
    private static final int SUPPLIES_CAPACITY = 50;
    private static final int DAMAGE = 10;
    private static final int MAX_HEALTH = 100;
    private final Game game;
    private final ArrayList<Item> inventory;
    private int damage;
    private Item shield;
    private Item weapon;
    private boolean onMove;
    private int health;
    private int supplies;
    private int goldCoins;

    public Player(Game game) {
        this.supplies = SUPPLIES_CAPACITY;
        this.goldCoins = 10;
        this.onMove = true;
        this.health = MAX_HEALTH;
        this.shield = new NoItem();
        this.weapon = new NoItem();
        this.game = game;
        this.damage = DAMAGE;
        this.inventory = new ArrayList<>();
    }

    public void draw(Graphics2D g2D) {
        if (this.health <= 0) {
            g2D.setColor(new Color(0, 0, 0, 150));
            g2D.fillRect(0, 0, this.game.getWidth(), this.game.getHeight());
            g2D.setFont(new Font("Viner Hand ITC", Font.BOLD, 280));
            g2D.setColor(Color.BLACK);
            g2D.drawString(
                "You died!",
                (int)(this.game.getWidth() * 0.175),
                (int)(this.game.getHeight() * 0.51));
            g2D.setFont(new Font("Viner Hand ITC", Font.BOLD, 300));
            g2D.setColor(Color.RED);
            g2D.drawString(
                "You died!",
                (int)(this.game.getWidth() * 0.15),
                this.game.getHeight() / 2);
        }
    }

    public int getSupplies() {
        return this.supplies;
    }

    public void setSupplies(int supplies) {
        if (this.supplies + supplies < SUPPLIES_CAPACITY) {
            this.supplies = supplies;
        } else {
            this.supplies = SUPPLIES_CAPACITY;
        }
        System.out.println(this.supplies);
    }

    public boolean isSuppliesFull() {
        return this.supplies == SUPPLIES_CAPACITY;
    }

    public Item getItemFromInventory(int index) {
        if (!this.inventory.isEmpty() && index < this.inventory.size()) {
            return this.inventory.get(index);
        }
        return new NoItem();
    }

    public void putItemToInventory(Item item) {
        if (this.inventory.size() < INVENTORY_CAPACITY) {
            this.inventory.add(item);
        }
    }

    public void removeItemFromInventory(int i, int j) {
        if (!this.inventory.isEmpty()) {
            this.inventory.removeIf(
                item -> item.getI() == i && item.getJ() == j);
        }
    }


    public int getInventoryCapacity() {
        return INVENTORY_CAPACITY;
    }


    public int getSuppliesCapacity() {
        return SUPPLIES_CAPACITY;
    }

    public boolean isInventoryFull() {
        return this.inventory.size() == INVENTORY_CAPACITY;
    }


    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int dealDamage() {
        if (this.weapon instanceof Sword &&
            ((Sword)this.weapon).isKillingOnOneHit()) {
            return Integer.MAX_VALUE;
        } else if (this.weapon instanceof Saw &&
            ((Saw)this.weapon).isDoubleTheDamage()) {
            return this.damage *
                ((Weapon)this.weapon).getDamageMultiplication() * 2;
        }
        if (this.weapon instanceof Weapon) {
            return this.damage *
                ((Weapon)this.weapon).getDamageMultiplication();
        }
        return this.damage;
    }

    public void takeDamage(int takenDamage) {
        if (this.health >= 0) {
            if (this.shield instanceof StrongShield) {
                if (!((StrongShield)this.shield).isTakingAllDamage()) {
                    this.health -= takenDamage;
                }
            } else {
                this.health -= takenDamage;
            }
        }
    }

    public boolean isOnMove() {
        return this.onMove;
    }

    public void setOnMove(boolean onMove) {
        this.onMove = onMove;
    }

    public void resetHealth() {
        this.health = MAX_HEALTH;
    }

    public void resetDamage() {
        this.damage = DAMAGE;
    }

    public Item getShield() {
        return this.shield;
    }

    public void setShield(Item shield) {
        this.shield = shield;
    }

    public Item getWeapon() {
        return this.weapon;
    }

    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }

    public void gainGold(int numOfCoins) {
        this.goldCoins += numOfCoins;
    }

    public void looseGold(int numOfCoins) {
        this.goldCoins -= numOfCoins;
    }

    public int getGoldCoins() {
        return this.goldCoins;
    }
}
