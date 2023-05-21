package dalibor.crownsofbetrayal.items.usableItems;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.Sellable;
import dalibor.crownsofbetrayal.items.Usable;
import dalibor.crownsofbetrayal.tools.ImageReader;

/**
 * Heal ktory dedi od itemu a implemetuje Usable aby sa dal pouzit
 * a Sellable aby sa dal predat
 */
public class Heal extends Item implements Sellable, Usable {
    private int price;

    /**
     * V konstruktore nastavujem napevno hodnoty do predkovho konstruktora
     * A setujem cenu stitu
     */
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

    /**
     * Heal pridava 30 zivota
     *
     * @param player hrac na ktory sa aplikuje efekt
     */
    @Override
    public void use(Player player) {
        int tempHealth = player.getHealth();
        player.setHealth(tempHealth + 30);
    }
}
