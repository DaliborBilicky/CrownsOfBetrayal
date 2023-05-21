package dalibor.crownsofbetrayal.items.weapons;

import dalibor.crownsofbetrayal.tools.ImageReader;
import java.util.Random;

/**
 * Specialny druh zbrane ktory dedi od zbrane
 */
public class Bow extends Weapon {
    /**
     * V konstruktore nastavujem napevno hodnoty do predkovho konstruktora
     */
    public Bow() {
        super(new ImageReader().getBufferedImage(
            "res/items/weapons/bow.png"), 1);
    }

    /**
     * @return ci hrac trafi vsetkych nepriatelov naraz (75% sanca)
     */
    public boolean isDealingMultipleHits() {
        Random random = new Random();
        int probability = random.nextInt(1, 101);
        return 0 < probability && probability <= 75;
    }
}
