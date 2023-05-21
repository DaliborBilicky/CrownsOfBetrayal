package dalibor.crownsofbetrayal.items.shields;

import dalibor.crownsofbetrayal.items.Sellable;
import dalibor.crownsofbetrayal.tools.ImageReader;

/**
 * Specialny typ stitu ktory dedi od stitu a implementuje interfacie aby sa dal
 * predat
 */
public class WeakShield extends Shield implements Sellable {
    private int price;

    /**
     * V konstruktore nastavujem napevno hodnoty do predkovho konstruktora
     * A setujem cenu stitu
     */
    public WeakShield() {
        super(new ImageReader().getBufferedImage("res/items/shields/shield.png"), 2);
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
}
