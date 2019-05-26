import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FireBall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FireBall extends Actor
{
    /**
     * Act - do whatever the FireBall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean isRemoved = false;
    public void act() 
    {
        move(5);
        if (isRemoved == false)
        {
           checkForRemoval();
        }
    }    
    
    public void checkForRemoval()
    {
        if (isTouching(Hero.class))
        {
            SideScrollingWorld world = (SideScrollingWorld) getWorld();
            world.getHero().decreaseLivesFireBall();
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
