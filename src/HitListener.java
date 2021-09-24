/**
 * The interface 'HitListener' defines methods for listeners to hit occurrences.
 * @author Yotam Levin
 * ID: 313248916
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit. The hitter parameter is the Ball
     * that's doing the hitting.
     * @param beingHit the block that was hit.
     * @param hitter the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}