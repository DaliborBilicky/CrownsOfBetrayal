package dalibor.crownsofbetrayal.items.armor;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.Sellable;
import dalibor.crownsofbetrayal.items.Storable;
import dalibor.crownsofbetrayal.items.Wearable;

public class Shield extends Item implements Wearable, Storable, Sellable {


    public Shield() {
        super(new ImageReader().getBufferedImage("res/items/shields/shield.png"));

    }


}
