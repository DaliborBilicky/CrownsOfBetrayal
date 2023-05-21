package dalibor.crownsofbetrayal.characters.enemies;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.tools.ImageReader;
import java.util.Random;

public class Ghost extends Enemy {
    public Ghost() {
        super(new ImageReader().getBufferedImage("res/characters/ghost.png"), 200, 2);
    }


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
