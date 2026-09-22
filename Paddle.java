import greenfoot.*;


/**
 * A paddle is an object that goes back and forth. Though it would be nice if balls would bounce of it.
 * 
 * @author The teachers 
 * @version 1
 */
public class Paddle extends Actor {
    private int width;
    private int height;
    private int dx;
    GreenfootImage octaneRight = new GreenfootImage("octaneRight.png");
    GreenfootImage octaneLeft = new GreenfootImage("octaneLeft.png");
    private boolean isTurnedLeft;
    private static int paddleX;

    /**
     * Constructs a new paddle with the given dimensions.
     */
    public Paddle(int width, int height) {
        this.width = width;
        this.height = height;
        // dx = hastighed på x-aksen
        dx = 1;
        createImage();
    }
    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        playerMove();
        tryChangeDirection();
        // vi tjekker om bilen peger mod venstre, hvis sandt, sæt billedet til venstre, ellers til højre
        if(isTurnedLeft){
            setImage(octaneLeft);
        } else {
            setImage(octaneRight);
        }
    }    
    /** Her bruger vi Greenfoots built-in method til at tjekke for keyboard-inputs
     *  og får spiller-paddlen til at bevæge sig hvis der trykke på A,D eller pil-taster
     */
        private void playerMove() {
        if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")){
        move(-5);
        isTurnedLeft = true;
    }
        if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")){
        move(5);
        isTurnedLeft = false;
    }
    }
    /**
     * Will rotate the paddle 180 degrees if the paddle is at worlds edge.
     */
    private void tryChangeDirection() {
        //Check to see if we are touching the outer boundaries of the world:
        // IF we are touching the right boundary OR we are touching the left boundary:
        if(getX() + width/2 >= getWorld().getWidth() || getX() - width/2 <= 0)
        {
            //Change our 'x' direction to the inverted direction:
            dx = dx * -1;
        }
    }
    /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */
    private void createImage() {
        //sætter billede for spillerens paddle
        setImage(octaneRight);
    }
}
