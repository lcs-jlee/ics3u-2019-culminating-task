import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cuphead here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cuphead extends Actor
{
    /**
     * Act - do whatever the Cuphead wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        movement();
    }   
    private void movement()
    {
        setRotation(getRotation()-3);
        move(5);
    }
}
