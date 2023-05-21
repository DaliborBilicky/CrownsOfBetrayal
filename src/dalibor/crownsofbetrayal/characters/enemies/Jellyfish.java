package dalibor.crownsofbetrayal.characters.enemies;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.tools.ImageReader;
import java.util.Random;

public class Jellyfish extends Enemy {
    public Jellyfish() {
        super(new ImageReader().getBufferedImage("res/characters/jellyfish.png"), 50, 15);
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
