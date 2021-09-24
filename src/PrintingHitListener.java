/**
 * The class 'PrintingHitListener' is in charge of printing to the console whenever a hit occurred.
 * @author Yotam Levin
 * ID: 313248916
 */
public class PrintingHitListener implements HitListener {

    /**
     * This method prints to the console whenever a hit occurred.
     * @param beingHit the block that was hit.
     * @param hitter the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}