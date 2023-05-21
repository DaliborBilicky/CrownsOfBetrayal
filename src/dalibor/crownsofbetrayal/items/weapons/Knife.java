package dalibor.crownsofbetrayal.items.weapons;

import dalibor.crownsofbetrayal.items.Sellable;
import dalibor.crownsofbetrayal.tools.ImageReader;

/**
 * Specialny druh zbrane ktory dedi od zbrane
 */
public class Knife extends Weapon implements Sellable {
    private int price;

    /**
     * V konstruktore nastavujem napevno hodnoty do predkovho konstruktora
     * A setujem cenu stitu
     */
    public Knife() {
        super(new ImageReader().getBufferedImage(
            "res/items/weapons/knife.png"), 2);
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
}
