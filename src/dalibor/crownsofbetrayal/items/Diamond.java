package dalibor.crownsofbetrayal.items;

import dalibor.crownsofbetrayal.graphics.ImageReader;

public class Diamond extends Item implements Sellable {

    private int price;

    public Diamond() {
        super(new ImageReader().getBufferedImage("res/items/diamond.png"));
    }

    @Override
    public void setPrice() {
        this.price = 100;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
