package dalibor.crownsofbetrayal.items.shields;

import dalibor.crownsofbetrayal.tools.ImageReader;
import java.util.Random;

/**
 * Specialny typ stitu ktory dedi od stitu
 */
public class StrongShield extends Shield {

    /**
     * V konstruktore nastavujem napevno hodnoty do predkovho konstruktora
     */
    public StrongShield() {
        super(new ImageReader().getBufferedImage("res/items/shields/strongShield.png"), 4);
    }

    /**
     * Tento stit je schopny pohltit vsetok utok nepriatela (15% sanca)
     *
     * @return ci stit pohltil vsetok utok
     */
    public boolean isTakingAllDamage() {
        Random random = new Random();
        int probability = random.nextInt(1, 101);
        return 0 < probability && probability <= 15;
    }
}
    