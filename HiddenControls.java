    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
    import java.util.List;
    import java.util.Timer;
    import java.util.TimerTask;
    
    /**
     * Write a description of class HiddenControls here.
     * 
     * @author (your name) 
     * @version (a version number or a date)
     */
    public class HiddenControls extends Actor
    {
        /**
         * Act - do whatever the HiddenControls wants to do. This method is called whenever
         * the 'Act' or 'Run' button gets pressed in the environment.
         */
        long initialTime = System.currentTimeMillis();  
        private Sample[] cacing;
        private FBanana[] banana;
        private FApple[] apple;
        private FPapaya[] papaya;
        private World world;
        private boolean first=true;
        private int timer = 0;
        private int leng = 20;
        
        public HiddenControls() {
            cacing = new Sample[leng];
            banana = new FBanana[leng];
            apple = new FApple[leng];
            papaya = new FPapaya[leng];
        }
        
       public HiddenControls(World wld) {
           this();
            world = wld;
        }
        
        public void act() 
        {
            // Add your action code here.
            timer++;
            if(timer>10000) timer = 0;
            
            if(first) {
                if(getWorld() instanceof MazeWorld) drawWorm();
                    else if(getWorld() instanceof EnergyWorld) drawFruit();
                first=false;
            } else {
                int interval = 7 * 60;
                if(timer%interval==0) {
                    if(getWorld() instanceof MazeWorld) drawWorm();
                    else if(getWorld() instanceof EnergyWorld) drawFruit();
                }
            }
            
        }    
        
         public void drawFruit() {
            int x_min = 180, x_max = 630;
            int y_min = 75, y_max = 600;
            int space = 10;
    
            List remove = world.getObjects(Fruit.class); 
            for (Object objects : remove) world.removeObject((Fruit)objects );

            for(int i=0;i<leng;i++) {
                int x=getRandomNumber(x_min,x_max);
                int y=getRandomNumber(y_min,y_max);
                int n=getRandomNumber(1,3);
            
                banana[i] = new FBanana();
                apple[i] = new FApple();
                papaya[i] = new FPapaya();
                if(world.getObjectsAt(x, y, Actor.class).isEmpty() &&
                   world.getObjectsAt(x+space, y, Actor.class).isEmpty() &&
                   world.getObjectsAt(x, y+space, Actor.class).isEmpty() &&
                   world.getObjectsAt(x+space, y+space, Actor.class).isEmpty() &&
                   world.getObjectsAt(x-space, y, Actor.class).isEmpty() &&
                   world.getObjectsAt(x, y-space, Actor.class).isEmpty() &&
                   world.getObjectsAt(x-space, y-space, Actor.class).isEmpty()) {
                    switch(n) {
                        case 1: world.addObject(banana[i],x,y); break;
                        case 2: world.addObject(apple[i],x,y); break;
                        case 3: world.addObject(papaya[i],x,y); break;
                        default: world.addObject(banana[i],x,y);
                    }
                }
            }
        }
        
        public void drawWorm() {
            int x_min = 180, x_max = 630;
            int y_min = 75, y_max = 600;
            int space = 10;
    
            List remove = world.getObjects( Sample.class ); 
            for (Object objects : remove) world.removeObject( ( Sample ) objects );

            for(int i=0;i<cacing.length;i++) {
                int x=getRandomNumber(x_min,x_max);
                int y=getRandomNumber(y_min,y_max);
            
                cacing[i] = new Sample();
                if(world.getObjectsAt(x, y, Actor.class).isEmpty() &&
                   world.getObjectsAt(x+space, y, Actor.class).isEmpty() &&
                   world.getObjectsAt(x, y+space, Actor.class).isEmpty() &&
                   world.getObjectsAt(x+space, y+space, Actor.class).isEmpty() &&
                   world.getObjectsAt(x-space, y, Actor.class).isEmpty() &&
                   world.getObjectsAt(x, y-space, Actor.class).isEmpty() &&
                   world.getObjectsAt(x-space, y-space, Actor.class).isEmpty()) {
                    world.addObject(cacing[i],x,y);
                }
            }
        }

        public int getRandomNumber(int start,int end)
        {
           int normal = Greenfoot.getRandomNumber(end-start+1);
           return normal+start;
        }
}
