package dalibor.crownsofbetrayal.items.weapons;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Bow extends Weapon {
    public Bow(BufferedImage image, int goldValue, int damageMultiplication) {
        super(new ImageReader().getBufferedImage("res/item/weapons/bow.png"), 15, 1);
    }

    public boolean isDealingMultipleHits() {
        Random random = new Random();
        int probability = random.nextInt(1, 101);
        return 0 < probability && probability <= 25;
    }
}
