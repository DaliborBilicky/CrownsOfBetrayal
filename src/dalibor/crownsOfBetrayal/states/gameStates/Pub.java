package dalibor.crownsOfBetrayal.states.gameStates;

import dalibor.crownsOfBetrayal.graphics.ImageReader;
import dalibor.crownsOfBetrayal.states.CurrentState;
import dalibor.crownsOfBetrayal.states.State;
import dalibor.crownsOfBetrayal.states.States;
import java.awt.event.MouseEvent;

public class Pub extends State {
    public Pub(CurrentState currentState, int windowWidth, int windowHeight) {
        super(currentState, new ImageReader().getBufferedImage("res/bg/pub.png"), windowWidth, windowHeight);
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
        if (event.getX() < 100 && event.getY() < 100) {
            this.getCurrentState().setState(States.GAME_MENU);
        }
    }

    @Override
    public void mouseMoved(MouseEvent event) {

    }

}
