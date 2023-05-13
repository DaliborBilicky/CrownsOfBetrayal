package dalibor.crownsofbetrayal.graphics.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Button {
    private final Rectangle buttonBounds;
    private final int posX;
    private final int posY;
    private final int width;
    private final int height;
    private boolean mouseIn;

    public Button(int posX, int posY, int width, int height) {
        this.width = width;
        this.height = height;
        this.posX = posX - (this.width / 2);
        this.posY = posY - (this.height / 2);
        this.buttonBounds =
            new Rectangle(this.posX, this.posY, this.width, this.height);

    }

    public void draw(Graphics2D g2D) {
        if (this.mouseIn) {
            g2D.setColor(Color.WHITE);
            g2D.setStroke(new BasicStroke(5));
            g2D.drawRect(this.posX, this.posY, this.width, this.height);
        }
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isMouseIn() {
        return this.mouseIn;
    }

    public void setMouseIn(boolean mouseIn) {
        this.mouseIn = mouseIn;
    }

    public Rectangle getButtonBounds() {
        return this.buttonBounds;
    }


}
