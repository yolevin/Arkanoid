/**
 * The interface 'Collidable' defines methods used on collidable objects in the game.
 * @author Yotam Levin
 * ID: 313248916
 */
public interface Collidable {

    /**
     * Get method - rectangle shape of the object.
     * @return the rectangle shape of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param hitter the ball that hit this object.
     * @param collisionPoint the point of the collision.
     * @param currentVelocity the current velocity that the hitting object has.
     * @return the new velocity that was calculated based on the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}