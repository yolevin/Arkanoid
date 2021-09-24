import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The class 'FinalFourBG' is in charge of the background of the level "Final Four".
 * @author Yotam Levin
 * ID: 313248916
 */
public class FinalFourBG implements Sprite {

    /**
     * Constructor method.
     */
    public FinalFourBG() {
    }

    /**
     * Draws the background.
     * @param d the surface to draw the background on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.CYAN);
        d.fillRectangle(0, 0, GameFlow.WIDTH, GameFlow.HEIGHT);

        Color color = Color.LIGHT_GRAY;
        d.setColor(color);
        int x = 100;
        int y = 350;
        int lineX = x;
        int lineY = y;
        for (int i = 0; i < 2; i++) {
            color = Color.WHITE;
            d.setColor(color);
            for (int k = 0; k < 10; k++) {
                d.drawLine(lineX, lineY, lineX - 30, GameFlow.HEIGHT);
                lineX = lineX + 10;
            }

            color = Color.LIGHT_GRAY;
            d.setColor(color);
            for (int j = 0; j < 5; j++) {
                d.fillCircle(x, y, 30);
                x = x + 20;
                if (j % 2 == 0) {
                    y = y + 35;
                } else {
                    y = y - 30;
                }
                color = color.darker();
                d.setColor(color);
            }
            x = 550;
            y = 450;
            lineX = x;
            lineY = y;
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
