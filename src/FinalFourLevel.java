import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.util.Random;

/**
 * The class 'FinalFourLevel' describes the level called "Final Four".
 * @author Yotam Levin
 * ID: 313248916
 */
public class FinalFourLevel implements LevelInformation {

    /**
     * Returns the number of balls in the level.
     * @return number of balls in the level.
     */
    @Override
    public int numberOfBalls() {
        return 3;
    }

    /**
     * Returns the balls' different velocities.
     * @return a list containing balls' velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<>();
        int speed = 5;
        int angle = -45;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            ballVelocities.add(Velocity.fromAngleAndSpeed(angle, speed));
            angle = angle + 45;
        }
        return ballVelocities;
    }

    /**
     * Returns the level's paddle speed.
     * @return the paddle's speed.
     */
    @Override
    public int paddleSpeed() {
        return 5;
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
        return "Final Four";
    }

    /**
     * Returns the level's background.
     * @return the level's background.
     */
    @Override
    public Sprite getBackground() {
        return new FinalFourBG();
    }

    /**
     * Returns the level's different blocks.
     * @return a list containing the level's blocks.
     */
    @Override
    public List<Block> blocks() {
        int x;
        int y;
        Random rand = new Random();
        List<Block> blockList = new ArrayList<>();

        // Game blocks
        y = (GameFlow.HEIGHT / 2) - 50;
        Block block = null;
        Color color = Color.RED.brighter();
        for (int i = 0; i < 7; i++) {
            x = GameFlow.WIDTH - GameFlow.BORDER_SIZE;

//            // To generate random color
//            float r = rand.nextFloat();
//            float g = rand.nextFloat();
//            float b = rand.nextFloat();
//            Color color = new Color(r, g, b);
            for (int j = 0; j < 15; j++) {
                x = x - 50;
                block = new Block(new Rectangle(new Point(x, y), 50, 25), color);
                blockList.add(block);
            }
            color = color.darker();
            y = y - 25;
        }
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
