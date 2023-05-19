package dalibor.crownsofbetrayal.graphics.window;

import dalibor.crownsofbetrayal.graphics.ImageReader;
import dalibor.crownsofbetrayal.main.Game;
import java.awt.Cursor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Frame {
    private static final String TITLE = "Minecraft 2D";
    private final Panel panel;

    public Frame(Game game) {
        GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0];
        JFrame frame = new JFrame();
        this.panel = new Panel(game);

        frame.setTitle(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setVisible(true);
        device.setFullScreenWindow(frame);
        frame.add(this.panel);
        frame.pack();
        this.panel.requestFocus();
        // kod z internetu cisto len pre dobry pocit 
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = new ImageReader()
            .getBufferedImage("res/cursor.png");
        Cursor cursor =
            toolkit.createCustomCursor(image, new Point(0, 0), "none");
        frame.setCursor(cursor);
    }

    public void repaintPanel() {
        this.panel.repaint();
    }

}
