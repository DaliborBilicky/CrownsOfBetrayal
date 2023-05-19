package dalibor.crownsofbetrayal.characters.enemies;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.graphics.ImageReader;

public class Snowman extends Enemy {
    public Snowman() {
        super(new ImageReader()
                .getBufferedImage("res/characters/snowman.png"),
            50, 15);
    }


    @Override
    public int dealDamage() {
        return 0;
    }

    @Override
    public void takeDamage(int takenDamage) {

    }

    @Override
    public void makeSpecialAttack(Player player) {

    }
}
