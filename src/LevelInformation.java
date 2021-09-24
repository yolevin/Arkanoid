import java.util.List;

/**
 * The interface 'LevelInformation' specifies the information required to fully describe a level.
 * @author Yotam Levin
 * ID: 313248916
 */
public interface LevelInformation {

    /**
     * Returns the number of balls in the level.
     * @return number of balls in the level.
     */
    int numberOfBalls();

    /**
     * Returns the balls' different velocities.
     * @return a list containing balls' velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the level's paddle speed.
     * @return the paddle's speed.
     */
    int paddleSpeed();

    /**
     * Returns the level's paddle width.
     * @return the paddle's width.
     */
    int paddleWidth();

    /**
     * Returns the level's name.
     * @return the level's name.
     */
    String levelName();

    /**
     * Returns the level's background.
     * @return the level's background.
     */
    Sprite getBackground();

    /**
     * Returns the level's different blocks.
     * @return a list containing the level's blocks.
     */
    List<Block> blocks();

    /**
     * Returns the number of blocks that should be removed before the level is considered to be "cleared".
     * @return a the number of blocks to remove.
     */
    int numberOfBlocksToRemove();
}