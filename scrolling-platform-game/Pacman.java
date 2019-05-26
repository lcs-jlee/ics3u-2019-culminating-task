import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pacman here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pacman extends Actor
{
    /**
     * Act - do whatever the Pacman wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean isFacingRight = true;
    private int frames = 0;
    private int startingX = 32 *3;
    private boolean ifAllowedToMove = false;
    private int moveAmount = 0;
    private int life = 15;
    
    public void act() 
    {
        // Add your action code here.
        checkMovement();
        fire();
        if (frames % 5 == 0 && ifAllowedToMove == true)
        {
            movement();
        }
        if (moveAmount >=  500)
        {
            ifAllowedToMove = false;
        }
        if (frames % 5 == 0 && ifAllowedToMove == false)
        {
            movementBack();
        }
        checkForRemoval();
        frames++;
    }
    private void fire()
    {
        if (frames % 300 == 0 || frames == 0)
        {
            for(int i = 0; i <=6; i++)
            {
                FireBall fireBall = new FireBall();
                getWorld().addObject(fireBall, getX()+30, getY());     
            }
        }
    }
    private void movement()
    {
        move(50);
        moveAmount +=50;
    }
    private void movementBack()
    {
        if (moveAmount >= 0)
        {
            move(-30);
            moveAmount -=30;
        }
        
    }
    private void checkMovement()
    {
        
        if (frames % 600 == 0)
        {
            
                ifAllowedToMove = true;
            
            
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
    public void decreaseLives()
    {
        life--;
    }
    public void checkForRemoval()
    {
        if (life <= 0)
        {
            SideScrollingWorld world = (SideScrollingWorld) getWorld(); 
            
            world.isPacmanDead = true;
            getWorld().removeObject(this);
        }
    }
}
