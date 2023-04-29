package dalibor.survivalGame.graphics.window;

import dalibor.survivalGame.main.Game;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
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
        frame.add(this.panel);
        frame.pack();
        device.setFullScreenWindow(frame);
        this.panel.requestFocus();
    }

    public void repaintPanel() {
        this.panel.repaint();
    }

}
