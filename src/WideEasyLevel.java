import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.util.Random;

/**
 * The class 'WideEasyLevel' describes the level called "WideEasy".
 * @author Yotam Levin
 * ID: 313248916
 */
public class WideEasyLevel implements LevelInformation {

    /**
     * Returns the number of balls in the level.
     * @return number of balls in the level.
     */
    @Override
    public int numberOfBalls() {
        return 10;
    }

    /**
     * Returns the balls' different velocities.
     * @return a list containing balls' velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<>();
        int speed = 5;
        int angle = -68;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            ballVelocities.add(Velocity.fromAngleAndSpeed(angle, speed));
            angle = angle + 15;
        }
        return ballVelocities;
    }

    /**
     * Returns the level's paddle speed.
     * @return the paddle's speed.
     */
    @Override
    public int paddleSpeed() {
        return 3;
    }

    /**
     * Returns the level's paddle width.
     * @return the paddle's width.
     */
    @Override
    public int paddleWidth() {
        return GameFlow.WIDTH - 100;
    }

    /**
     * Returns the level's name.
     * @return the level's name.
     */
    @Override
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * Returns the level's background.
     * @return the level's background.
     */
    @Override
    public Sprite getBackground() {
        return new WideEasyBG();
    }

    /**
     * Returns the level's different blocks.
     * @return a list containing the level's blocks.
     */
    @Override
    public List<Block> blocks() {
        // Game blocks
        int x = -(GameFlow.BORDER_SIZE);
        int y = (int) (GameFlow.HEIGHT / 2.5);
        int flag = 1;
        Random rand = new Random();
        Color color = new Color(200, 200, 230);
        Block block = null;
        List<Block> blockList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            x = x + 50;
            if (flag == 1 && i != 8) {

//                // To generate random color
//                float r = rand.nextFloat();
//                float g = rand.nextFloat();
//                float b = rand.nextFloat();
//                color = new Color(r, g, b);
                color = new Color(color.getRed() - 25, color.getGreen() - 25, color.getBlue());
                flag = 2;
            } else {
                flag = 1;
            }
            block = new Block(new Rectangle(new Point(x, y), 50, 25), color);
            blockList.add(block);
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
