
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
    private int life = 5;
    private int frames = 0;
    public void act() 
    {
        
        if (frames % 10 == 0)
        {
            movement();
        }
        
        checkHit();
        frames++;
    } 
    private void movement()
    {
        int selection = Greenfoot.getRandomNumber(4);
        if (selection == 0)
        {
            setLocation(getX()+10,getY()+5);
        }
        else if (selection ==1)
        {
            setLocation(getX()+10,getY()-5);
        }
        else if (selection ==2)
        {
            setLocation(getX()-10,getY()-5);
        }
        else if (selection == 3)
        {
            setLocation(getX()-10, getY()+5);
        }
        else 
        {
            setLocation(getX(), getY());
        }
    }
    private void checkRemoval()
    {
        
        if (life < 0)
        {
            getWorld().removeObject(this);
        }
        
    }
    private void checkHit()
    {
        if (isTouching(Bullet.class))
        {
             life --;
             checkRemoval();
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
    
}
