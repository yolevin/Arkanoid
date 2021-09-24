import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The class 'KeyPressStoppableAnimation' describes animations that use a key press.
 * @author Yotam Levin
 * ID: 313248916
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor method.
     * @param sensor the keyboard sensor of the game.
     * @param key a key to press on.
     * @param animation the animation to affect.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * Perform the actions necessary in a single frame.
     * @param d the surface to draw the game on.
     */
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        }
        this.isAlreadyPressed = false;
    }

    /**
     * Decides if the game should be stopped.
     * @return True if the game should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}