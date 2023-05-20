package dalibor.crownsofbetrayal.items.shields;

import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.Wearable;
import java.awt.image.BufferedImage;

public abstract class Shield extends Item implements Wearable {
    private final int protection;

    public Shield(BufferedImage image, int protection) {
        super(image);
        this.protection = protection;
    }

    public int getProtection() {
        return this.protection;
    }
}
