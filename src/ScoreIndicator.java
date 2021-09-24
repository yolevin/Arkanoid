import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The class 'scoreIndicator' is in charge of displaying the score during a game.
 * @author Yotam Levin
 * ID: 313248916
 */
public class ScoreIndicator implements Sprite {
    private final Counter scoreCounter;
    private final Rectangle location;

    /**
     * Constructor method.
     * @param scoreCounter the counter to keep score with.
     * @param location the location where the score will be displayed.
     */
    public ScoreIndicator(Counter scoreCounter, Rectangle location) {
        this.scoreCounter = scoreCounter;
        this.location = location;
    }

    /**
     * Draws the score indicator on the given surface.
     * @param d the surface to draw the score indicator on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        int textXLocation = (int) (this.location.getWidth() / 2) - 30;
        int textYLocation = (int) this.location.getUpperLeft().getY() + 15;
        d.drawText(textXLocation, textYLocation, "Score: " + this.scoreCounter.getValue(), 15);
    }

    /**
     * defines what the score indicator has to do when time passes, nothing.
     */
    @Override
    public void timePassed() {

    }

    /**
     * adds the score indicator to the game - adds it to the sprites list.
     * @param game the current game that is run.
     */
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
