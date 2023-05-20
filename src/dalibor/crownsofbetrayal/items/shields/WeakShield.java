package dalibor.crownsofbetrayal.items.shields;

import dalibor.crownsofbetrayal.graphics.ImageReader;

public class WeakShield extends Shield {
    public WeakShield() {
        super(new ImageReader().getBufferedImage("res/items/shields/shield.png"), 10, 2);
    }
}
