package dalibor.crownsofbetrayal.items;

import dalibor.crownsofbetrayal.graphics.ImageReader;

public class NoItem extends Item implements Sellable, Usable {
    public NoItem() {
        super(new ImageReader().getBufferedImage("res/items/noItem.png"), 0);
    }
}
