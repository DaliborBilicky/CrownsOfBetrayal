package dalibor.crownsofbetrayal.items.weapons;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import java.util.Random;

public class Sword extends Weapon {
    public Sword() {
        super(new ImageReader().getBufferedImage("res/item/weapons/sword.png"), 20, 4);
    }

    public boolean isKillingOnOneHit() {
        Random random = new Random();
        int probability = random.nextInt(1, 101);
        return 0 < probability && probability <= 15;
    }
}
