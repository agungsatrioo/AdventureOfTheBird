import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ESnake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ESnake extends Enemy
{
    /**
     * Act - do whatever the ESnake wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        super.act();
        if(isTouching(Sample.class)) removeTouching(Sample.class);
    }    
}
