import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * The class 'CountdownAnimation' describes the countdown at the beginning of each level.
 * @author Yotam Levin
 * ID: 313248916
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private final int countFrom;
    private final SpriteCollection gameScreen;
    private boolean stop;
    private final Counter counter;

    /**
     * Constructor method.
     * @param numOfSeconds the number of seconds that the countdown lasts.
     * @param countFrom which number to count to 1 from.
     * @param gameScreen the current level's screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.counter = new Counter(countFrom);
    }

    /**
     * Counts down for a couple of seconds before the level starts.
     * @param d the surface to draw the message on.
     */
    public void doOneFrame(DrawSurface d) {

        // Draw the initial placement of sprites.
        this.gameScreen.drawAllOn(d);
        Sleeper sleeper = new Sleeper();

        // The string to print to the screen.
        String countDown = this.counter.getValue() + "...";
        d.setColor(Color.BLACK);
        d.fillRectangle(0, (GameFlow.HEIGHT / 2) - 35, GameFlow.WIDTH, 70);
        d.setColor(Color.WHITE);
        d.drawText((d.getWidth() / 2) - 10, (d.getHeight() / 2) + 25, countDown, 60);
        if (this.counter.getValue() != this.countFrom) {
            sleeper.sleepFor((long) (this.numOfSeconds / this.countFrom * 1000));
        }
        this.counter.decrease(1);
        if (this.counter.getValue() == -1) {
            this.stop = true;
        }
    }

    /**
     * Decides if the game should be stopped.
     * @return True if the game should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}