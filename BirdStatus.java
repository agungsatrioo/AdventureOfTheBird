    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
     import java.text.DecimalFormat;
     import java.util.List;
     import java.util.Timer;
    
    /**
     * Write a description of class BirdStatus here.
     * 
     * @author (your name) 
     * @version (a version number or a date)
     */
    public class BirdStatus extends Actor
    {
        /**
         * Act - do whatever the BirdStatus wants to do. This method is called whenever
         * the 'Act' or 'Run' button gets pressed in the environment.
         */
        private final double H_MAX = 3;
        public double health = 3;
        
        private final int E_MAX = 1000;
        public int energy = 1000;
        public int score = 0;
        
        private Lives lives[];
        private World wld;
        private DecimalFormat decimalFormat;
        private GreenfootImage half_img;
        
        public BirdStatus() {
            lives = new Lives[(int)Math.round(health)];
            decimalFormat = new DecimalFormat("#.##");
            half_img = new GreenfootImage("half_heart.png");
        }
        
        public BirdStatus(World world) {
            this();
            this.wld    = world;
        }
        
        public BirdStatus(BirdStatus state, World world) {
            this(world);
            this.health = state.health;
            this.energy = state.energy;
            this.score  = state.score;
            this.lives  = state.lives;
        }
        
        public void drawLives() {
            int firstX = 180;
            double sisa_health = health+1;

            List remove = wld.getObjects( Lives.class ); 
            for (Object objects : remove) wld.removeObject( ( Lives ) objects );
            
            for(int i = 0;i<health;i++) {
                lives[i] = new Lives();
                wld.addObject(lives[i],firstX,20);
                firstX +=(lives[i].getImage().getWidth()+5);
                sisa_health--;
                
                if(sisa_health<1) lives[i].setImage(half_img);
            }
        }
        
        public void act() 
        {
            String disp = decimalFormat.format(energy);
            setImage(new GreenfootImage("Score: " + score + "\nEnergy: "+disp, 12, Color.GREEN, Color.BLACK));
        }    
         
        public void addScore(){
            score++;
        } 
        
        public void minEnergy(){
            energy -= 1;
        }
        
        public void charge(){
            if(energy<E_MAX) energy +=1;
        }
        
        public void addEnergy(int amount) {
            if(energy<E_MAX) energy +=amount;
        }
        
        public void plusHealth() {
            if(health<H_MAX) this.health +=.5;
            drawLives();
        }
    
        public void minHealth() {
            if(health>0)this.health -= .5;
            drawLives();
    }
}
