package dalibor.crownsofbetrayal.items.weapons;

import dalibor.crownsofbetrayal.tools.ImageReader;
import java.util.Random;

public class Bow extends Weapon {
    public Bow() {
        super(new ImageReader().getBufferedImage("res/items/weapons/bow.png"), 1);
    }

    public boolean isDealingMultipleHits() {
        Random random = new Random();
        int probability = random.nextInt(1, 101);
        return 0 < probability && probability <= 50;
    }
}
