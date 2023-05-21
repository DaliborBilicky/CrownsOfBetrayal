package dalibor.crownsofbetrayal.items.usableItems;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.Sellable;
import dalibor.crownsofbetrayal.items.Usable;
import dalibor.crownsofbetrayal.tools.ImageReader;

/**
 * Adrenaline ktory dedi od itemu a implemetuje Usable aby sa dal pouzit
 * a Sellable aby sa dal predat
 */
public class Adrenaline extends Item implements Usable, Sellable {
    private int price;

    /**
     * V konstruktore nastavujem napevno hodnoty do predkovho konstruktora
     * A setujem cenu stitu
     */
    public Adrenaline() {
        super(new ImageReader().getBufferedImage("res/items/adrenaline.png"));
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

    /**
     * Adrenaline po pouziti znasoby silu utoku do konca hry
     *
     * @param player hrac na ktory sa aplikuje efekt
     */
    @Override
    public void use(Player player) {
        int tempDamage = player.getDamage();
        player.setDamage(tempDamage * 5);
    }
}
