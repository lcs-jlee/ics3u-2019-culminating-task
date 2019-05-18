import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Bullet extends Actor
{
    /**
     * Act - do whatever the bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    boolean isRemoved = false;
    public void act() 
    {
        // Add your action code here.
        move(10);
        
        
        if (isRemoved == false)
        {
            checkIfAtEdge();
        }
    } 
     private void checkIfAtEdge()
    {
        if (isAtEdge())
        {
            SideScrollingWorld world = (SideScrollingWorld) getWorld();
           
            //get removed
            this.isRemoved = true;
            getWorld().removeObject(this);
        }
    
    }
}
