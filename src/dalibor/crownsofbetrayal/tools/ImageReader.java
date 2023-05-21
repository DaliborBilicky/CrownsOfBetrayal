package dalibor.crownsofbetrayal.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Vyhradena trieda aby sa lepsie nacitali obrazky
 */
public class ImageReader {

    /**
     * @param imagePath cesta k obrazku
     * @return nacitany obrazok
     */
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
