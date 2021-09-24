/**
 * The class 'Velocity' describes a velocity object.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 * @author Yotam Levin
 * ID: 313248916
 */
public class Velocity {
    private final double dx;
    private final double dy;

    /**
     * Constructor method.
     * @param dx the change in position on the x axis.
     * @param dy the change in position on the y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Get method - change in position on the x axis.
     * @return change in position on the x axis.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Get method - change in position on the y axis.
     * @return change in position on the y axis.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p a point to change.
     * @return a new point with the new values after the addition.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * calculate velocity based on angle and amount of units to advance.
     * @param angle the direction for an object to go in
     * @param speed the amount of units for an object to advance
     * @return a new velocity calculated by angle and amount of units to advance.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos((angle + 270) * Math.PI / 180) * speed;
        double dy = Math.sin((angle + 270) * Math.PI / 180) * speed;
        return new Velocity(dx, dy);
    }
}