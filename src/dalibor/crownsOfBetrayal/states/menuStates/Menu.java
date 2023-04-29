package dalibor.crownsOfBetrayal.states.menuStates;

import dalibor.crownsOfBetrayal.graphics.ImageReader;
import dalibor.crownsOfBetrayal.states.CurrentState;
import dalibor.crownsOfBetrayal.states.State;
import dalibor.crownsOfBetrayal.states.States;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Menu extends State {

    public Menu(CurrentState currentState, int windowWidth, int windowHeight) {
        super(currentState, new ImageReader().getBufferedImage("res/bg/menu.png"), windowWidth, windowHeight);
    }

    @Override
    public void draw(Graphics2D g2D) {
        super.draw(g2D);
        g2D.setColor(Color.GREEN);
        g2D.drawRect((int) (this.getWindowWidth() * 0.5) - 200,
            (int) (this.getWindowHeight() * 0.88 - 100),
            400,
            100);
        g2D.drawRect((int) (this.getWindowWidth() * 0.5) - 200,
            (int) (this.getWindowHeight() * 0.75 - 100),
            400,
            100);
        g2D.drawRect((int) (this.getWindowWidth() * 0.5) - 200,
            (int) (this.getWindowHeight() * 0.62 - 100),
            400,
            100);
        g2D.drawRect((int) (this.getWindowWidth() * 0.5) - 200,
            (int) (this.getWindowHeight() * 0.49 - 100),
            400,
            100);
    }

    @Override
    public void update() {

    }

    @Override
    public void mosePressed(MouseEvent event) {

    }

    @Override
    public void moseReleased(MouseEvent event) {

    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (event.getX() > this.getWindowWidth() * 0.5 - 200 &&
            event.getX() < this.getWindowWidth() * 0.5 + 200 &&
            event.getY() > this.getWindowHeight() * 0.88 - 100 &&
            event.getY() < this.getWindowHeight() * 0.88 + 100) {
            System.exit(0);
        }
        if (event.getX() > this.getWindowWidth() * 0.5 - 200 &&
            event.getX() < this.getWindowWidth() * 0.5 + 200 &&
            event.getY() > this.getWindowHeight() * 0.49 - 100 &&
            event.getY() < this.getWindowHeight() * 0.49 + 100) {
            this.getCurrentState().setState(States.GAME_MENU);
        }
    }
}
