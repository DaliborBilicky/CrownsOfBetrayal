package dalibor.crownsofbetrayal.items.shields;

import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.Wearable;
import java.awt.image.BufferedImage;

/**
 * Abstractna trieda od ktorej dedia ostatne typy stitov a implemetuje interface
 * aby sa dal nasadit
 */
public abstract class Shield extends Item implements Wearable {
    private final int protection;

    /**
     * @param image      obrazok itemu
     * @param protection kolko je schopny stit pohltit
     */
    public Shield(BufferedImage image, int protection) {
        super(image);
        this.protection = protection;
    }

    public int getProtection() {
        return this.protection;
    }
}
