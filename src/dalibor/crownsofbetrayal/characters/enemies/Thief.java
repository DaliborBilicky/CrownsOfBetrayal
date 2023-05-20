package dalibor.crownsofbetrayal.characters.enemies;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.tools.ImageReader;

public class Thief extends Enemy {
    public Thief() {
        super(new ImageReader()
                .getBufferedImage("res/characters/thief.png"),
            100, 10);
    }


    @Override
    public void makeSpecialAttack(Player player) {

    }
}
