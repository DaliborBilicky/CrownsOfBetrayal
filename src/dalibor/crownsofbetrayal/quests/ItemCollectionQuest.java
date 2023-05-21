package dalibor.crownsofbetrayal.quests;

import java.util.Random;

public class ItemCollectionQuest extends Quest {
    private final int numOfCollectedItems;
    private int collectedItems;

    /**
     * V konstruktore nastavujem napevno hodnoty do predkovho konstruktora
     * A nastavujem pocet itemov ktore treba pozbierat
     */
    public ItemCollectionQuest() {
        super(new Random().nextInt(20, 100));
        this.numOfCollectedItems = new Random().nextInt(1, 11);
    }

    /**
     * Kontroluje ci sa splnil quest ak nie tak pripocita do pocitadla
     */
    public void collectedItem() {
        this.collectedItems++;
        if (this.collectedItems >= this.numOfCollectedItems) {
            this.setDone(true);
        }
    }

    /**
     * @return popis questu
     */
    @Override
    public String getDescription() {
        return String.format("Collect %d items for %d gold.",
            this.numOfCollectedItems - this.collectedItems, this.getReward());
    }
}
