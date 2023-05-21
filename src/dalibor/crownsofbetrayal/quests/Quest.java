package dalibor.crownsofbetrayal.quests;

import dalibor.crownsofbetrayal.characters.Player;

public class Quest {
    private final Player player;
    private boolean done;
    private int reward;
    private String description;

    public Quest(Player player, int reward) {
        this.player = player;
        this.reward = reward;
        this.description = "No quest";
        this.done = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void giveReward() {
        this.player.gainGold(this.reward);
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
