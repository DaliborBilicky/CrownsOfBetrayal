package dalibor.crownsOfBetrayal.states.gameStates;

import dalibor.crownsOfBetrayal.graphics.ImageReader;
import dalibor.crownsOfBetrayal.states.CurrentState;
import dalibor.crownsOfBetrayal.states.State;
import java.awt.event.MouseEvent;

public class Pup extends State {
    public Pup(CurrentState currentState, int windowWidth, int windowHeight) {
        super(currentState, new ImageReader().getBufferedImage("res/bg/bg.png"), windowWidth, windowHeight);
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

    }

}
