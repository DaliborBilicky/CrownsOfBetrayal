package dalibor.crownsofbetrayal.graphics.window;

import dalibor.crownsofbetrayal.listeners.GameMouseListener;
import dalibor.crownsofbetrayal.main.Game;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Trieda cisto na vykreslovanie a prijimanie inputov z mysky
 */
public class Panel extends JPanel {
    private final Game game;

    /**
     * @param game hlavna trieda aby som vedel zavolat jej kresliacu metodu
     */
    public Panel(Game game) {
        GameMouseListener gML = new GameMouseListener(this);
        this.game = game;
        this.setPreferredSize(new Dimension(this.game.getWidth(), this.game.getHeight()));
        this.addMouseListener(gML);
        this.addMouseMotionListener(gML);
        this.setFocusable(true);
        this.setLayout(null);
    }


    /**
     * hlavna metoda kde sa vsetko vykresluje
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        this.game.render(g2D);
    }


    public Game getGame() {
        return this.game;
    }
}
