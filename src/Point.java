/**
 * The class 'Point' describes a point object.
 * @author Yotam Levin
 * ID: 313248916
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * Constructor method.
     * @param x x value of point.
     * @param y y value of point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculate the distance between two points.
     * @param other a point to measure the distance with.
     * @return return the distance of this point to the another point.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
    }

    /**
     * checks if two points are equal.
     * @param other a point to check equality with.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {

        // For precision
        double epsilon = Math.pow(10, -5);
        if (other == this) {
            return true;
        }
        return Math.abs(this.x - other.getX()) <= epsilon && Math.abs(this.y - other.getY()) <= epsilon;
    }

    /**
     * Get method - x value of point.
     * @return x value of point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Get method - y value of point.
     * @return y value of point.
     */
    public double getY() {
        return this.y;
    }
}