package dalibor.crownsofbetrayal.characters.enemies;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.tools.ImageReader;
import java.util.Random;

public class Thief extends Enemy {
    public Thief() {
        super(new ImageReader()
                .getBufferedImage("res/characters/thief.png"),
            100, 10);
    }


    @Override
    public void makeSpecialAttack(Player player) {
        Random random = new Random();
        int probability = random.nextInt(1, 101);
        if (0 < probability && probability <= 5) {
            player.robbed();
        }
    }
}
