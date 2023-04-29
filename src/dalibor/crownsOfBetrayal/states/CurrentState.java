package dalibor.crownsOfBetrayal.states;

public class CurrentState {
    private States states;

    public CurrentState() {
        this.states = States.MENU;
    }

    public States getState() {
        return this.states;
    }

    public void setState(States states) {
        this.states = states;
    }
}
