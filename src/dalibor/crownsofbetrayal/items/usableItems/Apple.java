package dalibor.crownsofbetrayal.items.usableItems;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.items.Item;
import dalibor.crownsofbetrayal.items.Sellable;
import dalibor.crownsofbetrayal.items.Usable;
import dalibor.crownsofbetrayal.tools.ImageReader;

/**
 * Jablko ktore dedi od itemu a implemetuje Usable aby sa dalo pouzit
 * a Sellable aby sa dalo predat
 */
public class Apple extends Item implements Usable, Sellable {
    private int price;

    /**
     * V konstruktore nastavujem napevno hodnoty do predkovho konstruktora
     * A setujem cenu stitu
     */
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

    /**
     * Jablku prida hracovy 10 zivota
     *
     * @param player hrac na ktory sa aplikuje efekt
     */
    @Override
    public void use(Player player) {
        int tempHealth = player.getHealth();
        player.setHealth(tempHealth + 10);
    }
}
