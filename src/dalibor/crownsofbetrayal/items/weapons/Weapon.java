package dalibor.crownsofbetrayal.items.weapons;

import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.Wearable;
import java.awt.image.BufferedImage;

/**
 * Abstraktna trieda zbran dedi od itemu a implementuje nositelny aby sa dala
 * nasadit
 */
public abstract class Weapon extends Item implements Wearable {
    private final int damageMultiplication;


    /**
     * @param image                obrazok zbrane
     * @param damageMultiplication nasobok ktory pridava zbran
     */
    public Weapon(BufferedImage image, int damageMultiplication) {
        super(image);
        this.damageMultiplication = damageMultiplication;
    }

    public int getDamageMultiplication() {
        return this.damageMultiplication;
    }
}
