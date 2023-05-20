package dalibor.crownsofbetrayal.items.weapons;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.items.Sellable;

public class Knife extends Weapon implements Sellable {
    private int price;

    public Knife() {
        super(new ImageReader().getBufferedImage("res/items/weapons/knife.png"), 1);
        this.setPrice();
    }

    @Override
    public void setPrice() {
        this.price = 5;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}