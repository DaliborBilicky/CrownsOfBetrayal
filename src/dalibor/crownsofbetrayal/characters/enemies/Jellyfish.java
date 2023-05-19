package dalibor.crownsofbetrayal.characters.enemies;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.graphics.ImageReader;

public class Jellyfish extends Enemy {
    public Jellyfish() {
        super(new ImageReader().getBufferedImage("res/characters/jellyfish.png"), 50, 30);
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