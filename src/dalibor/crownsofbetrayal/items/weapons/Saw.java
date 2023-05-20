package dalibor.crownsofbetrayal.items.weapons;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import java.util.Random;

public class Saw extends Weapon {
    public Saw() {
        super(new ImageReader().getBufferedImage("res/item/weapons/saw.png"), 15, 2);
    }

    public boolean isDoubleTheDamage() {
        Random random = new Random();
        int probability = random.nextInt(1, 101);
        return 0 < probability && probability <= 50;
    }
}
