package dalibor.crownsofbetrayal.items;

import dalibor.crownsofbetrayal.tools.ImageReader;

/**
 * Specialny druh itemu ktory dedi od itemu a implemenetuje Sellable aby sa dal
 * predat
 * Sluzi iba ako prijem penazi kedze ma velku hodnotu
 */
public class Diamond extends Item implements Sellable {
    private int price;

    /**
     * V konstruktore nastavujem napevno hodnoty do predkovho konstruktora
     * A setujem cenu stitu
     */
    public Diamond() {
        super(new ImageReader().getBufferedImage("res/items/diamond.png"));
        this.setPrice();
    }

    @Override
    public void setPrice() {
        this.price = 100;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
