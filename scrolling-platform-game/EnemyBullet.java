import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class EnemyBullet extends Actor
{
    /**
     * Act - do whatever the bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    boolean isRemoved = false;
    
    //constructor
    EnemyBullet (int rotation)
    {
          setRotation(rotation);
    }
    //act
    public void act() 
    {
        // Add your action code here.
        move(-8);
        if (isRemoved == false)
        {
            checkIfAtEdge();
        }
        if (isRemoved == false)
        {
            checkForRemoval();
        }
    } 
    /**
     * it should be removed when it touches the edge
     */
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
    /**
     *  removes when it touches Hero and Platform
     */
    public void checkForRemoval()
    {
        if (isTouching(Hero.class))
        {
            SideScrollingWorld world = (SideScrollingWorld) getWorld();
            world.getHero().decreaseLives();
            this.isRemoved = true;
            world.removeObject(this);
        }
        else if (isTouching(Platform.class))
        {
            this.isRemoved = true;
            getWorld().removeObject(this);
        }
        
    }
    
    
}
