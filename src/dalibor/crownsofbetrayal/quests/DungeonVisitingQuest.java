package dalibor.crownsofbetrayal.quests;

import dalibor.crownsofbetrayal.characters.Player;
import java.util.Random;

public class DungeonVisitingQuest extends Quest {
    private final int numOfVisits;
    private int visitCounter;

    public DungeonVisitingQuest(Player player) {
        super(player, new Random().nextInt(20, 100));
        this.numOfVisits = new Random().nextInt(1, 6);
    }

    public void visitedDungeon() {
        this.visitCounter++;
        if (this.visitCounter >= this.numOfVisits) {
            this.setDone(true);
        }
    }

    @Override
    public String getDescription() {
        return String.format("Visit %d dungeons for %d gold.",
            this.numOfVisits - this.visitCounter, this.getReward());
    }
}
