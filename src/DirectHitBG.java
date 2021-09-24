import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The class 'DirectHitBG' is in charge of the background of the level "Direct Hit".
 * @author Yotam Levin
 * ID: 313248916
 */
public class DirectHitBG implements Sprite {

    /**
     * Constructor method.
     */
    public DirectHitBG() {
    }

    /**
     * Draws the background.
     * @param d the surface to draw the background on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GRAY);
        d.fillRectangle(0, 0, GameFlow.WIDTH, GameFlow.HEIGHT);
        d.setColor(Color.BLUE);
        d.drawCircle(GameFlow.WIDTH / 2, 125, 60);
        d.drawCircle(GameFlow.WIDTH / 2, 125, 90);
        d.drawCircle(GameFlow.WIDTH / 2, 125, 120);
        d.drawLine(GameFlow.WIDTH / 2, 155, GameFlow.WIDTH / 2, 275);
        d.drawLine(GameFlow.WIDTH / 2, 95, GameFlow.WIDTH / 2, -25);
        d.drawLine(GameFlow.WIDTH / 2 - 30, 125, GameFlow.WIDTH / 2 - 150, 125);
        d.drawLine(GameFlow.WIDTH / 2 + 30, 125, GameFlow.WIDTH / 2 + 150, 125);
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
