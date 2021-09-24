import java.util.List;

/**
 * The class 'Line' describes a line object.
 * @author Yotam Levin
 * ID: 313248916
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * Constructor method.
     * @param start starting point of line.
     * @param end ending point of line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor method.
     * @param x1 x value of starting point of line.
     * @param y1 x value of starting point of line.
     * @param x2 x value of ending point of line.
     * @param y2 x value of ending point of line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Calculate the length of the line.
     * @return length of the line.
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Calculate the middle point of the line.
     * @return middle point of the line.
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * Get method - start point of the line.
     * @return start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Get method - end point of the line.
     * @return end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Calculate the slope of the line.
     * @return slope of the line.
     */
    public double slope() {
        if (this.start().getX() == this.end().getX()) {
            return Double.POSITIVE_INFINITY;
        }
        return (this.start.getY() - this.end().getY()) / (this.start().getX() - this.end().getX());
    }

    /**
     * Calculate the 'b' in the line's equation.
     * @return 'b' in the line's equation.
     */
    public double equation() {
        if (this.slope() == Double.POSITIVE_INFINITY) {
            return this.start.getX();
        }
        return (this.slope() * (-1) * this.start().getX() + this.start().getY());
    }

    /**
     * Checks if two lines are intersecting.
     * @param other a line to check intersection with.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        double interX;
        double interY;

        // If lines are parallel
        if (this.slope() == other.slope()) {
            // If lines are vertical
            if (this.slope() == Double.POSITIVE_INFINITY) {
                if (this.equation() != other.equation()) {
                    return false;
                } else if ((Math.min(this.start().getY(), this.end().getY())
                        > Math.max(other.start().getY(), other.end().getY())
                        || (Math.max(this.start().getY(), this.end().getY())
                        < Math.min(other.start().getY(), other.end().getY())))) {
                    return false;
                }

                // Otherwise - not vertical
            } else {
                if (this.equation() != other.equation()) {
                    return false;
                } else if ((Math.min(this.start().getX(), this.end().getX())
                        > Math.max(other.start().getX(), other.end().getX())
                        || (Math.max(this.start().getX(), this.end().getX())
                        < Math.min(other.start().getX(), other.end().getX())))) {
                    return false;
                }
            }
        } else {
            // If one line is vertical
            if (this.slope() == Double.POSITIVE_INFINITY) {
                interX = this.equation();
                interY = other.slope() * interX + other.equation();
                if ((interY > Math.max(this.start.getY(), this.end.getY())
                        || interY < Math.min(this.start.getY(), this.end.getY()))
                        || (interX > Math.max(other.start.getX(), other.end.getX())
                        || interX < Math.min(other.start.getX(), other.end.getX()))) {
                    return false;
                }
            } else if (other.slope() == Double.POSITIVE_INFINITY) {
                interX = other.equation();
                interY = this.slope() * interX + this.equation();
                if ((interY > Math.max(other.start().getY(), other.end().getY())
                        || interY < Math.min(other.start().getY(), other.end().getY()))
                        || (interX > Math.max(this.start.getX(), this.end.getX())
                        || interX < Math.min(this.start.getX(), this.end.getX()))) {
                    return false;
                }

                // if none of the lines are vertical
            } else {
                interX = ((-1) * this.equation() + other.equation()) / (this.slope() - other.slope());
            }
            if (interX > Math.max(this.start.getX(), this.end.getX())
                    || interX < Math.min(this.start.getX(), this.end.getX())
                    || (interX > Math.max(other.start().getX(), other.end().getX())
                    || interX < Math.min(other.start().getX(), other.end().getX()))) {
                return false;
            }
        }
        return true;
    }

    /**
     * finds the intersection point between two lines.
     * @param other a line to check intersection with.
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double x;
        double y;
        if (!(this.isIntersecting(other))) {
            return null;
        }

        // If lines are parallel
        if (this.slope() == other.slope()) {

            // Check if lines are vertical
            if (this.slope() == Double.POSITIVE_INFINITY) {

                // If lines are uniting
                if ((Math.min(this.start().getY(), this.end().getY())
                    < Math.max(other.start().getY(), other.end().getY()))
                    && (Math.max(this.start().getY(), this.end().getY())
                    > Math.min(other.start().getY(), other.end().getY()))) {
                return null;

                // Lines have one intersection point
                } else if (Math.min(this.start().getY(), this.end().getY())
                        == Math.max(other.start().getY(), other.end().getY())) {
                    if (this.start().getY() < this.end().getY()) {
                        return this.start();
                    } else {
                        return this.end();
                    }
                } else {
                    if (this.start().getY() > this.end().getY()) {
                        return this.start();
                    } else {
                        return this.end();
                    }
                }

                // Otherwise - not vertical
            } else {

                // If lines are uniting
                if ((Math.min(this.start().getX(), this.end().getX())
                    < Math.max(other.start().getX(), other.end().getX()))
                    && (Math.max(this.start().getX(), this.end().getX())
                    > Math.min(other.start().getX(), other.end().getX()))) {
                return null;

                    // Lines have one intersection point
                } else if (Math.min(this.start().getX(), this.end().getX())
                        == Math.max(other.start().getX(), other.end().getX())) {
                    if (this.start().getX() < this.end().getX()) {
                        return this.start();
                    } else {
                        return this.end();
                    }
                } else {
                    if (this.start().getX() > this.end().getX()) {
                        return this.start();
                    } else {
                        return this.end();
                    }
                }
            }
        } else {

            // If one line is vertical
            if (this.slope() == Double.POSITIVE_INFINITY) {
                x = this.equation();
                y = other.slope() * x + other.equation();
            } else if (other.slope() == Double.POSITIVE_INFINITY) {
                x = other.equation();
                y = this.slope() * x + this.equation();

                // If none of the lines are vertical
            } else {
                x = ((-1) * this.equation() + other.equation()) / (this.slope() - other.slope());
                y = this.slope() * x + this.equation();
            }
            return new Point(x, y);
        }
    }

    /**
     * Checks how many intersections happen between this line and a rectangle.
     * @param rect a rectangle to count intersections with.
     * @return 0 if there are no intersection points, 1 if there is only one, 2 if there are exactly two,
     * and 3 if there are 3 or more points (the line is joined with one of the rectangle's edges.
     */
    public int isIntersectingWithRectangle(Rectangle rect) {
        Point p1 = null;
        Point p2 = null;
        Point p3 = null;
        Point p4 = null;

        // If not intersecting at all
        if (!this.isIntersecting(rect.upperEdge()) && !this.isIntersecting(rect.bottomEdge())
            && !this.isIntersecting(rect.leftEdge()) && !this.isIntersecting(rect.rightEdge())) {
            return 0; // no intersections
        }


        // If joining with one of the edges
        if ((this.isIntersecting(rect.upperEdge()) && this.intersectionWith(rect.upperEdge()) == null)
            || (this.isIntersecting(rect.bottomEdge()) && this.intersectionWith(rect.bottomEdge()) == null)
            || (this.isIntersecting(rect.leftEdge()) && this.intersectionWith(rect.leftEdge()) == null)
            || (this.isIntersecting(rect.rightEdge()) && this.intersectionWith(rect.rightEdge()) == null)) {
            return 3; // joining with one of the edges
        }

        p1 = this.intersectionWith(rect.upperEdge());
        p2 = this.intersectionWith(rect.bottomEdge());
        p3 = this.intersectionWith(rect.leftEdge());
        p4 = this.intersectionWith(rect.rightEdge());


        // Check upper edge
        if (p1 != null) {
            if (p2 != null) {
                return 2; // two intersection points
            }
            if (p3 != null) {
                if (p1.equals(p3)) {
                    return 1; // one intersection point in the corner
                } else {
                    return 2; // two intersection points
                }
            }
            if (p4 != null) {
                if (p1.equals(p4)) {
                    return 1; // one intersection point in the corner
                } else {
                    return 2; // two intersection points
                }
            }
            return 1; // one intersection
        }

        // Check bottom edge
        if (p2 != null) {
            if (p3 != null) {
                if (p2.equals(p3)) {
                    return 1; // one intersection point in the corner
                } else {
                    return 2; // two intersection points
                }
            }
            if (p4 != null) {
                if (p2.equals(p4)) {
                    return 1; // one intersection point in the corner
                } else {
                    return 2; // two intersection points
                }
            }
            return 1; // one intersection
        }

        // Check left edge
        if (p3 != null) {
            if (p4 != null) {
                return 2; // two intersection points
            }
            return 1; // one intersection
        }

        // Check right edge
        if (p4 != null) {
            return 1; // one intersection
        }
        return 0;
    }

    /**
     * finds closest intersection point to this line's start, from a list of points.
     * @param rect the rectangle to check collisions with.
     * @return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = rect.intersectionPoints(this);

        if (points.isEmpty()) {
            return null;
        } else {
            Point currentP = points.get(0);
            Point newP = null;
            for (int i = 1; i < points.size(); i++) {
                newP = points.get(i);
                if (currentP.distance(this.start()) > newP.distance(this.start())) {
                    currentP = newP;
                }
            }
            return currentP;
        }
    }

    /**
     * checks if a given point is on this line.
     * @param p a point to check.
     * @return true if the point is on the line, false otherwise.
     */
    public boolean isPointOnLine(Point p) {
        if (p.distance(this.start()) + p.distance(this.end()) == this.start().distance(this.end())) {
            return true;
        }
        return false;
    }

    /**
     * checks if two lines are equal.
     * @param other a line to check equality with.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {

        if (other == this) {
            return true;
        }
        return (this.start().equals(other.start()) && this.end().equals(other.end()));
    }
}