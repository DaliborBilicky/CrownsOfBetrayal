package dalibor.crownsofbetrayal.tools;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.characters.enemies.Enemy;
import dalibor.crownsofbetrayal.characters.enemies.Ghost;
import dalibor.crownsofbetrayal.characters.enemies.Jellyfish;
import dalibor.crownsofbetrayal.characters.enemies.Scarecrow;
import dalibor.crownsofbetrayal.characters.enemies.Snowman;
import dalibor.crownsofbetrayal.characters.enemies.Thief;
import dalibor.crownsofbetrayal.items.Diamond;
import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.shields.StrongShield;
import dalibor.crownsofbetrayal.items.shields.WeakShield;
import dalibor.crownsofbetrayal.items.usableItems.Adrenaline;
import dalibor.crownsofbetrayal.items.usableItems.Apple;
import dalibor.crownsofbetrayal.items.usableItems.Beer;
import dalibor.crownsofbetrayal.items.usableItems.Heal;
import dalibor.crownsofbetrayal.items.weapons.Bow;
import dalibor.crownsofbetrayal.items.weapons.Knife;
import dalibor.crownsofbetrayal.items.weapons.Saw;
import dalibor.crownsofbetrayal.items.weapons.Sword;
import dalibor.crownsofbetrayal.quests.DungeonVisitingQuest;
import dalibor.crownsofbetrayal.quests.ItemCollectionQuest;
import dalibor.crownsofbetrayal.quests.KillingQuest;
import dalibor.crownsofbetrayal.quests.Quest;
import java.util.Random;

/**
 * Vyhradena trieda pre lepsiu manipulaciu na generovanie nahodnych itemov
 */
public class RandomGenerator {
    private final Random random;

    /**
     * Incilizacia triedy Random
     */
    public RandomGenerator() {
        this.random = new Random();
    }

    /**
     * @return nahdny pocet itemov
     */
    public int getNumOfItems() {
        return this.random.nextInt(1, 4);
    }

    /**
     * @return nahodny item
     */
    public Item getItem() {
        int probability = this.random.nextInt(1, 155);
        if (0 <= probability && probability < 100) {
            return this.getItemForShop();
        } else if (100 <= probability && probability < 114) {
            return new StrongShield();
        } else if (114 <= probability && probability < 128) {
            return new Saw();
        } else if (128 <= probability && probability < 142) {
            return new Sword();
        } else {
            return new Bow();
        }
    }

    /**
     * @return nahodny Sellable item
     */
    public Item getItemForShop() {
        int probability = this.random.nextInt(1, 101);
        if (0 <= probability && probability < 14) {
            return new Diamond();
        } else if (14 <= probability && probability < 28) {
            return new Knife();
        } else if (28 <= probability && probability < 42) {
            return new WeakShield();
        } else if (42 <= probability && probability < 56) {
            return new Heal();
        } else if (56 <= probability && probability < 70) {
            return new Adrenaline();
        } else if (70 <= probability && probability < 84) {
            return new Beer();
        } else {
            return new Apple();
        }
    }

    /**
     * @return nahodny nepriatel
     */
    public Enemy getEnemy() {
        int probability = this.random.nextInt(1, 101);
        if (0 <= probability && probability < 20) {
            return new Ghost();
        } else if (20 <= probability && probability < 40) {
            return new Snowman();
        } else if (40 <= probability && probability < 60) {
            return new Scarecrow();
        } else if (60 <= probability && probability < 80) {
            return new Thief();
        } else {
            return new Jellyfish();
        }
    }


    /**
     * @param player hrac
     * @return nahodny quest
     */
    public Quest getQuest(Player player) {
        int probability = this.random.nextInt(1, 101);
        if (0 <= probability && probability < 34) {
            return new KillingQuest();
        } else if (34 <= probability && probability < 68) {
            return new ItemCollectionQuest();
        } else {
            return new DungeonVisitingQuest();
        }
    }
}
