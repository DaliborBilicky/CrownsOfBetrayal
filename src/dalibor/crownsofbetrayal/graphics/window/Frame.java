package dalibor.crownsofbetrayal.graphics.window;

import dalibor.crownsofbetrayal.main.Game;
import dalibor.crownsofbetrayal.tools.ImageReader;
import java.awt.Cursor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Trieda na obalenie java JFrame ktory zobrazi okno
 */
public class Frame {
    /**
     * Konstanta pre nadpis okna
     */
    private static final String TITLE = "Crowns Of Betrayal";
    private final Panel panel;

    /**
     * Konstruktor nastavuje okno tak ako potrebujem
     *
     * @param game hlavna trieda hra aby som ju pridal do panelu lebo je zapuzdreny
     */
    public Frame(Game game) {
        // kod z internetu vygoolgy som ako dat JFrame na full screen
        GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0];
        JFrame frame = new JFrame();
        this.panel = new Panel(game);

        frame.setTitle(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(
            new ImageReader().getBufferedImage("res/cursor.png"));
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setVisible(true);
        device.setFullScreenWindow(frame);
        frame.add(this.panel);
        frame.pack();
        this.panel.requestFocus();
        // kod z internetu cisto len pre dobry pocit. Vygoogly som ako zmenit cursor
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = new ImageReader()
            .getBufferedImage("res/cursor.png");
        Cursor cursor =
            toolkit.createCustomCursor(image, new Point(0, 0), "none");
        frame.setCursor(cursor);
    }

    /**
     * Metoda pre zapuzdrenie Panelu
     */
    public void repaintPanel() {
        this.panel.repaint();
    }

}
