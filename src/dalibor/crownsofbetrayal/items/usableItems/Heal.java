package dalibor.crownsofbetrayal.items.usableItems;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.Sellable;
import dalibor.crownsofbetrayal.items.Usable;

public class Heal extends Item implements Sellable, Usable {
    private int price;

    public Heal() {
        super(new ImageReader().getBufferedImage("res/items/heal.png"));
        this.setPrice();
    }

    @Override
    public void setPrice() {
        this.price = 30;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public void use(Player player) {
        int tempHealth = player.getHealth();
        player.setHealth(tempHealth + 30);
    }
}
