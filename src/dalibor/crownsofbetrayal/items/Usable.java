package dalibor.crownsofbetrayal.items;

import dalibor.crownsofbetrayal.characters.Player;

/**
 * Interfacie aby sa dalo odkontrolovat ci je item pouzitelny
 */
public interface Usable {
    /**
     * @param player hrac na ktory sa aplikuje efekt
     */
    void use(Player player);
}
