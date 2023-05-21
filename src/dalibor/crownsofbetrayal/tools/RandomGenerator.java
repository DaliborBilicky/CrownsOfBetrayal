package dalibor.crownsofbetrayal.tools;

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
import java.util.Random;

public class RandomGenerator {
    private final Random random;

    public RandomGenerator() {
        this.random = new Random();
    }

    public int getNumOfItems() {
        return this.random.nextInt(1, 4);
    }

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
}
