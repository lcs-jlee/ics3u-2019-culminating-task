    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
    
    /**
     * Template for a side-scrolling platform game.
     * 
     * @author R. Gordon
     * @version May 8, 2019
     */
    public class SideScrollingWorld extends World
    {
        /**
         * Instance variables
         * 
         * These are available for use in any method below.
         */    
        // Tile size in pixels for world elements (blocks, clouds, etc)
        public static final int TILE_SIZE = 32;
        public static final int HALF_TILE_SIZE = TILE_SIZE/2;
        // World size constants
        public static final int VISIBLE_WIDTH = 640;
        public static final int VISIBLE_HEIGHT = 448;
        public static final int HALF_VISIBLE_WIDTH = VISIBLE_WIDTH / 2;
        public static final int HALF_VISIBLE_HEIGHT = VISIBLE_HEIGHT / 2;
        public static final int SCROLLABLE_WIDTH = VISIBLE_WIDTH * 4;
        public static final int SCROLLABLE_HEIGHT = VISIBLE_HEIGHT * 2;
        
        // Hero
        Hero theHero;
    
        // Track whether game is on
        private boolean isGameOver;
        private int frames;
        private int score = 0;
        private int spaceShipScore =0;
        
        public boolean isPacmanDead = false;
        /**
         * Constructor for objects of class SideScrollingWorld.
         */
        public SideScrollingWorld()
        {    
            // Create a new world with 640x480 cells with a cell size of 1x1 pixels.
            // Final argument of 'false' means that actors in the world are not restricted to the world boundary.
            // See: https://www.greenfoot.org/files/javadoc/greenfoot/World.html#World-int-int-int-boolean-
            super(VISIBLE_WIDTH, VISIBLE_HEIGHT, 1, false);
    
            // Set up the starting scene
            setup();
            
            // Game on
            isGameOver = false;
            frames = 0;
            
        }
    
        /**
         * Set up the entire world.
         */
        private void setup()
        {
            //add ground on the far left side
            addLeftGround();
            //add fences
            addFences();
            //add stardestroyers (decoration)
            addStardestroyers();
            //add ground on far right side
            addRightGround();
            //add "metal plates" 
            for (int i = 0; i <=3; i ++)
            {
                addSteps(i);
            }
            //add platform for "upper world"
            addUpGround(SCROLLABLE_WIDTH/TILE_SIZE*2);
            //add hero
            addHero();
            //add enemy
            addEnemy();
            
        }
        private void addItems()
        {
            //add item(heart) randomly
            if (frames % 1800 == 0 || frames == 0)
            {
                int selection = Greenfoot.getRandomNumber(2);
                
                if (selection == 0)
                {
                    int x = 400 + 16 * (selection + 2) *16;
                    
                    addObject(new Heart(), x, 200);
                }
                else if (selection == 1)
                {
                    int x = 400 + 16 * (selection + 2) *16;
                    
                    addObject(new Heart(), x, 200);
                }
                else
                {
                    int x = 400 + 16 * (selection +2) *16;
                    
                    addObject(new Heart(), x, 200);
                }
            }
        
    }
    /**
     * Add enemies to the world
     */
    private void addEnemy()
    {
        
        for (int i = 0; i <= 3; i ++)
        {
            int x = 400 + 16 * i * 32;
            int y = 260;
            addObject(new Enemy(), x, y);
        }
        for (int i = 0; i <= 3; i ++)
        {
            int x = 656 + 16 * i * 32;
            int y = 196;
            addObject(new Enemy(), x, y);
        }
        //upper world
        for (int i = 0; i<= 5; i++)
        {   
            int x = TILE_SIZE * 12 + 32 * 12 * i;
            int y = -TILE_SIZE - TILE_SIZE ;
            addObject(new Spaceship(), x, y);
        }
        //add boss(pacman)
        addObject(new Pacman(), TILE_SIZE *3, - 3 *TILE_SIZE - HALF_TILE_SIZE -5);
    }
    /**
     * Add blocks to create the ground to walk on at bottom-left of scrollable world.
     */
    private void addLeftGround()
    {
        // How many tiles will cover the bottom of the initial visible area of screen?
        final int tilesToCreate = 5;
        
        // Loop to create and add the tile objects
        for (int i = 0; i < tilesToCreate; i += 1)
        {
            // Add ground objects at bottom of screen
            // NOTE: Actors are added based on their centrepoint, so the math is a bit trickier.
            int x = i * TILE_SIZE * 2 + TILE_SIZE;
            int y = getHeight() - TILE_SIZE / 2;

            // Create a ground tile
            Ground groundTile = new Ground(x, y);

            // Add the objects
            addObject(groundTile, x, y);
        }
        
        
    }

    /**
     * Add some fences at left and right side.
     */
    private void addFences()
    {
        // Three fences on left side of world
        int x = TILE_SIZE / 2 + TILE_SIZE * 5;
        int y = VISIBLE_HEIGHT - TILE_SIZE / 2 - TILE_SIZE;
        Fence fence1 = new Fence(x, y);
        addObject(fence1, x, y);

        x = TILE_SIZE / 2 + TILE_SIZE * 6;
        y = VISIBLE_HEIGHT - TILE_SIZE / 2 - TILE_SIZE;        
        Fence fence2 = new Fence(x, y);
        addObject(fence2, x, y);

        x = TILE_SIZE / 2 + TILE_SIZE * 7;
        y = VISIBLE_HEIGHT - TILE_SIZE / 2 - TILE_SIZE;
        Fence fence3 = new Fence(x, y);
        addObject(fence3, x, y);

        // Two fences on right side of world
        x = SCROLLABLE_WIDTH - TILE_SIZE / 2 - TILE_SIZE * 3;
        y = VISIBLE_HEIGHT / 2;
        Fence fence4 = new Fence(x, y);
        addObject(fence4, x, y);

        x = SCROLLABLE_WIDTH - TILE_SIZE / 2 - TILE_SIZE * 4;
        y = VISIBLE_HEIGHT / 2;
        Fence fence5 = new Fence(x, y);
        addObject(fence5, x, y);
    }
    /**
     * add Ground for upper part of Scrollable world
     */
    private void addUpGround(int howMany)
    {
        for (int i = 0; i <= howMany; i++)
        {
            int x = i * TILE_SIZE * 2 + TILE_SIZE;
            int y = -HALF_TILE_SIZE ; 
            Ground ground = new Ground(x,y);
            addObject(ground, x, y);
        }
        for (int j = 1; j <= howMany/14; j++)
        {
            for (int i = 1; i <= 3; i++)
            {
                int x = 7 * TILE_SIZE + TILE_SIZE * 12 * j;
                int y = -HALF_TILE_SIZE -TILE_SIZE * i;
                addObject(new MetalPlate(x,y),x,y);
            }
        }
    }
    /**
     * Add steps made out of metal plates leading to end of world.
     */
    private void addSteps(int howFar)
    {
        

        // Add groups of plates
        for (int j = 1; j <=4; j++)
        {
            
            for (int i = 1; i <= 3; i +=1)
            {
                int x =  HALF_TILE_SIZE + i * TILE_SIZE + 5 *TILE_SIZE + 4 * j * TILE_SIZE + 16* howFar *TILE_SIZE;
                int y = VISIBLE_HEIGHT - HALF_TILE_SIZE - 3 * TILE_SIZE - j * TILE_SIZE;
                MetalPlate plate = new MetalPlate(x,y);
                addObject(plate, x, y);
            }
        }
        
    }

    /**
     * Add a few clouds for the opening scene.
     */
    private void addStardestroyers()
    {
        Stardestroyer st1 = new Stardestroyer(170, 125);
        addObject(st1, 170, 125);
        Stardestroyer st2 = new Stardestroyer(450, 175);
        addObject(st2, 450, 175);
        Stardestroyer st3 = new Stardestroyer(775, 50);
        addObject(st3, 775, 50);
        Stardestroyer st4 = new Stardestroyer(300, 230);
        addObject(st4, 300, 230);
    }

    /**
     * Act
     * 
     * This method is called approximately 60 times per second.
     */
    public void act()
    {
        //playing sound
        if (frames % 9360 == 0 || frames == 0)
        {
            Greenfoot.playSound("a.mp3");
        }
        //show GO!
        if (frames == 0)
        {
            addObject(new Go(),VISIBLE_WIDTH/2,VISIBLE_HEIGHT/2); 
            //System.out.println("w");
        } 
        addItems();
        checkWin();
        frames++;
    }

    /**
     * Add the hero to the world.
     */
    private void addHero()
    {
        // Initial horizontal position
        int initialX = getWidth() - 5 * getWidth() / 6;
        int initialY = getHeight() / 4 * 3;
        // Instantiate the hero object
        theHero = new Hero(initialX, initialY);

        // Add hero in bottom left corner of screen
        addObject(theHero, initialX, getHeight() / 4 * 3);
    }

    /**
     * Add blocks to create the ground to walk on at top-right of scrollable world.
     */
    private void addRightGround()
    {
        // Constants to control dimensions of the ground at end of world
        final int COUNT_OF_GROUND = 3;
        final int GROUND_BELOW_COLUMNS = COUNT_OF_GROUND * 2 ;
        final int GROUND_BELOW_ROWS = 6;
        final int COUNT_OF_GROUND_BELOW = GROUND_BELOW_COLUMNS * GROUND_BELOW_ROWS;
        
        // Make portal
        int X = SCROLLABLE_WIDTH - TILE_SIZE * 2;
        int Y = TILE_SIZE + TILE_SIZE ;
        Portal portal = new Portal(X, Y);
        addObject(portal, X, Y);
        
        // 1. Make ground at end of level (top layer)
        for (int i = 0; i < COUNT_OF_GROUND; i += 1)
        {
            // Position in wider scrollable world
            int x = SCROLLABLE_WIDTH - TILE_SIZE - i * TILE_SIZE * 2;
            int y = VISIBLE_HEIGHT / 2 + TILE_SIZE ;

            // Create object and add it
            Ground ground = new Ground(x, y);
            addObject(ground, x, y);
        }

        // 2. Make sub-ground at end of level (below top layer)
        for (int i = 0; i < GROUND_BELOW_COLUMNS; i += 1)
        {
            for (int j = 0; j < GROUND_BELOW_ROWS; j += 1)
            {
                // Position in wider scrollable world
                int x = SCROLLABLE_WIDTH - TILE_SIZE / 2 - i * TILE_SIZE;
                int y = VISIBLE_HEIGHT / 2 + TILE_SIZE + TILE_SIZE * (j + 1);

                // Create object and add it
                GroundBelow groundBelow = new GroundBelow(x, y);
                addObject(groundBelow, x, y);
            }
        }
    }

    /**
     * Return an object reference to the hero.
     */
    public Hero getHero()
    {
        return theHero;
    }
    private void checkWin()
    {
        if(isPacmanDead == true)
        {
            addObject(new Game(),VISIBLE_WIDTH/2, VISIBLE_HEIGHT/2); 
            showText("You killed " + score +" UFOs", 300, 20);
            showText("You destroyed " + spaceShipScore +" spaceships", 300, 50);
            Greenfoot.stop();
        }
    }

    /**
     * Set game over
     */
    public void setGameOver()
    {
        isGameOver = true;
    }
    public void addScore()
    {
        score ++;
    }
    public void addScoreSpaceShip()
    {
        spaceShipScore ++;
    }
}

