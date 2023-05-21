package dalibor.crownsofbetrayal.characters.enemies;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.tools.ImageReader;
import java.util.Random;

public class Snowman extends Enemy {
    public Snowman() {
        super(new ImageReader()
                .getBufferedImage("res/characters/snowman.png"),
            50, 7);
    }


    @Override
    public void makeSpecialAttack(Player player) {
        Random random = new Random();
        int probability = random.nextInt(1, 101);
        if (0 < probability && probability <= 5) {
            player.setStunned(true);
        }
    }
}
