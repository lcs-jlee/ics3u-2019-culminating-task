import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Go here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Go extends Actor
{
    /**
     * Act - do whatever the Go wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int frames = 0;
    Go()
    {
        Greenfoot.playSound("go.wav");
    }
    public void act() 
    {
       if (frames / 60 == 1)
       {
           getWorld().removeObject(this);
       }
       frames++;
    }    
    
}
