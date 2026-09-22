import greenfoot.*;

public class IntroWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;

    public IntroWorld() {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        setBackground("introscreen.png");
        showText("Press \"ENTER\" to start the game.", 250, 600);
        showText("Move the car with \"A\" and \"D\" or arrow keys.", 250, 630);
        Ball.resetLostRounds();
    }
    
    public void act()
    {
        String key = Greenfoot.getKey();
        if (key != null && key.equals("enter")) {
            Greenfoot.setWorld(new PingWorld(true));
        }
    }
    
}
