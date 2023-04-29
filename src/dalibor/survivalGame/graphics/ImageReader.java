package dalibor.survivalGame.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImageReader {

    public BufferedImage getBufferedImage(String imagePath) {
        BufferedImage readImage = null;
        try {
            readImage = ImageIO.read(new File(imagePath));
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(
                null,
                "File " + imagePath + " not found.");
        }
        return readImage;
    }

}
