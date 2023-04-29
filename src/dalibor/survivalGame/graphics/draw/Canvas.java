package dalibor.survivalGame.graphics.draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Canvas {
    private final int height;
    private final int width;
    private Graphics g2D;

    public Canvas(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public void setGraphics(Graphics g2D) {
        this.g2D = g2D;
    }

    public void drawMenuBG() {
        this.g2D.setColor(Color.gray);
        this.g2D.fillRect(0, 0, this.width, this.height);
    }

    public void drawFPS(int frames, int updates) {
        this.g2D.setColor(Color.GREEN);
        this.g2D.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        this.g2D.drawString(String.format("FPS: %d", frames), 5, 40);
        this.g2D.drawString(String.format("UPS: %d", updates), 5, 20);
    }

}
