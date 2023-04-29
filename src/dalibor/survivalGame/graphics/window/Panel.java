package dalibor.survivalGame.graphics.window;

import dalibor.survivalGame.listeners.GameKeyListener;
import dalibor.survivalGame.listeners.GameMouseListener;
import dalibor.survivalGame.main.Game;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Panel extends JPanel {
    private final Game game;

    public Panel(Game game) {
        this.game = game;
        this.setPreferredSize(new Dimension(this.game.getGameWidth(), this.game.getGameHeight()));
        this.addMouseListener(new GameMouseListener(this));
        this.addKeyListener(new GameKeyListener(this));
        this.setFocusable(true);
        this.setLayout(null);
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        this.game.render(g2D);
    }


    public Game getGame() {
        return this.game;
    }
}
