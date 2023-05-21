package dalibor.crownsofbetrayal.characters.enemies;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.tools.ImageReader;
import java.util.Random;

/**
 * Duch typ nepriatela dedi od enemy
 */
public class Ghost extends Enemy {
    /**
     * V konstruktore nastavujem napevno hodnoty do predkovho konstruktora
     */
    public Ghost() {
        super(new ImageReader().getBufferedImage("res/characters/ghost.png"), 200, 2);
    }


    /**
     * Implementovana metoda z predka
     * Duch si vie pridat zivoty (25% sanca)
     *
     * @param player hrac na ktorom sa vykona specialny utok
     */
    @Override
    public void makeSpecialAttack(Player player) {
        Random random = new Random();
        int probability = random.nextInt(1, 101);
        if (0 < probability && probability <= 25) {
            int health = this.getHealth();
            this.setHealth(health + 30);
        }
    }
}
