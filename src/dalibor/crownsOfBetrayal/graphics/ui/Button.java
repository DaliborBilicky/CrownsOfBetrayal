package dalibor.crownsOfBetrayal.graphics.ui;

import dalibor.crownsOfBetrayal.graphics.ImageReader;
import dalibor.crownsOfBetrayal.states.CurrentState;
import dalibor.crownsOfBetrayal.states.States;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Button {
    private final Rectangle buttonBounds;
    private final int posX;
    private final int posY;
    private final CurrentState currentState;
    private final int width;
    private final BufferedImage leftArrow;
    private final BufferedImage rightArrow;
    private final int height;
    private final States state;
    private boolean mouseIn;

    public Button(int posX, int posY, int width, int height, States state, CurrentState currentState) {
        this.width = width;
        this.height = height;
        this.posX = posX - (this.width / 2);
        this.posY = posY - (this.height / 2);
        this.currentState = currentState;
        this.state = state;
        this.buttonBounds =
            new Rectangle(this.posX, this.posY, this.width, this.height);
        this.leftArrow =
            new ImageReader().getBufferedImage(
                "res/buttons/button_arrow_left.png");
        this.rightArrow =
            new ImageReader().getBufferedImage(
                "res/buttons/button_arrow_right.png");
    }

    public void draw(Graphics2D g2D) {
        int width = this.height;
        if (this.mouseIn) {
            g2D.drawImage(
                this.leftArrow,
                this.posX - this.height,
                this.posY,
                width,
                this.height,
                null);
            g2D.drawImage(
                this.rightArrow,
                this.posX + this.width,
                this.posY,
                width,
                this.height,
                null);
            g2D.setColor(Color.GREEN);
            //g2D.drawRect(this.posX, this.posY, this.width, this.height);
        }
    }

    public void setMouseIn(boolean mouseIn) {
        this.mouseIn = mouseIn;
    }

    public Rectangle getButtonBounds() {
        return buttonBounds;
    }

    public void applyState() {
        this.currentState.setState(this.state);
    }
}
