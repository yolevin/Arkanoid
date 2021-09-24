import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;

/**
 * The class 'Game' describes the actual game and everything it contains.
 * @author Yotam Levin
 * ID: 313248916
 */
public class GameLevel implements Animation {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final biuoop.GUI gui;
    private final Counter blockCounter;
    private final Counter ballCounter;
    private final Counter score;
    private final Counter lives;
    private final AnimationRunner runner;
    private boolean running;
    private final LevelInformation levelInformation;

    /**
     * Constructor method.
     * @param sprites a collection of sprites that the game holds.
     * @param environment the game's environment.
     * @param gui the game's GUI.
     * @param score the game's score.
     * @param runner the game's animation loop.
     * @param levelInformation the current level's information.
     * @param lives the lives left for this game.
     */
    public GameLevel(SpriteCollection sprites, GameEnvironment environment, biuoop.GUI gui, Counter score,
                     Counter lives, AnimationRunner runner, LevelInformation levelInformation) {
        this.sprites = sprites;
        this.environment = environment;
        this.gui = gui;
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.score = score;
        this.lives = lives;
        this.runner = runner;
        this.levelInformation = levelInformation;
        this.running = true;
    }

    /**
     * Adds a collidable to the game.
     * @param c the new collidable to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * removes a collidable from the game.
     * @param c the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Adds a sprite to the game.
     * @param s the new sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * removes a sprite from the game.
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Get method - the game's environment.
     * @return the game's environment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Initialize a new game: create the Blocks and Ball and Paddle and add them to the game.
     */
    public void initialize() {
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        ScoreTrackingListener scoreTrack = new ScoreTrackingListener(this.score);

        // Background
        this.levelInformation.getBackground().addToGame(this);

        // Paddle - MUST BE THE FIRST COLLIDABLE!
        createPaddle();

        // Top bar
        Rectangle topBarRect = new Rectangle(new Point(0, 0), GameFlow.WIDTH, 20);
        Block topBar = new Block(topBarRect, Color.LIGHT_GRAY);
        topBar.addToGame(this);

        // Block creation
        createBlocks(blockRemover, scoreTrack, ballRemover);

        // Score Indicator
        createScoreIndicator(topBarRect);

        // Name Indicator
        createNameIndicator(topBarRect);

        // Lives Indicator
        createLivesIndicator(topBarRect);
    }

    /**
     * Decides if the game should be stopped.
     * @return True if the game should stop, false otherwise.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Perform the actions necessary in a single frame.
     * @param d the surface to draw the game on.
     */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.blockCounter.getValue() == this.levelInformation.blocks().size()
                - this.levelInformation.numberOfBlocksToRemove()) {
                this.score.increase(100);
                this.blockCounter.decrease(this.blockCounter.getValue());
                this.running = false;
        }
        if (this.ballCounter.getValue() == 0) {
            this.lives.decrease(1);
            this.running = false;
        }
        if (this.gui.getKeyboardSensor().isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.gui.getKeyboardSensor(),
                    this.gui.getKeyboardSensor().SPACE_KEY, new PauseScreen(this.gui.getKeyboardSensor())));
        }
    }

    /**
     * Runs the game: start the animation loop.
     */
    public void run() {
        this.createBalls();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
    }

    /**
     * returns the number of balls still left in the game.
     * @return the number of balls still left in the game.
     */
    public int getNumOfBlocks() {
        return this.blockCounter.getValue();
    }

    /**
     * Creates the balls of the game.
     */
    public void createBalls() {
        int numBalls = this.levelInformation.numberOfBalls();
        List<Velocity> ballVelocities = this.levelInformation.initialBallVelocities();
        for (int i = 0; i < numBalls; i++) {
            Ball ball = new Ball(GameFlow.WIDTH / 2, GameFlow.HEIGHT - 100, 5, Color.WHITE);
            ball.setVelocity(ballVelocities.get(i));
            ball.addToGame(this);
            this.ballCounter.increase(1);
        }
    }

    /**
     * Creates the paddle of the game.
     */
    public void createPaddle() {
        int paddleWidth = this.levelInformation.paddleWidth();
        int paddleSpeed = this.levelInformation.paddleSpeed();

        // Create paddle
        Rectangle paddleRect = new Rectangle(new Point((int) ((GameFlow.WIDTH / 2) - (paddleWidth / 2)),
                GameFlow.HEIGHT - 20), paddleWidth, 10);
        Paddle paddle = new Paddle(this.gui.getKeyboardSensor(), paddleRect, Color.YELLOW, 0,
                GameFlow.WIDTH, paddleSpeed);
        paddle.addToGame(this);
    }

    /**
     * Creates the score indicator of the game.
     * @param topBar the bar to present the score on.
     */
    public void createScoreIndicator(Rectangle topBar) {

        // Create score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, topBar);
        scoreIndicator.addToGame(this);
    }

    /**
     * Creates the lives indicator of the game.
     * @param topBar the bar to present the lives on.
     */
    public void createLivesIndicator(Rectangle topBar) {

        // Create score indicator
        LivesIndicator livesIndicator = new LivesIndicator(this.lives, topBar);
        livesIndicator.addToGame(this);
    }

    /**
     * Creates the name indicator of the game.
     * @param topBar the bar to present the name on.
     */
    public void createNameIndicator(Rectangle topBar) {

        // Create name indicator
        NameIndicator nameIndicator = new NameIndicator(this.levelInformation.levelName(), topBar);
        nameIndicator.addToGame(this);
    }

    /**
     * Creates the blocks of the game.
     * @param blockRemover the block remover object to add to each block.
     * @param scoreTrack the tracker to keep score.
     * @param ballRemover the ball remover object to add to the death block.
     */
    public void createBlocks(BlockRemover blockRemover, ScoreTrackingListener scoreTrack, BallRemover ballRemover) {
        Color borderColor = Color.BLACK;

        // Border blocks
        Block left = new Block(new Rectangle(new Point(0, 20), GameFlow.BORDER_SIZE, GameFlow.HEIGHT), borderColor);
        left.addToGame(this);
        Block right = new Block(new Rectangle(new Point(GameFlow.WIDTH - GameFlow.BORDER_SIZE, 20),
                GameFlow.BORDER_SIZE, GameFlow.HEIGHT), borderColor);
        right.addToGame(this);
        Block up = new Block(new Rectangle(new Point(GameFlow.BORDER_SIZE, 20), GameFlow.WIDTH - 50,
                GameFlow.BORDER_SIZE), borderColor);
        up.addToGame(this);
        Block death = new Block(new Rectangle(new Point(GameFlow.BORDER_SIZE, GameFlow.HEIGHT + 5),
                GameFlow.WIDTH - (GameFlow.BORDER_SIZE * 2), GameFlow.BORDER_SIZE), borderColor);
        death.addToGame(this);
        death.addHitListener(ballRemover);

        // Game blocks
        List<Block> blocks = this.levelInformation.blocks();
        for (Block block : blocks) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrack);
            this.blockCounter.increase(1);
        }
    }
}