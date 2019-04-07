import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bird here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bird extends Actor
{
    /**
     * Act - do whatever the Bird wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int speed = 3;
    private int x,y,firstX,firstY;
    private boolean played = false,isColliding=false;
    private GreenfootImage rightImage;
    private GreenfootImage leftImage;
    BirdStatus counter;
    
    public Bird() {
        rightImage = getImage();
        leftImage = new GreenfootImage(rightImage);
        leftImage.mirrorHorizontally();   
    }
    
    public Bird(BirdStatus stats) {
        this();
        counter = stats;
    }
    
    public void act() 
    {
        // Add your action code here.
        keypress();
        addCollision();
        stopAtWalls();
        ketemu();
        checkLife();
    }    
    
    public void keypress() {
        if(played==false) {
            firstX = getX();
            firstY = getY();
        }
        if(Greenfoot.isKeyDown("A")){
            this.setLocation(getX()-speed, getY());
            if(!getImage().equals(leftImage)) setImage(leftImage);
            played=true;
            if(!isTouching(Tree.class)) counter.minEnergy();
        }else if(Greenfoot.isKeyDown("W")){
            this.setLocation(getX(), getY()-speed);
            played=true;
            if(!isTouching(Tree.class)) counter.minEnergy();
        }else if(Greenfoot.isKeyDown("D")){
            this.setLocation(getX()+speed, getY());
            if(getImage().equals(leftImage)) setImage(rightImage);
            played=true;
            if(!isTouching(Tree.class)) counter.minEnergy();
        }else if(Greenfoot.isKeyDown("S")){
            this.setLocation(getX(), getY()+speed);
            played=true;
            if(!isTouching(Tree.class)) counter.minEnergy();
        }
    }
    
    public void addCollision(){
       if(isAtEdge()) move(0);
    }
    
    public void checkLife() {
        if(counter.energy<0 || counter.health==0) {
            movetoCenter();
            setImage("lose.png");
            Greenfoot.stop();
        }
    }
    
    
    public void ketemu() {
        if(isTouching(Enemy.class)){
            if(!isTouching(Tree.class)) {
                removeTouching(Enemy.class);
                counter.minHealth();
                setLocation(firstX,firstY); 
            }
        }
        
        if(isTouching(Tree.class)) {
            counter.charge();
        }
        
        if(isTouching(Sample.class)) {
            removeTouching(Sample.class);
            counter.addScore();
        }
        
        if(isTouching(Door.class)) _goto();
        
        if(isTouching(Fruit.class)) {
            if(isTouching(FBanana.class)) counter.addEnergy(1);
            else if(isTouching(FBanana.class)) counter.addEnergy(5);
            else if(isTouching(FPapaya.class)) counter.addEnergy(10);
            removeTouching(Fruit.class);
            _goto();
        }
    }
    
    public void _goto() {
        if(getWorld() instanceof MazeWorld)
                Greenfoot.setWorld(new EnergyWorld(counter));
            else if(getWorld() instanceof EnergyWorld)
                Greenfoot.setWorld(new MazeWorld(counter));
    }
    
    public void stopAtWalls() { 
        if (getOneIntersectingObject(Wall.class) != null) { 
            setLocation(x,y); 
            isColliding = true;
        } else {
            x = getX(); y = getY(); 
            isColliding = false;
        } 
    }
    
    public void movetoCenter(){
        setLocation(getWorld().getWidth()/2,getWorld().getHeight()/2);
    }

}
