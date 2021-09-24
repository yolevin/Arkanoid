/**
 * The class 'BallRemover' is in charge of removing balls from the game, as well as keeping count of the
 * number of balls that remain.
 * @author Yotam Levin
 * ID: 313248916
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor method.
     * @param game the current game that is run.
     * @param removedBalls the number of balls that remain in the game.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * This method removes a ball that hit the 'death region' from the game.
     * @param beingHit the block that was hit.
     * @param hitter the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}