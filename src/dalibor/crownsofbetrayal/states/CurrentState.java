package dalibor.crownsofbetrayal.states;

public class CurrentState {
    private States states;

    public CurrentState() {
        this.states = States.WORLD_MAP;
    }

    public States getState() {
        return this.states;
    }

    public void setState(States states) {
        if (states.name().equals(States.EXIT.name())) {
            System.exit(0);
        }
        this.states = states;
    }
}
