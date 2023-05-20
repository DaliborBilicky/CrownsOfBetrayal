package dalibor.crownsofbetrayal.characters.enemies;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.tools.ImageReader;

public class Scarecrow extends Enemy {

    public Scarecrow() {
        super(new ImageReader()
                .getBufferedImage("res/characters/scarecrow.png"),
            200, 20);
    }


    @Override
    public void makeSpecialAttack(Player player) {

    }
}
