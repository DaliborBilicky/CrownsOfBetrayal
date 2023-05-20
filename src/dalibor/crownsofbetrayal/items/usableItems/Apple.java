package dalibor.crownsofbetrayal.items.usableItems;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.Sellable;
import dalibor.crownsofbetrayal.items.Usable;

public class Apple extends Item implements Usable, Sellable {
    private int price;

    public Apple() {
        super(new ImageReader().getBufferedImage("res/items/apple.png"));
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

    @Override
    public void use(Player player) {
        int tempHealth = player.getHealth();
        player.setHealth(tempHealth + 10);
    }
}
