
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int life = 3;
    private int frames = 0;
    private int startingXPosition;
    private boolean directRight;
    public Enemy()
    {
        directRight = true;
    }

    public void act() 
    {

        movement();
        shoot();
        checkHit();
        checkRemoval();
        frames++;
    }

    private void shoot()
    {
        if (frames % 60 == 0 || frames == 0)
        {
            for(int i = 0; i <=6; i++)
            {
                EnemyBullet newBullet = new EnemyBullet(0 + 30 * i);
                getWorld().addObject(newBullet, getX(), getY());     
            }
        }

    
    }

    private void movement()
    {
        if (directRight == true )
        {
            move(2);
            if (frames % 24 == 0)
            {
                directRight = false;
            }
        }
        else 
        {
            move(-2);
            if (frames % 24 == 0)
            {
                directRight = true;
            }

        }

    }

    private void checkRemoval()
    {

        if (life < 1)
        {
            SideScrollingWorld world = (SideScrollingWorld) getWorld(); 
            world.addScore();
            getWorld().removeObject(this);
        }

    }

    private void checkHit()
    {
        if (isTouching(Bullet.class))
        {
            life --;
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
}
