package dalibor.crownsofbetrayal.items;

/**
 * Interfacie aby sa dalo odkontrolovat ci je item predajny
 */
public interface Sellable {
    /**
     * Nastavuje cenu
     */
    void setPrice();

    /**
     * @return vracia cenu
     */
    int getPrice();
}
