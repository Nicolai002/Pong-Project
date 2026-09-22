import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameOver extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    
    public GameOver(boolean gameLost) {    
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        if(gameLost){
            setBackground("losescreen.png");
            showText("GAME OVER", 250, 200);
            showText("Press \"ENTER\" to restart.", 250, 250);
            Greenfoot.playSound("gameover.mp3");
        }
    }
    public void act() {
        String key = Greenfoot.getKey();
        if (key != null && key.equals("enter")) {
            Greenfoot.setWorld(new IntroWorld());
        }
    }
}
