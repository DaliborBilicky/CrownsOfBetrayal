package dalibor.crownsofbetrayal.items.shields;

import dalibor.crownsofbetrayal.items.Sellable;
import dalibor.crownsofbetrayal.tools.ImageReader;

public class WeakShield extends Shield implements Sellable {
    private int price;

    public WeakShield() {
        super(new ImageReader().getBufferedImage("res/items/shields/shield.png"), 2);
        this.setPrice();
    }

    @Override
    public void setPrice() {
        this.price = 10;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
