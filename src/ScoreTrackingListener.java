/**
 * The class 'scoreTrackingListener' is in charge of keeping score during a game.
 * @author Yotam Levin
 * ID: 313248916
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor method.
     * @param scoreCounter the counter to keep score with.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method adds score to the score counter.
     * @param beingHit the block that was hit.
     * @param hitter the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
    }
}