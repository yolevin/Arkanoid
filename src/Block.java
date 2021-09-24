import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * The class 'Block' describes a block object.
 * @author Yotam Levin
 * ID: 313248916
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle collisionRectangle;
    private final java.awt.Color color;
    private final List<HitListener> hitListeners;

    /**
     * Constructor method.
     * @param collisionRectangle the rectangle shape that the block has.
     */
    public Block(Rectangle collisionRectangle) {
        this.collisionRectangle = collisionRectangle;
        this.color = Color.BLACK;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Constructor method.
     * @param collisionRectangle the rectangle shape that the block has.
     * @param color the block's color.
     */
    public Block(Rectangle collisionRectangle, Color color) {
        this.collisionRectangle = collisionRectangle;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Get method - rectangle shape.
     * @return the rectangle shape of the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    /**
     * Notifies the listeners that a hit has occurred.
     * @param hitter the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {

        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * adds a listener to the list of listeners.
     * @param hl the hit listener to add.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * removes a listener from the list of listeners.
     * @param hl the listener to remove.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * checks where on the block the collision point was and returns the velocity accordingly.
     * @param hitter the ball that hit this object.
     * @param collisionPoint the point of the collision between a ball and this block.
     * @param currentVelocity the current velocity that the ball has.
     * @return the new velocity of the ball after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);

        // If collision point is on the left or right border (not the corners)
        if (this.collisionRectangle.leftEdge().isPointOnLine(collisionPoint)
                || this.collisionRectangle.rightEdge().isPointOnLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy());
        }

        // If collision point is on the top or bottom border (not the corners)
        if (this.collisionRectangle.upperEdge().isPointOnLine(collisionPoint)
                || this.collisionRectangle.bottomEdge().isPointOnLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
        }

        // If collision point is in top right corner
        if (collisionPoint.equals(this.collisionRectangle.upperEdge().end())) {

            // If ball is coming from north east
            if (currentVelocity.getDy() < 0 && currentVelocity.getDx() < 0) {
                return new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy() * (-1));
            }

            // If ball is coming from north west
            if (currentVelocity.getDy() < 0 && currentVelocity.getDx() > 0) {
                return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
            }

            // If ball is coming from south east
            if (currentVelocity.getDy() > 0 && currentVelocity.getDx() < 0) {
                return new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy());
            }

            // If ball is coming directly from above
            if (currentVelocity.getDy() < 0 && currentVelocity.getDx() == 0) {
                return new Velocity(currentVelocity.getDy() * (-1), 0);
            }

            // If ball is coming directly from the right
            if (currentVelocity.getDy() == 0 && currentVelocity.getDx() < 0) {
                return new Velocity(0, currentVelocity.getDx() * (-1));
            }
        }

        // If collision point is in top left corner
        if (collisionPoint.equals(this.collisionRectangle.upperEdge().start())) {

            // If ball is coming from north east
            if (currentVelocity.getDy() < 0 && currentVelocity.getDx() < 0) {
                return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
            }

            // If ball is coming from north west
            if (currentVelocity.getDy() < 0 && currentVelocity.getDx() > 0) {
                return new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy() * (-1));
            }

            // If ball is coming from south west
            if (currentVelocity.getDy() > 0 && currentVelocity.getDx() > 0) {
                return new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy());
            }

            // If ball is coming directly from above
            if (currentVelocity.getDy() < 0 && currentVelocity.getDx() == 0) {
                return new Velocity(currentVelocity.getDy(), 0);
            }

            // If ball is coming directly from the left
            if (currentVelocity.getDy() == 0 && currentVelocity.getDx() > 0) {
                return new Velocity(0, currentVelocity.getDx());
            }
        }

        // If collision point is in bottom right corner
        if (collisionPoint.equals(this.collisionRectangle.bottomEdge().end())) {

            // If ball is coming from north east
            if (currentVelocity.getDy() < 0 && currentVelocity.getDx() < 0) {
                return new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy());
            }

            // If ball is coming from south east
            if (currentVelocity.getDy() > 0 && currentVelocity.getDx() < 0) {
                return new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy() * (-1));
            }

            // If ball is coming from south west
            if (currentVelocity.getDy() > 0 && currentVelocity.getDx() > 0) {
                return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
            }

            // If ball is coming directly from below
            if (currentVelocity.getDy() > 0 && currentVelocity.getDx() == 0) {
                return new Velocity(currentVelocity.getDy(), 0);
            }

            // If ball is coming directly from the right
            if (currentVelocity.getDy() == 0 && currentVelocity.getDx() < 0) {
                return new Velocity(0, currentVelocity.getDx());
            }
        }

        // If collision point is in bottom left corner
        if (collisionPoint.equals(this.collisionRectangle.bottomEdge().start())) {

            // If ball is coming from north west
            if (currentVelocity.getDy() < 0 && currentVelocity.getDx() > 0) {
                return new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy());
            }

            // If ball is coming from south east
            if (currentVelocity.getDy() > 0 && currentVelocity.getDx() < 0) {
                return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
            }

            // If ball is coming from south west
            if (currentVelocity.getDy() > 0 && currentVelocity.getDx() > 0) {
                return new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy() * (-1));
            }

            // If ball is coming directly from below
            if (currentVelocity.getDy() > 0 && currentVelocity.getDx() == 0) {
                return new Velocity(currentVelocity.getDy() * (-1), 0);
            }

            // If ball is coming directly from the left
            if (currentVelocity.getDy() == 0 && currentVelocity.getDx() > 0) {
                return new Velocity(0, currentVelocity.getDx() * (-1));
            }
        }
        return currentVelocity;
    }

    /**
     * Draws the block on the given surface.
     * @param surface the surface to draw the ball on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
                (int) this.collisionRectangle.getUpperLeft().getY(),
                (int) this.collisionRectangle.getWidth(),
                (int) this.collisionRectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
                (int) this.collisionRectangle.getUpperLeft().getY(),
                (int) this.collisionRectangle.getWidth(),
                (int) this.collisionRectangle.getHeight());
    }

    /**
     * defines what the block has to do when time passes, nothing.
     */
    @Override
    public void timePassed() {

    }

    /**
     * adds the block to the game - adds it to the collidables and sprites lists.
     * @param game the current game that is run.
     */
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * removes the block from the game - removes it from the collidables and sprites lists.
     * @param game the current game that is run.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }
}
