package dalibor.crownsofbetrayal.states;

/**
 * Trieda v ktorej sa spracovava aktualny state
 */
public class CurrentState {
    private States states;

    /**
     * V konstruktore sa nastavi state na Menu
     */
    public CurrentState() {
        this.states = States.MENU;
    }

    public States getState() {
        return this.states;
    }

    /**
     * @param states na ktory sa ma nastavit ak je Quit tak sa hra vypne
     */
    public void setState(States states) {
        if (states.name().equals(States.QUIT.name())) {
            System.exit(0);
        }
        this.states = states;
    }
}
