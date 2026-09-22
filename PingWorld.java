import greenfoot.*;
import java.util.*;

public class PingWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    
    /** Vi bruger Greenfoots inbyggede getRandomNumber metode, som har et minimum på 0 og et limit man sætter i (int)
     *  Vi tilføjer 200 til værdien som den genererer, for at lave en range på 200-500.
     *  Vi trækker 200 fra vores limit for at kompensere for de 200 vi tilføjer.
     */
    private int yx = 200 + Greenfoot.getRandomNumber(300);

    /**
     * Constructor for objects of class PingWorld.
     */
    public PingWorld(boolean gameStarted)
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        if (gameStarted)
        {
            //sætter baggrund for denne verden
            setBackground("stadium.png");
            //sætter rækkefølgen af objekter (hvordan de skal overlap)
            setPaintOrder(Paddle.class,aiPaddle.class,Ball.class,Scoreboard.class);
            
            // Create a new world with WORLD_WIDTHxWORLD_HEIGHT cells with a cell size of 1x1 pixels.
            addObject(new Ball(), WORLD_WIDTH/2, WORLD_HEIGHT/2);
            addObject(new Paddle(100,20), 60, WORLD_HEIGHT - 50);
            
            // Tilføjer ai Paddle (bredde, højde af objektet), width(x-aksen), height(y-aksen) <- hvor den skal spawne
            addObject(new aiPaddle(100,20), 60, WORLD_HEIGHT - 650);
            // Tilføjer scoreboard til verdenen
            addObject(new Scoreboard(), 450,100);
            
        }
        else
        {
            Greenfoot.setWorld(new IntroWorld());
        }
    }
    public void act(){
        // Hvis mængden af objekter af Obstacle class'en er 0, tilføj et obstacle.
        if (getObjects(Obstacle.class).size()==0){
            //Randomizer y-værdien hver gang der spawner et nyt obstacle.
            yx = 200 + Greenfoot.getRandomNumber(300);
            addObject(new Obstacle(100,20), -50, yx);
        }
        if(Ball.getLostRounds()>=3){
            Greenfoot.setWorld(new GameOver(true));
        }
    }
}
