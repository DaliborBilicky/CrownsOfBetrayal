package dalibor.crownsofbetrayal.characters.enemies;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.tools.ImageReader;
import java.util.Random;

/**
 * Strasiak typ nepriatela dedi od enemy
 */
public class Scarecrow extends Enemy {
    /**
     * V konstruktore nastavujem napevno hodnoty do predkovho konstruktora
     */
    public Scarecrow() {
        super(new ImageReader()
                .getBufferedImage("res/characters/scarecrow.png"),
            200, 10);
    }


    /**
     * Implementovana metoda z predka
     * Strasiak vie dat dvojity utok (15% sanca)
     *
     * @param player hrac na ktorom sa vykona specialny utok
     */
    @Override
    public void makeSpecialAttack(Player player) {
        Random random = new Random();
        int probability = random.nextInt(1, 101);
        if (0 < probability && probability <= 15) {
            player.takeDamage(this.getDamage());
        }
    }
}
