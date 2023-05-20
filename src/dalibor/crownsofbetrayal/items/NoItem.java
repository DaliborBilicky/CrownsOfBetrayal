package dalibor.crownsofbetrayal.items;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.tools.ImageReader;

public class NoItem extends Item implements Sellable, Usable {
    private int price;

    public NoItem() {
        super(new ImageReader().getBufferedImage("res/items/noItem.png"));
        this.setPrice();
    }

    @Override
    public void setPrice() {
        this.price = 0;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public void use(Player player) {

    }
}
