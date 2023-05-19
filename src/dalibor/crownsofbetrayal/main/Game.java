package dalibor.crownsofbetrayal.main;

import dalibor.crownsofbetrayal.graphics.window.Frame;
import dalibor.crownsofbetrayal.states.CurrentState;
import dalibor.crownsofbetrayal.states.gameStates.Crafting;
import dalibor.crownsofbetrayal.states.gameStates.GameMenu;
import dalibor.crownsofbetrayal.states.gameStates.Inventory;
import dalibor.crownsofbetrayal.states.gameStates.Menu;
import dalibor.crownsofbetrayal.states.gameStates.Pub;
import dalibor.crownsofbetrayal.states.gameStates.Quests;
import dalibor.crownsofbetrayal.states.gameStates.Shop;
import dalibor.crownsofbetrayal.states.gameStates.WorldMap;
import java.awt.Graphics2D;
import java.awt.Toolkit;

public class Game implements Runnable {
    private static final int EXPECTED_WINDOW_WIDTH = 1920;
    private static final int EXPECTED_WINDOW_HEIGHT = 1080;
    private static final int WINDOW_WIDTH =
        (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int WINDOW_HEIGHT =
        (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final int FPS = 30;
    private static final int UPS = 60;
    private static final double SECOND_IN_NANOSECOND = 1000000000.0;
    private static final int SECOND_IN_MILLISECOND = 1000;
    private final CurrentState currentState;
    private final Frame frame;
    private final Menu menu;
    private final GameMenu gameMenu;
    private final Inventory inventory;
    private final Pub pub;
    private final Quests quests;
    private final Shop shop;
    private final Crafting crafting;
    private final WorldMap worldMap;

    public Game() {
        System.out.println(EXPECTED_WINDOW_WIDTH);
        System.out.println(WINDOW_WIDTH);
        System.out.println(EXPECTED_WINDOW_WIDTH / WINDOW_WIDTH);
        double scale = (double)EXPECTED_WINDOW_WIDTH / WINDOW_WIDTH;
        this.currentState = new CurrentState();
        this.menu =
            new Menu(this.currentState, WINDOW_WIDTH, WINDOW_HEIGHT, scale);
        this.gameMenu =
            new GameMenu(this.currentState, WINDOW_WIDTH, WINDOW_HEIGHT, scale);
        this.inventory =
            new Inventory(this.currentState, WINDOW_WIDTH, WINDOW_HEIGHT, scale);
        this.pub =
            new Pub(this.currentState, WINDOW_WIDTH, WINDOW_HEIGHT, scale);
        this.quests =
            new Quests(this.currentState, WINDOW_WIDTH, WINDOW_HEIGHT, scale);
        this.shop =
            new Shop(this.currentState, WINDOW_WIDTH, WINDOW_HEIGHT, scale);
        this.crafting =
            new Crafting(this.currentState, WINDOW_WIDTH, WINDOW_HEIGHT, scale);
        this.worldMap =
            new WorldMap(this.currentState, WINDOW_WIDTH, WINDOW_HEIGHT, scale);

        this.frame = new Frame(this);
    }


    public void startGameLoop() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = SECOND_IN_NANOSECOND / FPS;
        double timePerUpdate = SECOND_IN_NANOSECOND / UPS;

        long previousTime = System.nanoTime();
        long previousPrintTime = System.currentTimeMillis();


        double deltaFrame = 0;
        double deltaUpdate = 0;

        while (true) {
            long currentTime = System.nanoTime();
            long currentPrintTime = System.currentTimeMillis();
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


            if (currentPrintTime - previousPrintTime >=
                SECOND_IN_MILLISECOND) {

                previousPrintTime = currentPrintTime;
            }
        }
    }

    private void update() {
        switch (this.currentState.getState()) {
            case MENU -> this.menu.update();
            case GAME_MENU -> this.gameMenu.update();
            case INVENTORY -> this.inventory.update();
            case PUB -> this.pub.update();
            case SHOP -> this.shop.update();
            case WORLD_MAP -> this.worldMap.update();
            case QUESTS -> this.quests.update();
            case CRAFTING -> this.crafting.update();
        }
    }

    public void render(Graphics2D g2D) {
        switch (this.currentState.getState()) {
            case MENU -> this.menu.draw(g2D);
            case GAME_MENU -> this.gameMenu.draw(g2D);
            case INVENTORY -> this.inventory.draw(g2D);
            case PUB -> this.pub.draw(g2D);
            case SHOP -> this.shop.draw(g2D);
            case WORLD_MAP -> this.worldMap.draw(g2D);
            case QUESTS -> this.quests.draw(g2D);
            case CRAFTING -> this.crafting.draw(g2D);
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

    public Shop getShop() {
        return this.shop;
    }

    public Crafting getCrafting() {
        return this.crafting;
    }

    public WorldMap getWorldMap() {
        return this.worldMap;
    }

    public int getWidth() {
        return WINDOW_WIDTH;
    }

    public int getHeight() {
        return WINDOW_HEIGHT;
    }

}
