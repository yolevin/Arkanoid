import biuoop.DrawSurface;
import java.util.List;
import java.util.ArrayList;

/**
 * The class 'SpriteCollection' describes a collection of sprites that appear in the game.
 * @author Yotam Levin
 * ID: 313248916
 */
public class SpriteCollection {
    private final List<Sprite> spriteList;

    /**
     * Constructor method.
     * @param spriteList the game's sprites list.
     */
    public SpriteCollection(ArrayList<Sprite> spriteList) {
        this.spriteList = spriteList;
    }

    /**
     * Constructor method - initializes an empty list of sprites.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * Adds a sprite to the collection.
     * @param s the new sprite to add.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * removes a sprite from the list of sprites.
     * @param s the sprite to be removed from the game.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * Notifies other objects that time has passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesL = new ArrayList<>(this.spriteList);
        for (Sprite sprite : spritesL) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all sprites in the game.
     * @param d the surface to draw the sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spritesL = new ArrayList<>(this.spriteList);
        for (Sprite sprite : spritesL) {
            sprite.drawOn(d);
        }
    }
}