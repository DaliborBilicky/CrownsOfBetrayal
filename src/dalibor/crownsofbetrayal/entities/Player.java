package dalibor.crownsofbetrayal.entities;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import java.awt.image.BufferedImage;

public class Player {
    private final BufferedImage imageRepresentation;
    private int suplies;

    public Player() {
        this.suplies = 50;
        this.imageRepresentation = new ImageReader().getBufferedImage("res/characters/player.png");
    }


}
