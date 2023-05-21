package dalibor.crownsofbetrayal.items.usableItems;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.Sellable;
import dalibor.crownsofbetrayal.items.Usable;
import dalibor.crownsofbetrayal.tools.ImageReader;

/**
 * Pivo ktore dedi od itemu a implemetuje Usable aby sa dalo pouzit
 * a Sellable aby sa dalo predat
 */
public class Beer extends Item implements Usable, Sellable {
    private int price;

    /**
     * V konstruktore nastavujem napevno hodnoty do predkovho konstruktora
     * A setujem cenu stitu
     */
    public Beer() {
        super(new ImageReader().getBufferedImage("res/items/beer.png"));
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

    /**
     * Pivo ako alkohol zvbudzuje agresivitu preto 2x damage
     *
     * @param player hrac na ktory sa aplikuje efekt
     */
    @Override
    public void use(Player player) {
        int tempDamage = player.getDamage();
        player.setDamage(tempDamage * 2);
    }
}
