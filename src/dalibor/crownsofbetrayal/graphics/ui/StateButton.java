package dalibor.crownsofbetrayal.graphics.ui;

import dalibor.crownsofbetrayal.states.CurrentState;
import dalibor.crownsofbetrayal.states.States;
import dalibor.crownsofbetrayal.tools.ImageReader;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class StateButton extends Button {
    private final BufferedImage leftArrow;
    private final BufferedImage rightArrow;
    private CurrentState currentState;
    private States state;

    public StateButton(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
        this.leftArrow =
            new ImageReader().getBufferedImage(
                "res/buttons/button_arrow_left.png");
        this.rightArrow =
            new ImageReader().getBufferedImage(
                "res/buttons/button_arrow_right.png");
    }


    public void setCurrentState(CurrentState currentState) {
        this.currentState = currentState;
    }

    public void setState(States state) {
        this.state = state;
    }

    @Override
    public void draw(Graphics2D g2D) {
        //        super.draw(g2D);
        int width = this.getHeight();
        if (this.isMouseIn()) {
            g2D.drawImage(
                this.leftArrow,
                this.getPosX() - this.getHeight(),
                this.getPosY(),
                width,
                this.getHeight(),
                null);
            g2D.drawImage(
                this.rightArrow,
                this.getPosX() + this.getWidth(),
                this.getPosY(),
                width,
                this.getHeight(),
                null);
        }
    }

    public void applyState() {
        this.currentState.setState(this.state);
    }
}
