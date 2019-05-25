import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Heart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Heart extends Item
{
    /**
     * Act - do whatever the Heart wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //keep track of the time
    private int frames;
    public void act() 
    {
        ifTouching();
       // checkDissapear();
        frames++;
    }    
    private void ifTouching()
    {
        if (isTouching(Hero.class))
        {
            getWorld().removeObject(this);
        }
    }
    private void checkDissapear()
    {
        if (frames % 36000 == 0)
        {
            getWorld().removeObject(this);
        }
    }
}
