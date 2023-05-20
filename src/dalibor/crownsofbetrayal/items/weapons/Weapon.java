package dalibor.crownsofbetrayal.items.weapons;

import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.Sellable;
import dalibor.crownsofbetrayal.items.Wearable;
import java.awt.image.BufferedImage;

public class Weapon extends Item implements Wearable, Sellable {
    private final int damageMultiplication;

    public Weapon(BufferedImage image, int goldValue, int damageMultiplication) {
        super(image, goldValue);
        this.damageMultiplication = damageMultiplication;
    }

    public int getDamageMultiplication() {
        return this.damageMultiplication;
    }
}
