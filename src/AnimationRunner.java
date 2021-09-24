import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * The class 'AnimationRunner' is in charge of implementing the animation.
 * @author Yotam Levin
 * ID: 313248916
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;

    /**
     * Constructor method.
     * @param gui the gui for the game.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * Constructor method.
     */
    public AnimationRunner() {
        this.gui = new GUI("Arkanoid", 800, 600);
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * Runs the game: start the animation loop.
     * @param animation the animation for this game.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}