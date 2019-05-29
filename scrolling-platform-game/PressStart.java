import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PressStart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PressStart extends Actor
{
    /**
     * Act - do whatever the PressStart wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        getWorld().removeObject(this);
    }    
}
