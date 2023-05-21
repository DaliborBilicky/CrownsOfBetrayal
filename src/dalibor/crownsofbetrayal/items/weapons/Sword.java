package dalibor.crownsofbetrayal.items.weapons;

import dalibor.crownsofbetrayal.tools.ImageReader;
import java.util.Random;

/**
 * Specialny druh zbrane ktory dedi od zbrane
 */
public class Sword extends Weapon {
    /**
     * V konstruktore nastavujem napevno hodnoty do predkovho konstruktora
     */
    public Sword() {
        super(new ImageReader().getBufferedImage(
            "res/items/weapons/sword.png"), 4);
    }

    /**
     * @return sanca ci zbran zabije nepriatela na jednu ranu (15%)
     */
    public boolean isKillingOnOneHit() {
        Random random = new Random();
        int probability = random.nextInt(1, 101);
        return 0 < probability && probability <= 15;
    }
}
