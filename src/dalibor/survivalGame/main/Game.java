package dalibor.survivalGame.main;

import dalibor.survivalGame.graphics.draw.Canvas;
import dalibor.survivalGame.graphics.window.Frame;
import dalibor.survivalGame.states.CurrentGameState;
import dalibor.survivalGame.states.Menu;
import dalibor.survivalGame.states.PlayingGame;
import java.awt.Graphics2D;
import java.awt.Toolkit;

public class Game implements Runnable {
    private static final int GAME_WIDTH =
        (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int GAME_HEIGHT =
        (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final int FPS = 60;
    private static final int UPS = 200;
    private static final double SECOND_IN_NANOSECOND = 1000000000.0;
    private static final int SECOND_IN_MILLISECOND = 1000;
    private final CurrentGameState currentGameState;
    private final Frame frame;
    private final Menu menu;
    private final PlayingGame playingGame;
    private final Canvas canvas;

    public Game() {
        this.canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        this.currentGameState = new CurrentGameState();
        this.menu = new Menu(
            this.currentGameState, this.canvas);
        this.playingGame = new PlayingGame(
            this.currentGameState, this.canvas, GAME_WIDTH, GAME_HEIGHT);

        this.frame = new Frame(this);
    }

    public void startGameLoop() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public void render(Graphics2D g2D) {
        switch (this.currentGameState.getState()) {
            case MENU -> this.menu.draw(g2D);
            case PLAYING_GAME -> this.playingGame.draw(g2D);
        }
    }

    @Override
    public void run() {
        double timePerFrame = SECOND_IN_NANOSECOND / FPS;
        double timePerUpdate = SECOND_IN_NANOSECOND / UPS;

        long previousTime = System.nanoTime();
        long previousPrintTime = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;


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
                updates++;
                deltaUpdate--;
            }

            if (deltaFrame >= 1) {
                this.frame.repaintPanel();
                frames++;
                deltaFrame--;
            }


            if (currentPrintTime - previousPrintTime >=
                SECOND_IN_MILLISECOND) {
                this.playingGame.setFPS(frames, updates);
                frames = 0;
                updates = 0;


                previousPrintTime = currentPrintTime;
            }
        }
    }

    private void update() {
        switch (this.currentGameState.getState()) {
            case MENU -> this.menu.update();
            case PLAYING_GAME -> this.playingGame.update();
        }
    }

    public CurrentGameState getCurrentGameState() {
        return this.currentGameState;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public PlayingGame getPlayingGame() {
        return this.playingGame;
    }

    public int getGameWidth() {
        return GAME_WIDTH;
    }

    public int getGameHeight() {
        return GAME_HEIGHT;
    }

}
