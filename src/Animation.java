import biuoop.DrawSurface;

/**
 * The interface 'Animation' defines methods that affect the animation of the game.
 * @author Yotam Levin
 * ID: 313248916
 */
public interface Animation {

    /**
     * Perform the actions necessary in a single frame.
     * @param d the surface to draw the game on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Decides if the game should be stopped.
     * @return True if the game should stop, false otherwise.
     */
    boolean shouldStop();
}