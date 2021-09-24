import biuoop.GUI;
import java.util.ArrayList;

/**
 * The class 'GameFlow' is in charge of shifting between levels smoothly.
 * @author Yotam Levin
 * ID: 313248916
 */
public class GameFlow {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int BORDER_SIZE = 25;
    private final AnimationRunner runner;
    private final GUI gui;
    private final Counter score;
    private final Counter lives;

    /**
     * Constructor method.
     */
    public GameFlow() {
        this.gui = new biuoop.GUI("Araknoid", WIDTH, HEIGHT);
        this.runner = new AnimationRunner(this.gui);
        this.score = new Counter();
        this.lives = new Counter(3);
    }

    /**
     * Iterates through the levels one by one.
     * @param levels the list of levels to play through.
     */
    public void runLevels(ArrayList<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(new SpriteCollection(), new GameEnvironment(), this.gui, this.score,
                    this.lives, this.runner, levelInfo);
            level.initialize();
            while (this.lives.getValue() != 0 && level.getNumOfBlocks() != 0) {
                level.run();
            }
            if (this.lives.getValue() == 0) {
                break;
            }
        }
        this.runner.run(new KeyPressStoppableAnimation(this.gui.getKeyboardSensor(),
                this.gui.getKeyboardSensor().SPACE_KEY, new EndScreen(this.gui.getKeyboardSensor(),
                !(this.lives.getValue() == 0), score.getValue())));
        this.gui.close();
    }
}