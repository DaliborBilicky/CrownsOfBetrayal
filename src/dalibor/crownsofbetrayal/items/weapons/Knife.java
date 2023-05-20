package dalibor.crownsofbetrayal.items.weapons;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import java.awt.image.BufferedImage;

public class Knife extends Weapon {
    public Knife(BufferedImage image, int goldValue, int damageMultiplication) {
        super(new ImageReader().getBufferedImage("res/item/weapons/knife.png"), 5, 1);
    }
}
