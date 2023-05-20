package dalibor.crownsofbetrayal.items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Item {
    private BufferedImage image;
    private int i;
    private int j;

    public Item(BufferedImage image) {
        this.image = image;
        this.i = 0;
        this.j = 0;
    }

    public void draw(Graphics2D g2D, int x, int y, int size) {
        g2D.drawImage(
            this.image,
            x - (size / 2),
            y - (size / 2),
            size,
            size,
            null);
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getI() {
        return this.i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return this.j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
