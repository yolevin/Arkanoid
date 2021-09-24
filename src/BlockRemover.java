/**
 * The class 'BlockRemover' is in charge of removing blocks from the game, as well as keeping count of the
 * number of blocks that remain.
 * @author Yotam Levin
 * ID: 313248916
 */
public class BlockRemover implements HitListener {
    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * Constructor method.
     * @param game the current game that is run.
     * @param removedBlocks the number of blocks that remain in the game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Removes blocks that were hit from the game.
     * @param beingHit the block that was hit.
     * @param hitter the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}