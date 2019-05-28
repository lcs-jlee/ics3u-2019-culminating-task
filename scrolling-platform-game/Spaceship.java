import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cuphead here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spaceship extends Actor
{
    /**
     * Act - do whatever the Cuphead wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int life = 5;
    private int frames = 0;
    public void act() 
    {
        movement();
        checkRemoval();
        frames++;
    }   
    private void movement()
    {
        setRotation(this.getRotation()-2);
        
        move(5);
        fire();
    }
    private void fire()
    {
        SideScrollingWorld world = (SideScrollingWorld) getWorld(); 
        if (frames % 4 == 0 && world.VISIBLE_HEIGHT/2 +30 < getY())
        {
            
            SpaceshipBullet newSpaceshipBullet = new SpaceshipBullet(this.getRotation());
            double x = Math.cos(getRotation()*Math.PI/180) * 22;
            double y = Math.sin(getRotation()*Math.PI/180) * 22;
            
            getWorld().addObject(newSpaceshipBullet, getX()+(int)Math.round(x), getY()+(int)Math.round(y));
            
            
            
        }
    }
    public void moveRight(int speed)
    {
        setLocation(getX() + speed, getY());
    }

    public void moveLeft(int speed)
    {
        setLocation(getX() - speed, getY());
    }
    
    public void moveUp(int speed)
    {
        setLocation(getX(), getY()-speed);
    }
    
    public void moveDown(int speed)
    {
        setLocation(getX(), getY()+speed);
    }

    /**
     * Decrease lives
     */
    public void decreaseLives()
    {
        life--;
        
    }
    private void checkRemoval()
    {

        if (life < 1)
        {
            SideScrollingWorld world = (SideScrollingWorld) getWorld(); 
            world.addScoreSpaceShip();
            getWorld().removeObject(this);
        }

    }
}
