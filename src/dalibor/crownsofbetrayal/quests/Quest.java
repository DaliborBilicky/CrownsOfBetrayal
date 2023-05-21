package dalibor.crownsofbetrayal.quests;

/**
 * Trieda quest od ktorej dedia specialne typy questov
 * taktiez sluzi na predijdenie null pointer chybe
 */
public class Quest {
    private final int reward;
    private final String description;
    private boolean done;


    /**
     * @param reward mince za dokonceny quest
     */
    public Quest(int reward) {
        this.reward = reward;
        this.description = "No quest";
        this.done = false;
    }

    /**
     * Bezparametricky konstruktor
     */
    public Quest() {
        this.reward = 0;
        this.description = "No quest";
        this.done = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getReward() {
        return this.reward;
    }
}
