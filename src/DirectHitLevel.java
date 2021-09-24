import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * The class 'DirectHitLevel' describes the level called "Direct Hit".
 * @author Yotam Levin
 * ID: 313248916
 */
public class DirectHitLevel implements LevelInformation {

    /**
     * Returns the number of balls in the level.
     * @return number of balls in the level.
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * Returns the balls' different velocities.
     * @return a list containing balls' velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<>();
        ballVelocities.add(Velocity.fromAngleAndSpeed(0, 5));
        return ballVelocities;
    }

    /**
     * Returns the level's paddle speed.
     * @return the paddle's speed.
     */
    @Override
    public int paddleSpeed() {
        return 10;
    }

    /**
     * Returns the level's paddle width.
     * @return the paddle's width.
     */
    @Override
    public int paddleWidth() {
        return 100;
    }

    /**
     * Returns the level's name.
     * @return the level's name.
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Returns the level's background.
     * @return the level's background.
     */
    @Override
    public Sprite getBackground() {
        return new DirectHitBG();
    }

    /**
     * Returns the level's different blocks.
     * @return a list containing the level's blocks.
     */
    @Override
    public List<Block> blocks() {
        int x = (GameFlow.WIDTH / 2) - 25;
        int y = 100;
        Block block = new Block(new Rectangle(new Point(x, y), 50, 50), Color.RED);
        List<Block> blockList = new ArrayList<>();
        blockList.add(block);
        return blockList;
    }

    /**
     * Returns the number of blocks that should be removed before the level is considered to be "cleared".
     * @return a the number of blocks to remove.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
