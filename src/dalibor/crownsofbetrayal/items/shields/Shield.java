package dalibor.crownsofbetrayal.items.shields;

import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.Sellable;
import dalibor.crownsofbetrayal.items.Wearable;
import java.awt.image.BufferedImage;

public class Shield extends Item implements Wearable, Sellable {
    private final int protection;

    public Shield(BufferedImage image, int goldValue, int protection) {
        super(image, goldValue);
        this.protection = protection;
    }

    public int getProtection() {
        return this.protection;
    }
}
