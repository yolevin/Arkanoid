import biuoop.DrawSurface;

/**
 * The interface 'Sprite' defines methods used on sprites in the game.
 * @author Yotam Levin
 * ID: 313248916
 */
public interface Sprite {

    /**
     * Draws the sprite on the given surface.
     * @param d the surface to draw the sprite on.
     */
    void drawOn(DrawSurface d);

    /**
     * defines what the sprite has to do when time passes.
     */
    void timePassed();

    /**
     * Adds the sprite to the game.
     * @param game the current game that is run.
     */
    void addToGame(GameLevel game);
}