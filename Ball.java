import greenfoot.*;
import java.util.List;

public class Ball extends Actor {
    private static final int BALL_SIZE = 35;
    private static final int BOUNCE_DEVIANCE_MAX = 5;
    private static final int STARTING_ANGLE_WIDTH = 90;
    private static final int DELAY_TIME = 100;

    private int speed;
    private boolean hasBouncedHorizontally;
    private boolean hasBouncedVertically;
    private int delay;
    private static boolean ballReturn;
    private static int ballX;
    private int touchCounter;
    
    // laver en static int til at holde styr på hvor mange gange spilleren har tabt, bruges i gameover world
    private static int lostRounds;
    
    // variabler til vores sound cooldown, "final" betyder at den variabel ikke kan ændres af andre i programmet
    private int soundCooldown;
    private final int soundCooldownMax = 40;

    /**
     * Contructs the ball and sets it in motion!
     */
    public Ball() {
        createImage();
        init();
    }
    /**
     * Creates and sets an image of a black ball to this actor.
     */
    private void createImage() {
        GreenfootImage ballImage = new GreenfootImage("ball.png");
        setImage(ballImage);
    }
    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (delay > 0) {
            delay--;
        }
        else
        {
            move(speed);
            ballX = getX();
            checkBounceOffWalls();
            checkRestart();
            isTouchingPaddle();
        }
        // vi laver en cooldown til boldens lyd, så den ikke spilles 10 gange lige efter hinanden ved kontakt
        // hvis cooldown er 0, og et objekt af en af de listede classes rør ved bolden, spilles lyden
        if(soundCooldown>0) {
            soundCooldown--;
        }
        if((isTouching(Paddle.class) || isTouching(aiPaddle.class) || isTouching(Obstacle.class) || isTouchingSides()) && soundCooldown == 0){
            Greenfoot.playSound("ballhit.mp3");
            soundCooldown = soundCooldownMax;
        }
    }    
    /** Her laver vi en method som tjekker om bolden rammer et objekt af Paddle class'en.
     *  Vi laver en liste af alle paddles og bruger Greenfoots built-in method til at
     *  tjekke om andre objekter rammer et objekt af denne class.
     *  I vores if-statement tjekker vi om listen IKKE er tom, og bruger vores givne method
     *  til at bounce bolden af, hvis det er tilfældet.
     */ 
    private void isTouchingPaddle() {
        List<Paddle> paddles = getIntersectingObjects(Paddle.class);
        if (!paddles.isEmpty()){
            revertVertically();
            ballReturn = true;
            // hver gang bolden bouncer af spillerens paddle increaser touch counter, hvis den er 10 eller mere, øges hastigheden
            touchCounter = touchCounter + 1;
            if(touchCounter>=10){
                speedIncrease();
            }
        }
        List<aiPaddle> aipaddles = getIntersectingObjects(aiPaddle.class);
        if (!aipaddles.isEmpty()){
            revertVertically();
            ballReturn = false;
        }
        List<Obstacle> obstacles = getIntersectingObjects(Obstacle.class);
        if (!obstacles.isEmpty() && ballReturn==true){
            revertVertically();
        }
    }
    public static boolean isBallReturning(){
        return ballReturn;
    }
    public static int getBallX(){
        return ballX;
    }
    /**
     * Returns true if the ball is touching one of the side walls.
     */
    private boolean isTouchingSides() {
        return (getX() <= BALL_SIZE/2 || getX() >= getWorld().getWidth() - BALL_SIZE/2);
    }
    /**
     * Returns true if the ball is touching the ceiling.
     */
    private boolean isTouchingCeiling() {
        return (getY() <= BALL_SIZE/2);
    }
    /**
     * Returns true if the ball is touching the floor.
     */
    private boolean isTouchingFloor() { 
        return (getY() >= getWorld().getHeight() - BALL_SIZE/2);
    }
    /**
     * Check to see if the ball should bounce off one of the walls.
     * If touching one of the walls, the ball is bouncing off.
     */
    private void checkBounceOffWalls()
    {
        if (isTouchingSides()) {
            if (! hasBouncedHorizontally) {
                revertHorizontally();
            }
        } else {
            hasBouncedHorizontally = false;
        }
    }
    //vi laver public static methods som kan bruges i game over world
    public static int getLostRounds(){
        return lostRounds;
    }
    public static int resetLostRounds(){
        lostRounds = 0;
        return lostRounds;
    }
    /**
     * Check to see if the ball should be restarted.
     * If touching the floor the ball is restarted in initial position and speed.
     */
    private void checkRestart() {
        if (isTouchingFloor()) {
            lostRounds++;
            resetGame();
        } else if (isTouchingCeiling()) {
            resetGame();
        }
    }
    /**
     * Bounces the ball back from a vertical surface.
     */
    private void revertHorizontally() {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((180 - getRotation()+ randomness + 360) % 360);
        hasBouncedHorizontally = true;
    }
    /**
     * Bounces the bal back from a horizontal surface.
     */
    private void revertVertically() {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((360 - getRotation()+ randomness + 360) % 360);
        hasBouncedVertically = true;
    }
    /**
     * Initialize the ball settings.
     */
    private void init() {
        speed = 2;
        delay = DELAY_TIME;
        hasBouncedHorizontally = false;
        hasBouncedVertically = false;
        setRotation(Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2);
    }
    // laver en method til at increase boldens hastighed
    private void speedIncrease() {
        speed = speed + 1;
        GameManager.increaseLevel();
        touchCounter = 0;
    }
    private void resetGame() {
        init();
        GameManager.resetLevel();
        setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
        // resetter boldens hastighed når der scores
        speed = 2;
        Greenfoot.playSound("goalsound.mp3");
        Greenfoot.playSound("crowdnoise.mp3");
    }
}
