package dalibor.crownsOfBetrayal.graphics.ui;

import dalibor.crownsOfBetrayal.graphics.ImageReader;
import dalibor.crownsOfBetrayal.states.CurrentState;
import dalibor.crownsOfBetrayal.states.States;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class LabelButton extends Button {
    private final BufferedImage leftArrow;
    private final BufferedImage rightArrow;
    private final CurrentState currentState;
    private final States state;

    public LabelButton(int posX, int posY, int width, int height,
                       States state, CurrentState currentState) {
        super(posX, posY, width, height);
        this.currentState = currentState;
        this.state = state;
        this.leftArrow =
            new ImageReader().getBufferedImage(
                "res/buttons/button_arrow_left.png");
        this.rightArrow =
            new ImageReader().getBufferedImage(
                "res/buttons/button_arrow_right.png");
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
