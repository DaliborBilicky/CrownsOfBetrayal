package dalibor.crownsofbetrayal.quests;

import dalibor.crownsofbetrayal.characters.Player;
import java.util.Random;

public class ItemCollectionQuest extends Quest {
    private final int numOfCollectedItems;
    private int collectedItems;

    public ItemCollectionQuest(Player player) {
        super(player, new Random().nextInt(20, 100));
        this.numOfCollectedItems = new Random().nextInt(1, 11);
    }

    public void collectedItem() {
        this.collectedItems++;
        if (this.collectedItems >= this.numOfCollectedItems) {
            this.setDone(true);
        }
    }

    @Override
    public String getDescription() {
        return String.format("Collect %d items for %d gold.",
            this.numOfCollectedItems - this.collectedItems, this.getReward());
    }
}
