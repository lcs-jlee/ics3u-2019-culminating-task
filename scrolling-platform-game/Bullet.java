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
        //check If Bullet should be removed
        if (isRemoved == false)
        {
            checkIfAtEdge();
        }
        if (isRemoved == false)
        {
            checkForEnemy();
        }
        if (isRemoved == false)
        {
            checkForSpaceship();
        }
        if (isRemoved == false)
        {
            checkIfAtPlatform();
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
    public void checkForEnemy()
    {
        if (isTouching(Enemy.class))
        {
            // Get the enemy we are touching
            Enemy theEnemy = (Enemy) getOneIntersectingObject(Enemy.class);
            theEnemy.decreaseLives();
            
            SideScrollingWorld world = (SideScrollingWorld) getWorld();
            this.isRemoved = true;
            world.removeObject(this);
        }
        
        
    }
    private void checkForSpaceship()
    {
        if (isTouching(Spaceship.class))
        {
            Spaceship theSpaceship = (Spaceship) getOneIntersectingObject(Spaceship.class);
            theSpaceship.decreaseLives();
            
            SideScrollingWorld world = (SideScrollingWorld) getWorld();
            this.isRemoved = true;
            world.removeObject(this);
        }
    }
    private void checkIfAtPlatform()
    {
         if (isTouching(Platform.class))
        {
            SideScrollingWorld world = (SideScrollingWorld) getWorld();
            this.isRemoved = true;
            world.removeObject(this);
        }
    }
    
    
}
