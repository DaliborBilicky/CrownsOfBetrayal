package dalibor.crownsofbetrayal.quests;

import dalibor.crownsofbetrayal.characters.enemies.Enemy;
import dalibor.crownsofbetrayal.tools.RandomGenerator;
import java.util.Random;

public class KillingQuest extends Quest {
    private final int numberOfEnemies;
    private final Enemy typeOfEnemy;
    private int killedEnemies;

    /**
     * V konstruktore nastavujem napevno hodnoty do predkovho konstruktora
     * A nastavujem pocet nepriatelov na zabitie
     */
    public KillingQuest() {
        super(new Random().nextInt(20, 100));
        this.numberOfEnemies = new Random().nextInt(1, 5);
        this.typeOfEnemy = new RandomGenerator().getEnemy();
    }

    /**
     * Kontroluje ci sa splnil quest ak nie tak pripocita do pocitadla
     */
    public void killedEnemy(Enemy enemy) {
        if (enemy.getName().equals(this.typeOfEnemy.getName())) {
            this.killedEnemies++;
            if (this.killedEnemies >= this.numberOfEnemies) {
                this.setDone(true);
            }
        }
    }

    /**
     * @return popis questu
     */
    @Override
    public String getDescription() {
        return String.format("Kill %d %s for %s gold.",
            this.numberOfEnemies - this.killedEnemies,
            this.typeOfEnemy.getName(), this.getReward());
    }
}
