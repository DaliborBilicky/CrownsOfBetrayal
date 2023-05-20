package dalibor.crownsofbetrayal.items.weapons;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.Sellable;
import dalibor.crownsofbetrayal.items.Storable;
import dalibor.crownsofbetrayal.items.Usable;
import dalibor.crownsofbetrayal.items.Wearable;

public class Weapon extends Item implements Usable, Storable, Sellable, Wearable {

    public Weapon() {
        super(new ImageReader().getBufferedImage("res/items/weapons/knife.png"));
 
    }
}
