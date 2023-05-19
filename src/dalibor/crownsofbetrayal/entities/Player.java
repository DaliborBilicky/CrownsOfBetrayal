package dalibor.crownsofbetrayal.entities;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.items.Item;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {
    private static final int INVENTORY_CAPACITY = 24;
    private static final int PUB_INVENTORY_CAPACITY = 20;
    private static final int CRAFTING_INVENTORY_CAPACITY = 12;
    private static final int SUPPLIES_CAPACITY = 50;
    private final BufferedImage imageRepresentation;
    private final ArrayList<Item> inventory;
    private final ArrayList<Item> pubInventory;
    private final ArrayList<Item> craftingInventory;
    private int supplies;

    public Player() {
        this.supplies = SUPPLIES_CAPACITY;
        this.imageRepresentation = new ImageReader()
            .getBufferedImage("res/characters/player.png");
        this.inventory = new ArrayList<>();
        this.pubInventory = new ArrayList<>();
        this.craftingInventory = new ArrayList<>();
    }

    public BufferedImage getImageRepresentation() {
        return this.imageRepresentation;
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

    public Iterable<Item> getInventory() {
        return this.inventory;
    }

    public Iterable<Item> getPubInventory() {
        return this.pubInventory;
    }

    public Iterable<Item> getCraftingInventory() {
        return this.craftingInventory;
    }

    public Item getItemFromInventory(int index) {
        return this.inventory.get(index);
    }

    public void putItemInInventory(Item item) throws CapacityOverflowException {
        if (this.inventory.size() < INVENTORY_CAPACITY) {
            this.inventory.add(item);
        } else {
            throw new CapacityOverflowException();
        }
    }

    public void putItemInPubInventory(Item item) throws CapacityOverflowException {
        if (this.pubInventory.size() < PUB_INVENTORY_CAPACITY) {
            this.pubInventory.add(item);
        } else {
            throw new CapacityOverflowException();
        }
    }

    public void putItemInCraftingInventory(Item item) throws CapacityOverflowException {
        if (this.pubInventory.size() < CRAFTING_INVENTORY_CAPACITY) {
            this.craftingInventory.add(item);
        } else {
            throw new CapacityOverflowException();
        }
    }

    public int getInventoryCapacity() {
        return INVENTORY_CAPACITY;
    }

    public int getPubInventoryCapacity() {
        return PUB_INVENTORY_CAPACITY;
    }

    public int getCraftingInventoryCapacity() {
        return CRAFTING_INVENTORY_CAPACITY;
    }

    public int getSuppliesCapacity() {
        return SUPPLIES_CAPACITY;
    }

    public boolean isInventoryFull() {
        return this.inventory.size() == INVENTORY_CAPACITY;
    }

    public boolean isPubInventoryFull() {
        return this.pubInventory.size() == PUB_INVENTORY_CAPACITY;
    }

    public boolean isCraftingInventoryFull() {
        return this.craftingInventory.size() == CRAFTING_INVENTORY_CAPACITY;
    }
}
