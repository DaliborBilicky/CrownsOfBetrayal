package dalibor.crownsofbetrayal.items;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import java.awt.image.BufferedImage;

public class Item {
    private BufferedImage image;

    public Item() {
        this.image = new ImageReader().getBufferedImage("res/items/noItem.png");
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
