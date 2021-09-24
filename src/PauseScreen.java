import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The class 'PauseScreen' describes a pause screen.
 * @author Yotam Levin
 * ID: 313248916
 */
public class PauseScreen implements Animation {
    private final KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor method.
     * @param k the keyboard sensor for the game.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * Displays a pause message.
     * @param d the surface to draw the message on.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / 5, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * Decides if the game should be stopped.
     * @return True if the game should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}