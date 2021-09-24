import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The class 'LivesIndicator' is in charge of displaying the name of a level during a game.
 * @author Yotam Levin
 * ID: 313248916
 */
public class LivesIndicator implements Sprite {
    private final Counter lives;
    private final Rectangle location;

    /**
     * Constructor method.
     * @param lives the amount of lives left for the game.
     * @param location the location where the name will be displayed.
     */
    public LivesIndicator(Counter lives, Rectangle location) {
        this.lives = lives;
        this.location = location;
    }

    /**
     * Draws the name indicator on the given surface.
     * @param d the surface to draw the name indicator on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        int textXLocation = (int) (this.location.getWidth() / 2) - 300;
        int textYLocation = (int) this.location.getUpperLeft().getY() + 15;
        d.drawText(textXLocation, textYLocation, "Lives: " + this.lives.getValue(), 15);
    }

    /**
     * defines what the name indicator has to do when time passes, nothing.
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
