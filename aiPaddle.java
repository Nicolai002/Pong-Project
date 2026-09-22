import greenfoot.*;


/**
 * A paddle is an object that goes back and forth. Though it would be nice if balls would bounce of it.
 * 
 * @author The teachers 
 * @version 1
 */
public class aiPaddle extends Actor {
    private int Width;
    private int Height;
    // dx = hastighed på x-aksen
    private int dx;
    private boolean facingLeft;
    GreenfootImage dominus = new GreenfootImage("dominus.png");

    /**
     * Constructs a new paddle with the given dimensions.
     */
    public aiPaddle(int Width, int Height)
    {
        this.Width = Width;
        this.Height = Height;
        // dx styrer hvor hurtigt den bevæger sig
        dx = 1;
        createImage();
    }
    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        tryChangeDirection();
    }    
    /**
     * Will rotate the paddle 180 degrees if the paddle is at worlds edge.
     */
    private void tryChangeDirection() {
        // hvis boldens x-værdi er større end bilens x-værdi, og bolden er på vej tilbage fra spilleren
        // skal den bevæge sig til højre
        // faceDirection bruges til at bestemme om bilen skal pege mod venstre eller højre
        if(Ball.getBallX()>getX() && Ball.isBallReturning()) {
            setLocation(getX() + dx, getY());
            faceDirection(false);
        }
        // hvis boldens x-værdi er mindre end bilens x-værdi, skal den bevæge sig til venstre
        else if (Ball.getBallX()<getX() && Ball.isBallReturning()) {
            setLocation(getX() - dx , getY());
            faceDirection(true);
        }
    }
    private void faceDirection(boolean movingLeft) {
        // en metode til at flip bilens billede afhængig af hvor den peger
        // hvis movingleft er false, men facingleft er true, flipper billedet og de bliver begge true
        // movingleft bliver kun false, når bilen rykker sig til højre på x-aksen
        if(movingLeft != facingLeft) {
            dominus.mirrorHorizontally();
            facingLeft = movingLeft;
        }
    }
    /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */
    private void createImage() {
        //sætter billede for ai paddle og flipper den
        setImage(dominus);
        dominus.mirrorVertically();
    }
}