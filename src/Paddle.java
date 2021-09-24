import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The class 'Paddle' describes a paddle object.
 * @author Yotam Levin
 * ID: 313248916
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle collisionRectangle;
    private final java.awt.Color color;
    private final int leftBorder;
    private final int rightBorder;
    private final int speed;

    /**
     * Constructor method.
     * @param keyboard the keyboard sensor for this game.
     * @param collisionRectangle the paddle's rectangle shape.
     * @param color The paddle's color.
     * @param leftBorder the game's left border.
     * @param rightBorder the game's right border.
     * @param speed the paddle's speed.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle collisionRectangle, java.awt.Color color,
                  int leftBorder, int rightBorder, int speed) {
        this.keyboard = keyboard;
        this.collisionRectangle = collisionRectangle;
        this.color = color;
        this.leftBorder = leftBorder + GameFlow.BORDER_SIZE;
        this.rightBorder = rightBorder - GameFlow.BORDER_SIZE;
        this.speed = speed;
    }

    /**
     * Moves the paddle left.
     */
    public void moveLeft() {
        this.collisionRectangle = new Rectangle(new Point(this.collisionRectangle.getUpperLeft().getX()
                - this.speed, this.collisionRectangle.getUpperLeft().getY()), this.collisionRectangle.getWidth(),
                this.collisionRectangle.getHeight());
    }

    /**
     * Moves the paddle right.
     */
    public void moveRight() {
        this.collisionRectangle = new Rectangle(new Point(this.collisionRectangle.getUpperLeft().getX()
                + this.speed, this.collisionRectangle.getUpperLeft().getY()), this.collisionRectangle.getWidth(),
                this.collisionRectangle.getHeight());
    }

    /**
     * defines what the paddle has to do when time passes, check if is needed to move left or right.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)
                && this.collisionRectangle.getUpperLeft().getX() > this.leftBorder) {
            this.moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                && this.collisionRectangle.upperEdge().end().getX() < this.rightBorder) {
            this.moveRight();
        }
    }

    /**
     * Draws the paddle on the given surface.
     * @param d the surface to draw the paddle on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
                (int) this.collisionRectangle.getUpperLeft().getY(),
                (int) this.collisionRectangle.getWidth(),
                (int) this.collisionRectangle.getHeight());
        d.setColor(this.color);
        d.fillRectangle((int) this.collisionRectangle.getUpperLeft().getX(),
                (int) this.collisionRectangle.getUpperLeft().getY(),
                (int) this.collisionRectangle.getWidth(),
                (int) this.collisionRectangle.getHeight());
    }

    /**
     * Get method - the paddle's rectangle shape.
     * @return the paddle's rectangle shape.
     */
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    /**
     * Checks where on the paddle the collision point was and returns the velocity accordingly. The function
     * divides the paddle into 5 parts and the velocity returned is based on which part includes the
     * collision point.
     * @param hitter the ball that hit this object.
     * @param collisionPoint the point of the collision between a ball and this block.
     * @param currentVelocity the current velocity that the ball has.
     * @return the new velocity of the ball after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double fifthOfSize = (this.collisionRectangle.getWidth() / 5);

        // calculate the ball's previous speed
        double ballSpeed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        if (collisionPoint.getX() >= this.collisionRectangle.getUpperLeft().getX()
                && collisionPoint.getX() < fifthOfSize + this.collisionRectangle.getUpperLeft().getX()) {
            return Velocity.fromAngleAndSpeed(300, ballSpeed);
        } else if (collisionPoint.getX() >= fifthOfSize + this.collisionRectangle.getUpperLeft().getX()
                && collisionPoint.getX() < (2 * fifthOfSize) + this.collisionRectangle.getUpperLeft().getX()) {
            return Velocity.fromAngleAndSpeed(330, ballSpeed);
        } else if (collisionPoint.getX() >= (2 * fifthOfSize) + this.collisionRectangle.getUpperLeft().getX()
                && collisionPoint.getX() < (3 * fifthOfSize) + this.collisionRectangle.getUpperLeft().getX()) {
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
        } else if (collisionPoint.getX() >= (3 * fifthOfSize) + this.collisionRectangle.getUpperLeft().getX()
                && collisionPoint.getX() <= (4 * fifthOfSize) + this.collisionRectangle.getUpperLeft().getX()) {
            return Velocity.fromAngleAndSpeed(30, ballSpeed);
        } else {
            return Velocity.fromAngleAndSpeed(60, ballSpeed);
        }
    }

    /**
     * Adds the paddle to the game - adds it to the collidables and sprites lists.
     * @param g the current game that is run.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}