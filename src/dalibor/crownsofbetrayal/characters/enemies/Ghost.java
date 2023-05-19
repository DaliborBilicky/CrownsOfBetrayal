package dalibor.crownsofbetrayal.characters.enemies;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.graphics.ImageReader;

public class Ghost extends Enemy {
    public Ghost() {
        super(new ImageReader().getBufferedImage("res/characters/ghost.png"), 300, 2);
    }


    @Override
    public void makeSpecialAttack(Player player) {

    }
}
