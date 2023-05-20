package dalibor.crownsofbetrayal.characters.enemies;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.tools.ImageReader;

public class Snowman extends Enemy {
    public Snowman() {
        super(new ImageReader()
                .getBufferedImage("res/characters/snowman.png"),
            50, 15);
    }


    @Override
    public void makeSpecialAttack(Player player) {

    }
}
