import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The class 'Ball' describes a ball object.
 * @author Yotam Levin
 * ID: 313248916
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private final java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Constructor method.
     * @param center the point where the center of the ball is.
     * @param r radius of the ball.
     * @param color The ball's color.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Constructor method.
     * @param x the point's center's x value.
     * @param y the point's center's y value.
     * @param r radius of the ball.
     * @param color The ball's color.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Get method - x value of ball's center.
     * @return x value of ball's center.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Get method - y value of ball's center.
     * @return y value of ball's center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Get method - radius of the ball.
     * @return radius of the ball.
     */
    public int getSize() {
        return r;
    }

    /**
     * Get method - ball's color.
     * @return color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Get method - ball's velocity.
     * @return velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Set method - sets the ball's center point.
     * @param newCenter a new center for the ball.
     */
    public void setCenter(Point newCenter) {
        this.center = newCenter;
    }

    /**
     * Set method - sets the ball's radius.
     * @param newSize the new radius of the ball.
     */
    public void setSize(int newSize) {
        this.r = newSize;
    }

    /**
     * Set method - sets the ball's velocity.
     * @param v the new velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Set method - sets the ball's velocity.
     * @param dx the new x axis direction of the ball's velocity.
     * @param dy the new y axis direction of the ball's velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Set method - sets the ball's environment to be that of the current game.
     * @param ge the new game environment.
     */
    public void setGameEnvironment(GameEnvironment ge) {
        this.gameEnvironment = ge;
    }

    /**
     * Draws the ball on the given surface.
     * @param surface the surface to draw the ball on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    /**
     * Checks if the ball was run over by the paddle and got stuck inside.
     * @return true if the ball was indeed stuck, false otherwise.
     */
    public boolean isBallInsidePaddle() {
        Rectangle paddle = this.gameEnvironment.getCollidables().get(0).getCollisionRectangle();
        if (this.center.getX() >= paddle.getUpperLeft().getX() && this.center.getX() <= paddle.getUpperLeft().getX()
                + paddle.getWidth() && this.center.getY() >= paddle.getUpperLeft().getY() && this.center.getY()
                <= paddle.getUpperLeft().getY() + paddle.getHeight()) {
            return true;
        }
        return false;
    }

    /**
     * "Moves the ball" - adding the velocity's x and y values to the ball's center after checking where it hits.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
        CollisionInfo colInfo = this.gameEnvironment.getClosestCollision(trajectory);
        if (colInfo != null) {

            // Change velocity based on hit
            this.setVelocity(colInfo.collisionObject().hit(this, colInfo.collisionPoint(), this.getVelocity()));
        } else {
            this.center = this.getVelocity().applyToPoint(this.center);
        }

        // Checks if ball was run over by paddle
        if (isBallInsidePaddle()) {
            Rectangle paddle = this.gameEnvironment.getCollidables().get(0).getCollisionRectangle();

            // If ball was run over in the left side of the paddle
            if (paddle.getUpperLeft().getX() + 0.5 * paddle.getWidth() > this.center.getX()) {
                this.center = new Point(paddle.getUpperLeft().getX() - 0.5, paddle.getUpperLeft().getY() - 0.5);
            } else {
                this.center = new Point(paddle.upperEdge().end().getX() + 0.5,
                        paddle.getUpperLeft().getY() - 0.5);
            }
        }
    }

    /**
     * defines what the ball has to do when time passes, move one step.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * adds the ball to the game - adds it to the sprites list and sets it's environment to be the game's environment.
     * @param game the current game that is run.
     */
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        this.setGameEnvironment(game.getEnvironment());
    }

    /**
     * removes the ball from the game - removes it from the sprites list.
     * @param game the current game that is run.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}