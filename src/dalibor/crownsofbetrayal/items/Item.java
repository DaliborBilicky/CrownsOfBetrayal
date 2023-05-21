package dalibor.crownsofbetrayal.items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Abstraktna trieda item od ktorej dedia vsetky itemy
 */
public abstract class Item {
    private final BufferedImage image;

    /**
     * @param image obrazok itemu
     */
    public Item(BufferedImage image) {
        this.image = image;
    }

    /**
     * vykresli obrazok itemu
     *
     * @param g2D  java trieda na kreslenie
     * @param x    x pozicia
     * @param y    y pozicia
     * @param size velkost obrazka
     */
    public void draw(Graphics2D g2D, int x, int y, int size) {
        g2D.drawImage(
            this.image,
            x - (size / 2),
            y - (size / 2),
            size,
            size,
            null);
    }
}
