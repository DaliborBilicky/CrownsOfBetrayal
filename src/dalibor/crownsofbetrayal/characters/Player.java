package dalibor.crownsofbetrayal.characters;

import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.NoItem;
import dalibor.crownsofbetrayal.items.shields.Shield;
import dalibor.crownsofbetrayal.items.shields.StrongShield;
import dalibor.crownsofbetrayal.items.usableItems.Apple;
import dalibor.crownsofbetrayal.items.weapons.Saw;
import dalibor.crownsofbetrayal.items.weapons.Sword;
import dalibor.crownsofbetrayal.items.weapons.Weapon;
import dalibor.crownsofbetrayal.main.Game;
import dalibor.crownsofbetrayal.quests.ItemCollectionQuest;
import dalibor.crownsofbetrayal.quests.Quest;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;


/**
 * Trieda hrac obsahuje invnetar a hodnoty ako utok, zivoty, atd.
 */
public class Player {
    /**
     * Konstanty
     * 1. kapacita inventara aby nebol nekonecne velky
     * 2. kapacita zasob aby neboli nekonecne velke
     * 3. velkost zakladneho utoku
     * 4. velkost zakladneho zivota
     */
    private static final int INVENTORY_CAPACITY = 24;
    private static final int SUPPLIES_CAPACITY = 50;
    private static final int DAMAGE = 10;
    private static final int MAX_HEALTH = 100;
    private final Game game;
    private final ArrayList<Item> inventory;
    private final Quest[] questBook;
    private int damage;
    private Item shield;
    private Item weapon;
    private boolean onMove;
    private int health;
    private int supplies;
    private int goldCoins;
    private boolean stunned;

    /**
     * V konstruktore prebieha zakladna inicializacia
     *
     * @param game hlavna trieda hra aby som vedel pracovat s ostatnymi obejektami
     */
    public Player(Game game) {
        this.supplies = SUPPLIES_CAPACITY;
        this.goldCoins = 20;
        this.onMove = true;
        this.health = MAX_HEALTH;
        this.shield = new NoItem();
        this.weapon = new NoItem();
        this.game = game;
        this.damage = DAMAGE;
        this.inventory = new ArrayList<>();
        this.inventory.add(new Apple());
        this.questBook = new Quest[10];
        this.setQuests();
    }

    /**
     * Nastavi indexy v poli na quest
     */
    public void setQuests() {
        for (int i = 0; i < this.questBook.length; i++) {
            this.questBook[i] = new Quest();
        }
    }

    /**
     * Pridava questy do pola
     *
     * @param quest quest ktory sa zapise
     */
    public void setNewQuest(Quest quest) {
        for (int i = 0; i < this.questBook.length; i++) {
            if (this.questBook[i].getDescription().equals("No quest")) {
                this.questBook[i] = quest;
                break;
            }
        }
    }

    /**
     * odstrani spravney quest
     */
    public void removeDoneQuests() {
        for (int i = 0; i < this.questBook.length; i++) {
            if (this.questBook[i].isDone()) {
                this.gainGold(this.questBook[i].getReward());
                this.questBook[i] = new Quest();
            }
        }
    }

    /**
     * @param numOfCoins pocet minci ktore sa pripocitaju
     */
    public void gainGold(int numOfCoins) {
        this.goldCoins += numOfCoins;
    }

    /**
     * Odstrani quest na danom indexe
     *
     * @param index odstranovany quest
     */
    public void removeQuest(int index) {
        this.questBook[index] = new Quest();
    }


    /**
     * @param index questu ktory chceme vratit
     * @return vybrany quest
     */
    public Quest getQuest(int index) {
        return this.questBook[index];
    }

    /**
     * Metoda vykresli game over screen ak hrac zomrie (ma zaporne zivoty)
     *
     * @param g2D java trieda na kreslenie
     */
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

    /**
     * metoda zabezpecuje aby sa dalo vzdy zasoby nastavit na plnu hodnotu
     * aj ked by sucet nevychadzal
     *
     * @param supplies nastavovane zasoby
     */
    public void setSupplies(int supplies) {
        this.supplies = Math.min(supplies, SUPPLIES_CAPACITY);
    }

    /**
     * @return ci su zasoby plne
     */
    public boolean isSuppliesFull() {
        return this.supplies == SUPPLIES_CAPACITY;
    }

    /**
     * V pripade ze index nesplni podmienku vrati sa prazdny item aby nebol
     * problem s null
     *
     * @param index pozicia itemu v inventary
     * @return item z inventara
     */
    public Item getItemFromInventory(int index) {
        if (!this.inventory.isEmpty() && index < this.inventory.size()) {
            return this.inventory.get(index);
        }
        return new NoItem();
    }

    /**
     * Metoda zapezpecuje aj aby sa neprekrocila kapacita
     *
     * @param item ukladany item do inventara
     */
    public void putItemToInventory(Item item) {
        if (this.inventory.size() < INVENTORY_CAPACITY) {
            this.inventory.add(item);
            for (Quest quest : this.questBook) {
                if (quest instanceof ItemCollectionQuest) {
                    ((ItemCollectionQuest)quest).collectedItem();
                }
            }
        }
    }

    /**
     * Ak nie je inventar prazdny vyhodi sa predmet z neho
     *
     * @param item vyhadzovany item z inventara
     */
    public void removeItemFromInventory(Item item) {
        if (!this.inventory.isEmpty()) {
            this.inventory.remove(item);
        }
    }

    public int getInventoryCapacity() {
        return INVENTORY_CAPACITY;
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

    /**
     * Kontroluje sa ci je zbran urciteho typu ak hej vykona sa
     * specialna metoda zbrane
     *
     * @return body utoku po uprave podla toho aku zbran ma hrac nasadenu
     */
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

    /**
     * Ak ma hrac nasadeny lepsi stit tak sa vykona jeho metoda
     *
     * @param takenDamage body utoku ktore dostane hrac
     */
    public void takeDamage(int takenDamage) {
        if (this.health >= 0) {
            if (this.shield instanceof Shield) {
                if (this.shield instanceof StrongShield) {
                    if (!((StrongShield)this.shield).isTakingAllDamage()) {
                        this.health -= takenDamage /
                            ((Shield)this.shield).getProtection();
                    }
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

    /**
     * resetne zdravie
     */
    public void resetHealth() {
        this.health = MAX_HEALTH;
    }

    /**
     * resetne body utoku
     */
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

    /**
     * @param numOfCoins pocet minci ktore sa odcitaju
     */
    public void looseGold(int numOfCoins) {
        this.goldCoins -= numOfCoins;
    }

    public int getGoldCoins() {
        return this.goldCoins;
    }

    /**
     * @return ci je inventar prazdny
     */
    public boolean isInventoryEmpty() {
        return this.inventory.isEmpty();
    }

    /**
     * Hrac bol okradnuty takze sa mu zmaze inventar
     */
    public void robbed() {
        this.inventory.clear();
    }

    public boolean isStunned() {
        return this.stunned;
    }

    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }
}
