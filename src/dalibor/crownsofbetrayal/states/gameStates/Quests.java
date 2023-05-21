package dalibor.crownsofbetrayal.states.gameStates;

import dalibor.crownsofbetrayal.graphics.ui.Button;
import dalibor.crownsofbetrayal.graphics.ui.QuestButton;
import dalibor.crownsofbetrayal.main.Game;
import dalibor.crownsofbetrayal.states.State;
import dalibor.crownsofbetrayal.states.States;
import dalibor.crownsofbetrayal.tools.ImageReader;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * Specialny druh statu kde si hrac vie pozret questy pripadne ich zmazat
 */
public class Quests extends State {
    private final QuestButton[][] questButtons;

    /**
     * Natvrdo nastavene pozadie
     *
     * @param game hlavna trieda
     */
    public Quests(Game game) {
        super(game, new ImageReader().getBufferedImage("res/bg/quests.png"));
        this.questButtons = new QuestButton[5][2];
        this.setButtons();
    }

    /**
     * Nastavi tlacidlam spravne hodnoty
     */
    private void setButtons() {
        for (int i = 0; i < this.questButtons.length; i++) {
            for (int j = 0; j < this.questButtons[i].length; j++) {
                this.questButtons[i][j] = new QuestButton(
                    (int)(this.getWindowWidth() * 0.077 +
                        (890 / this.getScale() * j)),
                    (int)(this.getWindowHeight() * 0.205 +
                        ((150 / this.getScale() + (i * 1.1)) * i)),
                    (int)(60 / this.getScale()),
                    (int)(60 / this.getScale())
                );
            }
        }
    }

    /**
     * Vykresluje vsetko co sa deje na obrazovke ak je Dungeon state aktivny
     *
     * @param g2D java trieda na vykreslovanie
     */
    @Override
    public void draw(Graphics2D g2D) {
        super.draw(g2D);
        for (Button[] buttons : this.questButtons) {
            for (Button button : buttons) {
                button.draw(g2D);
            }
        }
        g2D.setFont(new Font("Viner Hand ITC", Font.BOLD, 45));
        g2D.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                g2D.drawString(this.getPlayer().getQuest(i).getDescription(),
                    (int)(this.getGame().getWidth() * 0.1),
                    (int)(this.getGame().getHeight() * 0.25 + (150 * i)));
            } else {
                g2D.drawString(this.getPlayer().getQuest(i).getDescription(),
                    (int)(this.getGame().getWidth() * 0.57),
                    (int)(this.getGame().getHeight() * 0.25 + (150 * (i - 5))));
            }
        }
    }

    /**
     * Kontroluje ci hrac nema splneny quest
     */
    @Override
    public void update() {
        this.getPlayer().removeDoneQuests();
    }

    /**
     * Kontroluje ci sa dane tlacidlo pouzilo a ak hej tak vykona funkciu
     * tlacidla
     *
     * @param event event z mysky
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        if (event.getX() < 100 / this.getScale() &&
            event.getY() < 100 / this.getScale()) {
            this.getCurrentState().setState(States.GAME_MENU);
        }

        for (int i = 0; i < this.questButtons.length; i++) {
            for (int j = 0; j < this.questButtons[i].length; j++) {
                if (this.questButtons[i][j].getButtonBounds()
                    .contains(event.getX(), event.getY())) {
                    this.getPlayer().removeQuest(
                        (j * this.questButtons.length) + i);
                }
            }
        }
    }

    /**
     * Kontroluje ci kurzor je nad tlacidlom ak hej tak sa nastavy tlcidlo
     *
     * @param event event z mysky
     */
    @Override
    public void mouseMoved(MouseEvent event) {
        for (Button[] buttons : this.questButtons) {
            for (Button button : buttons) {
                button.setMouseIn(false);
                if (button.getButtonBounds().contains(event.getX(), event.getY())) {
                    button.setMouseIn(true);
                }
            }
        }
    }
}
