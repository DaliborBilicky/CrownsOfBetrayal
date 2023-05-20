package dalibor.crownsofbetrayal.items.shields;

import dalibor.crownsofbetrayal.tools.ImageReader;
import java.util.Random;

public class StrongShield extends Shield {

    public StrongShield() {
        super(new ImageReader().getBufferedImage("res/items/shields/strongShield.png"), 4);
    }

    public boolean isTakingAllDamage() {
        Random random = new Random();
        int probability = random.nextInt(1, 101);
        return 0 < probability && probability <= 15;
    }
}
    