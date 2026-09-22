import greenfoot.*;
import java.util.*;

/**
 * A paddle is an object that goes back and forth. Though it would be nice if balls would bounce of it.
 * 
 * @author The teachers 
 * @version 1
 */
public class Obstacle extends Actor
{
    private int Width;
    private int Height;
    private int dx;
    private int yx;

    /**
     * Constructs a new paddle with the given dimensions.
     */
    public Obstacle(int Width, int Height)
    {
        this.Width = Width;
        this.Height = Height;
        // dx = hastighed på x-aksen
        dx = 2;
        createImage();
    }

    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        tryChangeDirection();
        
        // Automatisk bevægelse
        setLocation(getX() + dx, getY() + yx);
        
        if(this.isAtEdge()){
            PingWorld world = (PingWorld) getWorld();
            world.removeObject(this);
        }
    }    
    /**
     * Will rotate the paddle 180 degrees if the paddle is at worlds edge.
     */
    private void tryChangeDirection()
    {
        //Check to see if we are touching the outer boundaries of the world:
        // IF we are touching the right boundary OR we are touching the left boundary:
        if(getX() + Width/2 >= getWorld().getWidth() || getX() - Width/2 <= 0)
        {   
            // Flytter obstacle på y-aksen når den rammer væggen
            yx = yx * - 1;
        }
    }

    /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */
    private void createImage()
    {
        //sætter billede for obstacle
        GreenfootImage rocketcar = new GreenfootImage("rocketcar.png");
        setImage(rocketcar);
    }

}
