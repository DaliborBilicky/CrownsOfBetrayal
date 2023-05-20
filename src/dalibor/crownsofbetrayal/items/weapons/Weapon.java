package dalibor.crownsofbetrayal.items.weapons;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.Sellable;
import dalibor.crownsofbetrayal.items.Wearable;

public class Weapon extends Item implements Wearable, Sellable {

    public Weapon() {
        super(new ImageReader().getBufferedImage("res/items/weapons/knife.png"), 10);

    }
}
