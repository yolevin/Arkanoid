import java.util.ArrayList;

/**
 * The class 'Rectangle' describes a rectangle object.
 * @author Yotam Levin
 * ID: 313248916
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Constructor method.
     * @param upperLeft the upper left point of the rectangle.
     * @param width the rectangle's width.
     * @param height the rectangle's height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line a line to check intersection points with.
     * @return a list of intersection points between a line and this rectangle.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Point tempP = null;
        ArrayList<Point> pl = new ArrayList<Point>();
        if (line.isIntersectingWithRectangle(this) == 0) {
            return pl;
        }
        if (line.isIntersectingWithRectangle(this) == 1) {
            if (line.isIntersecting(this.upperEdge())) {
                pl.add(line.intersectionWith(this.upperEdge()));
            } else if (line.isIntersecting(this.bottomEdge())) {
                pl.add(line.intersectionWith(this.bottomEdge()));
            } else if (line.isIntersecting(this.leftEdge())) {
                pl.add(line.intersectionWith(this.leftEdge()));
            } else {
                pl.add(line.intersectionWith(this.rightEdge()));
            }
            return pl;
        }
        if (line.isIntersectingWithRectangle(this) == 2) {
            if (line.isIntersecting(this.upperEdge())) {
                pl.add(line.intersectionWith(this.upperEdge()));
            }
            if (line.isIntersecting(this.bottomEdge())) {
                pl.add(line.intersectionWith(this.bottomEdge()));
            }
            if (line.isIntersecting(this.leftEdge())) {

                // Make sure we don't add a point twice (ex. if it's a corner)
                if (pl.size() < 2) {
                    pl.add(line.intersectionWith(this.leftEdge()));
                }
            }
            if (line.isIntersecting(this.rightEdge())) {

                // Make sure we don't add a point twice (ex. if it's a corner)
                if (pl.size() < 2) {
                    pl.add(line.intersectionWith(this.rightEdge()));
                }
            }
            return pl;
        }
        if (line.isIntersectingWithRectangle(this) == 3) {
            pl.add(line.start());
            pl.add(line.end());
        }
        return pl;
    }

    /**
     * Get method - width of rectangle.
     * @return width of rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Get method - height of rectangle.
     * @return height of rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Get method - upper-left point of the rectangle.
     * @return upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Calculates the top edge of the rectangle.
     * @return the top edge of the rectangle.
     */
    public Line upperEdge() {
        Point end = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        return new Line(this.upperLeft, end);
    }

    /**
     * Calculates the bottom edge of the rectangle.
     * @return the bottom edge of the rectangle.
     */
    public Line bottomEdge() {
        Point start = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point end = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return new Line(start, end);
    }

    /**
     * Calculates the left edge of the rectangle.
     * @return the left edge of the rectangle.
     */
    public Line leftEdge() {
        Point end = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        return new Line(this.upperLeft, end);
    }

    /**
     * Calculates the right edge of the rectangle.
     * @return the right edge of the rectangle.
     */
    public Line rightEdge() {
        Point start = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point end = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return new Line(start, end);
    }
}