package dalibor.crownsofbetrayal.items.usableItems;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.Sellable;
import dalibor.crownsofbetrayal.items.Usable;
import java.awt.image.BufferedImage;

public class Adrenaline extends Item implements Usable, Sellable {
    private int price;

    public Adrenaline(BufferedImage image) {
        super(image);
        this.setPrice();
    }

    @Override
    public void setPrice() {
        this.price = 50;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public void use(Player player) {
        int tempDamage = player.getDamage();
        player.setDamage(tempDamage * 5);
    }
}
