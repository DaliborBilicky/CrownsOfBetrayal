package dalibor.crownsofbetrayal.characters.enemies;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.tools.ImageReader;
import java.util.Random;

public class Scarecrow extends Enemy {

    public Scarecrow() {
        super(new ImageReader()
                .getBufferedImage("res/characters/scarecrow.png"),
            200, 20);
    }


    @Override
    public void makeSpecialAttack(Player player) {
        Random random = new Random();
        int probability = random.nextInt(1, 101);
        if (0 < probability && probability <= 15) {
            player.takeDamage(this.dealDamage());
        }
    }
}
