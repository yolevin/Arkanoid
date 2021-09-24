import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The class 'Green3' is in charge of the background of the level "Green 3".
 * @author Yotam Levin
 * ID: 313248916
 */
public class Green3BG implements Sprite {

    /**
     * Constructor method.
     */
    public Green3BG() {
    }

    /**
     * Draws the background.
     * @param d the surface to draw the background on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GREEN.darker().darker());
        d.fillRectangle(0, 0, GameFlow.WIDTH, GameFlow.HEIGHT);
        d.setColor(Color.YELLOW);
        d.fillCircle(150, 200, 15);
        d.setColor(Color.ORANGE);
        d.fillCircle(150, 200, 10);
        d.setColor(Color.WHITE);
        d.fillCircle(150, 200, 5);

        d.setColor(Color.DARK_GRAY.brighter());
        d.fillRectangle(145, 215, 10, 130);
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(135, 345, 30, 60);
        d.setColor(Color.DARK_GRAY.darker());
        d.fillRectangle(100, 405, 100, 200);

        d.setColor(Color.WHITE);
        int x;
        int y = 410;
        for (int i = 0; i < 5; i++) {
            x = 105;
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(x, y, 10, 30);
                x = x + 20;
            }
            y = y + 40;
        }
    }

    /**
     * defines what the background has to do when time passes, nothing.
     */
    @Override
    public void timePassed() {

    }

    /**
     * adds the background to the game - adds it to the sprites list.
     * @param game the current game that is run.
     */
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
