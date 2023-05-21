package dalibor.crownsofbetrayal.items;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.tools.ImageReader;

/**
 * Treida zabezpecuje aby nenastal problem s null pointer chybou
 */
public class NoItem extends Item implements Sellable, Usable {
    private int price;

    /**
     * V konstruktore nastavujem napevno hodnoty do predkovho konstruktora
     * A setujem cenu stitu
     */
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

    /**
     * Nevyuzite ale nutnost implementovat
     *
     * @param player hrac na ktory sa aplikuje efekt
     */
    @Override
    public void use(Player player) {

    }
}
