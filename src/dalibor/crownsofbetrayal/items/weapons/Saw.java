package dalibor.crownsofbetrayal.items.weapons;

import dalibor.crownsofbetrayal.tools.ImageReader;
import java.util.Random;

public class Saw extends Weapon {
    public Saw() {
        super(new ImageReader().getBufferedImage("res/items/weapons/saw.png"), 2);
    }

    public boolean isDoubleTheDamage() {
        Random random = new Random();
        int probability = random.nextInt(1, 101);
        return 0 < probability && probability <= 50;
    }
}
