package dalibor.crownsofbetrayal.characters;

import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.Sellable;
import dalibor.crownsofbetrayal.items.Storable;
import dalibor.crownsofbetrayal.items.Usable;
import dalibor.crownsofbetrayal.items.UsableForCrafting;
import dalibor.crownsofbetrayal.items.armor.Shield;
import dalibor.crownsofbetrayal.items.weapons.Weapon;
import dalibor.crownsofbetrayal.main.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Player {
    private static final int INVENTORY_CAPACITY = 24;
    private static final int SUPPLIES_CAPACITY = 50;
    private final Game game;
    private final ArrayList<Item> inventory;
    private final int damage;
    private final int maxHealth;
    private Item shield;
    private Item weapon;
    private boolean onMove;
    private int health;
    private int supplies;

    public Player(Game game) {
        this.supplies = SUPPLIES_CAPACITY;
        this.maxHealth = 100;
        this.onMove = true;
        this.health = this.maxHealth;
        this.shield = new Item();
        this.weapon = new Item();
        this.game = game;
        this.damage = 10;
        this.inventory = new ArrayList<>();
        this.inventory.add(new Shield());
        this.inventory.add(new Weapon());
        this.inventory.add(new Shield());
        this.inventory.add(new Weapon());
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

    public void setSupplies(int supplies) throws CapacityOverflowException {
        if (this.supplies != SUPPLIES_CAPACITY) {
            this.supplies = supplies;
        } else {
            throw new CapacityOverflowException();
        }
    }

    public boolean isSuppliesFull() {
        return this.supplies == SUPPLIES_CAPACITY;
    }

    public Item getItemFromInventory(int index) {
        if (!this.inventory.isEmpty() && index < this.inventory.size()) {
            switch (this.game.getCurrentState().getState()) {
                case INVENTORY, EXIT, GAME_MENU, MENU, WORLD_MAP, QUESTS -> {
                    return this.inventory.get(index);
                }
                case PUB -> {
                    if (this.inventory.get(index) instanceof Storable) {
                        return this.inventory.get(index);
                    }
                }
                case SHOP -> {
                    if (this.inventory.get(index) instanceof Sellable) {
                        return this.inventory.get(index);
                    }
                }
                case CRAFTING -> {
                    if (this.inventory.get(index) instanceof UsableForCrafting) {
                        return this.inventory.get(index);
                    }
                }
                case DUNGEON -> {
                    if (this.inventory.get(index) instanceof Usable) {
                        return this.inventory.get(index);
                    }
                }
            }
        }
        return new Item();
    }

    public void putItemInInventory(Item item) throws CapacityOverflowException {
        if (this.inventory.size() < INVENTORY_CAPACITY) {
            this.inventory.add(item);
        } else {
            throw new CapacityOverflowException();
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

    public int getDamage() {
        return this.damage;
    }

    public int dealDamage() {
        return this.damage;
    }

    public void takeDamage(int takenDamage) {
        if (this.health >= 0) {
            this.health -= takenDamage;
        }
    }

    public boolean isOnMove() {
        return this.onMove;
    }

    public void setOnMove(boolean onMove) {
        this.onMove = onMove;
    }

    public void resetHealth() {
        this.health = this.maxHealth;
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
}
