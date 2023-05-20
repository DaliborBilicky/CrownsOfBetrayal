package dalibor.crownsofbetrayal.items.weapons;

import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.Wearable;
import java.awt.image.BufferedImage;

public abstract class Weapon extends Item implements Wearable {
    private final int damageMultiplication;

    public Weapon(BufferedImage image, int damageMultiplication) {
        super(image);
        this.damageMultiplication = damageMultiplication;
    }

    public int getDamageMultiplication() {
        return this.damageMultiplication;
    }
}
