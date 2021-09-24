/**
 * The class 'CollisionInfo' describes information about a collision made between two objects.
 * @author Yotam Levin
 * ID: 313248916
 */
public class CollisionInfo {
    private Collidable col;
    private Line line;

    /**
     * Constructor method.
     * @param col the object that was hit.
     * @param line the movement vector of the object that collided with the collidable.
     */
    public CollisionInfo(Collidable col, Line line) {
        this.col = col;
        this.line = line;
    }

    /**
     * Finds the point at which the collision occurs.
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.line.closestIntersectionToStartOfLine(this.col.getCollisionRectangle());
    }

    /**
     * Get method - the collidable object involved in the collision.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.col;
    }
}