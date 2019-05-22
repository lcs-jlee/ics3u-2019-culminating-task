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
    boolean direction;
    //constructor
    Bullet (boolean direction1)
    {
          direction = direction1;
    }
    //act
    public void act() 
    {
        // Add your action code here.
        if (direction == true)
        {
            move(10);
        }
        else
        {
            move(-10);
        }
        if (isRemoved == false)
        {
            checkIfAtEdge();
        }
        if (isRemoved == false)
        {
            checkForRemoval();
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
    public void checkForRemoval()
    {
        if (isTouching(Enemy.class))
        {
            SideScrollingWorld world = (SideScrollingWorld) getWorld();
            this.isRemoved = true;
            getWorld().removeObject(this);
        }
        
    }
    
    
}
