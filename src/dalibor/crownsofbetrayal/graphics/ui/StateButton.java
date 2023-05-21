package dalibor.crownsofbetrayal.graphics.ui;

import dalibor.crownsofbetrayal.states.CurrentState;
import dalibor.crownsofbetrayal.states.States;
import dalibor.crownsofbetrayal.tools.ImageReader;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * specialny typ tlacidla ktory dedi od tlacidla
 * po kliknuti nan nastavi gameState
 */
public class StateButton extends Button {
    private final BufferedImage leftArrow;
    private final BufferedImage rightArrow;
    private CurrentState currentState;
    private States state;

    /**
     * Vytvaranie sipok pre estetickost tlacidla
     *
     * @param posX   pozicia x
     * @param posY   pozicia y
     * @param width  sirka
     * @param height vyska
     */
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

    /**
     * Metoda nakresli sipky po stranach tlacidla
     *
     * @param g2D java trieda na kreslenie
     */
    @Override
    public void draw(Graphics2D g2D) {
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

    /**
     * Metoda nastavi state ktory mu bol urceny
     */
    public void applyState() {
        this.currentState.setState(this.state);
    }

    public States getState() {
        return this.state;
    }

    public void setState(States state) {
        this.state = state;
    }
}
