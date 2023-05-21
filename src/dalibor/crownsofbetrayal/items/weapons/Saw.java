package dalibor.crownsofbetrayal.items.weapons;

import dalibor.crownsofbetrayal.tools.ImageReader;
import java.util.Random;

/**
 * Specialny druh zbrane ktory dedi od zbrane
 */
public class Saw extends Weapon {
    /**
     * V konstruktore nastavujem napevno hodnoty do predkovho konstruktora
     */
    public Saw() {
        super(new ImageReader().getBufferedImage(
            "res/items/weapons/saw.png"), 2);
    }

    /**
     * @return sanca ci zbran da dvojnasobny utok (50%)
     */
    public boolean isDoubleTheDamage() {
        Random random = new Random();
        int probability = random.nextInt(1, 101);
        return 0 < probability && probability <= 50;
    }
}
