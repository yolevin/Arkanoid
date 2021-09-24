import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The class 'WideEasyBG' is in charge of the background of the level "Wide Easy".
 * @author Yotam Levin
 * ID: 313248916
 */
public class WideEasyBG implements Sprite {

    /**
     * Constructor method.
     */
    public WideEasyBG() {
    }

    /**
     * Draws the background.
     * @param d the surface to draw the background on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, GameFlow.WIDTH, GameFlow.HEIGHT);
        d.setColor(Color.YELLOW.brighter());
        d.fillCircle(150, 150, 75);
        int x = -5;
        for (int i = 0; i < 125; i++) {
            d.drawLine(150, 150, x, (int) (GameFlow.HEIGHT / 2.5));
            x = x + 5;
        }
        d.setColor(Color.ORANGE);
        d.fillCircle(150, 150, 65);
        d.setColor(Color.YELLOW.darker());
        d.fillCircle(150, 150, 55);
    }

    /**
     * defines what the background has to do when time passes, nothing.
     */
    @Override
    public void timePassed() {

    }

    /**
     * adds the background to the game - adds it to the sprites list.
     * @param game the current game that is run.
     */
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
