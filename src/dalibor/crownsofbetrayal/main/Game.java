package dalibor.crownsofbetrayal.main;

import dalibor.crownsofbetrayal.characters.Player;
import dalibor.crownsofbetrayal.graphics.window.Frame;
import dalibor.crownsofbetrayal.states.CurrentState;
import dalibor.crownsofbetrayal.states.gameStates.Dungeon;
import dalibor.crownsofbetrayal.states.gameStates.GameMenu;
import dalibor.crownsofbetrayal.states.gameStates.Inventory;
import dalibor.crownsofbetrayal.states.gameStates.Menu;
import dalibor.crownsofbetrayal.states.gameStates.Pub;
import dalibor.crownsofbetrayal.states.gameStates.Quests;
import dalibor.crownsofbetrayal.states.gameStates.WorldMap;
import java.awt.Graphics2D;
import java.awt.Toolkit;

/**
 * Trieda kde sa vykonavaju vsetky ukony programu
 */
public class Game implements Runnable {
    /**
     * Konstany pre nastavenie velkosti okna
     */
    private static final int EXPECTED_WINDOW_WIDTH = 1920;
    // zistovanie presnych rozmerov mam z minulej semestralky
    private static final int WINDOW_WIDTH =
        (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int WINDOW_HEIGHT =
        (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    /**
     * FPS kolko krat sa ma obnovovat vykreslovanie
     * UPS kolko krat sa ma updatovat hra
     */
    private static final int FPS = 30;
    private static final int UPS = 60;
    /**
     * Reprezentacia sekundy pre lepsiu pracu v kode
     */
    private static final double SECOND_IN_NANOSECOND = 1000000000.0;
    private final CurrentState currentState;
    private final Frame frame;
    private final Menu menu;
    private final GameMenu gameMenu;
    private final Inventory inventory;
    private final Pub pub;
    private final Quests quests;
    private final WorldMap worldMap;
    private final Player player;
    private final double scale;
    private final Dungeon dungeon;

    /**
     * Incializacia vsetkych komponentov v hre
     */
    public Game() {
        this.scale = (double)EXPECTED_WINDOW_WIDTH / WINDOW_WIDTH;

        this.player = new Player(this);

        this.currentState = new CurrentState();
        this.menu = new Menu(this);
        this.gameMenu = new GameMenu(this);
        this.inventory = new Inventory(this);
        this.pub = new Pub(this);
        this.quests = new Quests(this);
        this.worldMap = new WorldMap(this);
        this.dungeon = new Dungeon(this);

        this.frame = new Frame(this);
    }

    /**
     * Spusti game loop
     */
    public void startGameLoop() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Metoda zapezpecuje spravne obnovovanie hry
     * upravena verzia z minulej semestralky
     * !!!Dopomoc internetom link v dokumentacii na video!!!
     */
    @Override
    public void run() {
        double timePerFrame = SECOND_IN_NANOSECOND / FPS;
        double timePerUpdate = SECOND_IN_NANOSECOND / UPS;

        long previousTime = System.nanoTime();

        double deltaFrame = 0;
        double deltaUpdate = 0;

        while (true) {
            long currentTime = System.nanoTime();
            long timesDiff = (currentTime - previousTime);

            deltaFrame += timesDiff / timePerFrame;
            deltaUpdate += timesDiff / timePerUpdate;

            previousTime = currentTime;

            if (deltaUpdate >= 1) {
                this.update();
                deltaUpdate--;
            }

            if (deltaFrame >= 1) {
                this.frame.repaintPanel();
                deltaFrame--;
            }
        }
    }

    /**
     * Switch ktory podla aktulaneho statu updatuje len ten spravny
     */
    private void update() {
        switch (this.currentState.getState()) {
            case MENU -> this.menu.update();
            case GAME_MENU -> this.gameMenu.update();
            case INVENTORY -> this.inventory.update();
            case PUB -> this.pub.update();
            case WORLD_MAP -> this.worldMap.update();
            case QUESTS -> this.quests.update();
            case DUNGEON -> this.dungeon.update();
            case QUIT -> System.exit(0);
        }
    }

    /**
     * Switch ktory podla aktulaneho statu vykresluje len ten spravny
     */
    public void render(Graphics2D g2D) {
        switch (this.currentState.getState()) {
            case MENU -> this.menu.draw(g2D);
            case GAME_MENU -> this.gameMenu.draw(g2D);
            case INVENTORY -> this.inventory.draw(g2D);
            case PUB -> this.pub.draw(g2D);
            case WORLD_MAP -> this.worldMap.draw(g2D);
            case QUESTS -> this.quests.draw(g2D);
            case DUNGEON -> this.dungeon.draw(g2D);
            case QUIT -> System.exit(0);
        }
    }

    public CurrentState getCurrentState() {
        return this.currentState;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public GameMenu getGameMenu() {
        return this.gameMenu;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public Pub getPub() {
        return this.pub;
    }

    public Quests getQuests() {
        return this.quests;
    }

    public WorldMap getWorldMap() {
        return this.worldMap;
    }

    public Dungeon getDungeon() {
        return this.dungeon;
    }

    public int getWidth() {
        return WINDOW_WIDTH;
    }

    public int getHeight() {
        return WINDOW_HEIGHT;
    }

    public Player getPlayer() {
        return this.player;
    }

    public double getScale() {
        return this.scale;
    }

}
