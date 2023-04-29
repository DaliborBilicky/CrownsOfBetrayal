package dalibor.survivalGame.states;

public class CurrentGameState {
    private State state;

    public CurrentGameState() {
        this.state = State.MENU;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
