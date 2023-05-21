package dalibor.crownsofbetrayal.quests;

import java.util.Random;

/**
 * Quest ktory pocita pocet navstivenych dungeonov
 */
public class DungeonVisitingQuest extends Quest {
    private final int numOfVisits;
    private int visitCounter;

    /**
     * V konstruktore nastavujem napevno hodnoty do predkovho konstruktora
     * A nastavujem pocet navstiveni
     */
    public DungeonVisitingQuest() {
        super(new Random().nextInt(20, 100));
        this.numOfVisits = new Random().nextInt(1, 6);
    }

    /**
     * Kontroluje ci sa splnil quest ak nie tak pripocita do pocitadla
     */
    public void visitedDungeon() {
        this.visitCounter++;
        if (this.visitCounter >= this.numOfVisits) {
            this.setDone(true);
        }
    }

    /**
     * @return popis questu
     */
    @Override
    public String getDescription() {
        return String.format("Visit %d dungeons for %d gold.",
            this.numOfVisits - this.visitCounter, this.getReward());
    }
}
