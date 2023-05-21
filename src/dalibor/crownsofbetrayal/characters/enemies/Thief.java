package dalibor.crownsofbetrayal.characters.enemies;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.tools.ImageReader;
import java.util.Random;

/**
 * Zlodej typ nepriatela dedi od enemy
 */
public class Thief extends Enemy {
    /**
     * V konstruktore nastavujem napevno hodnoty do predkovho konstruktora
     */
    public Thief() {
        super(new ImageReader()
                .getBufferedImage("res/characters/thief.png"),
            "thief", 100, 5);
    }


    /**
     * Implementovana metoda z predka
     * Zlodej vie okradnut hraca o vsetko co ma (10% sanca)
     *
     * @param player hrac na ktorom sa vykona specialny utok
     */
    @Override
    public void makeSpecialAttack(Player player) {
        Random random = new Random();
        int probability = random.nextInt(1, 101);
        if (0 < probability && probability <= 10) {
            player.robbed();
        }
    }
}
