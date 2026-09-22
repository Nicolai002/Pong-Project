import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Scoreboard extends Actor {
    public void act() {
        // scoreboard bliver hele tiden opdateret
        Scoreboard();
    }
    private void Scoreboard() {
        // vi laver en method som tegner et scoreboard og bruger vores method getLevel fra GameManager til at display level
        GreenfootImage image = new GreenfootImage(150, 30);
        image.setColor(Color.WHITE);
        image.setFont(new Font("Arial", true, false, 20));
        image.drawString("Level: " + GameManager.getLevel(), 0, 20);
        setImage(image);
    }
}