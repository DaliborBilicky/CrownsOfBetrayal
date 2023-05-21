package dalibor.crownsofbetrayal.characters.enemies;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.tools.ImageReader;
import java.util.Random;

/**
 * Snehuliak typ nepriatela dedi od enemy
 */
public class Snowman extends Enemy {
    /**
     * V konstruktore nastavujem napevno hodnoty do predkovho konstruktora
     */
    public Snowman() {
        super(new ImageReader()
                .getBufferedImage("res/characters/snowman.png"),
            50, 7);
    }

    /**
     * Implementovana metoda z predka
     * Snehuliak vie hracovy prekazit pouzivanie itemov do konca hry (5% sanca)
     *
     * @param player hrac na ktorom sa vykona specialny utok
     */
    @Override
    public void makeSpecialAttack(Player player) {
        Random random = new Random();
        int probability = random.nextInt(1, 101);
        if (0 < probability && probability <= 5) {
            player.setStunned(true);
        }
    }
}
