import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Heart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item extends Actor
{
    /**
     * Act - do whatever the Heart wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
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
