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
    private int speed=1;
    private int direction;
    public void act() 
    {
        int x = getX();
        int y = getY();
        move(speed);
        if(isAtEdge()||isTouching(Wall.class)) {
            balik(direction);
        }
    }    
    
    private void changeDirection(){
        direction = (direction+1+Greenfoot.getRandomNumber(3))%4;
    }
    
    private void balik(int dir) {
        //turn(180);
        turn(180);
        getImage().mirrorVertically();
        //changeDirection();
    }
}
