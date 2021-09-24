import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The class 'EndScreen' describes the end screen of the game.
 * @author Yotam Levin
 * ID: 313248916
 */
public class EndScreen implements Animation {
    private final KeyboardSensor keyboard;
    private final boolean didWin;
    private boolean stop;
    private final int score;

    /**
     * Constructor method.
     * @param k the keyboard sensor for the game.
     * @param didWin an indicator to decide what message to print, win or lost.
     * @param score the game's score.
     */
    public EndScreen(KeyboardSensor k, boolean didWin, int score) {
        this.keyboard = k;
        this.stop = false;
        this.didWin = didWin;
        this.score = score;
    }

    /**
     * Displays a pause message.
     * @param d the surface to draw the message on.
     */
    public void doOneFrame(DrawSurface d) {
        String s = "";
        if (this.didWin) {
            this.drawWinBG(d);
            d.setColor(Color.WHITE);
            s = "You Win!";
        } else {
            this.drawLoseBG(d);
            d.setColor(Color.WHITE);
            s = "Game Over!";
        }
        d.drawText(d.getWidth() / 3, d.getHeight() / 3, s, 50);
        d.drawText(d.getWidth() / 4, 2 * d.getHeight() / 3, "Your score is " + this.score, 50);
    }

    /**
     * Decides if the game should be stopped.
     * @return True if the game should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Draws the background for the win screen.
     * @param d the surface to draw the background on.
     */
    public void drawWinBG(DrawSurface d) {
        int i = 0;
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, GameFlow.WIDTH, GameFlow.HEIGHT);

        d.setColor(Color.YELLOW);
        i = 150;
        while (i > 0) {
            d.drawCircle(650, 150, i);
            i = i - 20;
        }
        d.setColor(Color.BLUE);
        i = 110;
        while (i > 0) {
            d.drawCircle(120, 500, i);
            i = i - 20;
        }
        d.setColor(Color.RED);
        i = 170;
        while (i > 0) {
            d.drawCircle(290, 215, i);
            i = i - 20;
        }
        d.setColor(Color.PINK);
        i = 130;
        while (i > 0) {
            d.drawCircle(365, 390, i);
            i = i - 20;
        }
        d.setColor(Color.GREEN);
        i = 190;
        while (i > 0) {
            d.drawCircle(600, 450, i);
            i = i - 20;
        }
    }

    /**
     * Draws the background for the game over screen.
     * @param d the surface to draw the background on.
     */
    public void drawLoseBG(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, GameFlow.WIDTH, GameFlow.HEIGHT);

        d.setColor(Color.WHITE);
        int c = 5;
        int w = GameFlow.WIDTH - 100;
        int h = GameFlow.HEIGHT - 100;
        int i = 50;
        int j = 50;
        while (c > 0) {
            d.drawRectangle(i, j, w, h);
            w = w - 40;
            h = h - 30;
            i = i + 20;
            j = j + 15;
            c = c - 1;
        }

    }
}