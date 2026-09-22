
public class GameManager  {
    private static int level = 1;
    
    // vi laver en method der increaser level, som vi kan kalde i andre classes
    public static void increaseLevel() {
        level ++;
    }
    public static int getLevel() {
        return level;
    }
    public static int resetLevel(){
        level = 1;
        return level;
    }
}