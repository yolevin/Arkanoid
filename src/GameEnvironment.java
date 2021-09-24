import java.util.ArrayList;
import java.util.List;

/**
 * The class 'GameEnvironment' describes the environment of the game, specifically it's collidables.
 * @author Yotam Levin
 * ID: 313248916
 */
public class GameEnvironment {
    private final List<Collidable> collidables;

    /**
     * Constructor method.
     * @param collidables the list of collidables that appear in the game.
     */
    public GameEnvironment(ArrayList<Collidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * Constructor method - initializes an empty array.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * adds a new collidable to the list of collidables in the game.
     * @param c a new collidable object to be added to the game.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * removes a collidable from the list of collidables in the game.
     * @param c the collidable object to be removed from the game.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Get method - the list of collidables.
     * @return list of collidables in the game.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * finds closest collision between a line (a ball's movement vector) and all the collidables in the game.
     * returns the collision point if one is found, null otherwise.
     * @param trajectory the ball's movement vector.
     * @return information about the closest collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point currentPoint = null;
        Point newPoint = null;
        Collidable col = null;

        // Iterate through the list of collidables
        List<Collidable> collidablesL = new ArrayList<>(this.collidables);
        for (int i = 0; i < collidablesL.size(); i++) {
            newPoint = trajectory.closestIntersectionToStartOfLine(collidablesL.get(i).getCollisionRectangle());
            if (newPoint != null) {
                if (currentPoint == null) {
                    currentPoint = newPoint;
                    col = collidablesL.get(i);
                } else {
                    if (currentPoint.distance(trajectory.start()) > newPoint.distance(trajectory.start())) {
                        currentPoint = newPoint;
                        col = collidablesL.get(i);
                    }
                }
            }
        }

        // If no collision points were returned
        if (currentPoint == null) {
            return null;
        }
        return new CollisionInfo(col, trajectory);
    }
}