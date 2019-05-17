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
    private static final int TILE_SIZE = 32;
    private static final int HALF_TILE_SIZE = TILE_SIZE/2;
    // World size constants
    private static final int VISIBLE_WIDTH = 640;
    private static final int VISIBLE_HEIGHT = 480;
    public static final int HALF_VISIBLE_WIDTH = VISIBLE_WIDTH / 2;
    public static final int HALF_VISIBLE_HEIGHT = VISIBLE_HEIGHT / 2;
    public static final int SCROLLABLE_WIDTH = VISIBLE_WIDTH * 4;
    public static final int SCROLLABLE_HEIGHT = VISIBLE_HEIGHT * 2;

    // Hero
    Hero theHero;

    // Track whether game is on
    private boolean isGameOver;

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
    }

    /**
     * Set up the entire world.
     */
    private void setup()
    {
        
        addLeftGround();
        addFences();
        addMetalPlateSteps();
        addClouds();
        addRightGround();
        
       
        addHero();
        /**
        // ground
        for (int i = 0; i <= 10; i +=1)
        {
            int x =  HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 14*TILE_SIZE + HALF_TILE_SIZE;
            Ground ground = new Ground(x,y);
            addObject(ground, x, y);
        }
        for (int i = 0; i <= 4; i +=1)
        {
            int x =  55*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 11*TILE_SIZE + HALF_TILE_SIZE;
            Ground ground = new Ground(x,y);
            addObject(ground, x, y);
        }
        for (int i = 0; i <= 4; i +=1)
        {
            int x =  55*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 12*TILE_SIZE + HALF_TILE_SIZE;
            Ground ground = new Ground(x,y);
            addObject(ground, x, y);
        }
        for (int i = 0; i <= 4; i +=1)
        {
            int x =  55*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 13*TILE_SIZE + HALF_TILE_SIZE;
            Ground ground = new Ground(x,y);
            addObject(ground, x, y);
        }
        for (int i = 0; i <= 4; i +=1)
        {
            int x =  55*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 14*TILE_SIZE + HALF_TILE_SIZE;
            Ground ground = new Ground(x,y);
            addObject(ground, x, y);
        }
        //fence
        for (int i = 0; i <= 3; i +=1)
        {
            int x = 2*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 13*TILE_SIZE + HALF_TILE_SIZE;
            Fence fence = new Fence(x,y);
            addObject(fence, x, y);
        }
        //Metal Plate
        for (int i = 0; i <= 2; i +=1)
        {
            int x =  12*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 13*TILE_SIZE + HALF_TILE_SIZE;
            MetalPlate metalPlate = new MetalPlate(x,y);
            addObject(metalPlate, x, y);
        }
        for (int i = 0; i <= 1; i +=1)
        {
            int x =  16*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 12*TILE_SIZE + HALF_TILE_SIZE;
            MetalPlate metalPlate = new MetalPlate(x,y);
            addObject(metalPlate, x, y);
        }
        for (int i = 0; i <= 0; i +=1)
        {
            int x =  19*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 11*TILE_SIZE + HALF_TILE_SIZE;
            MetalPlate metalPlate = new MetalPlate(x,y);
            addObject(metalPlate, x, y);
        }
        for (int i = 0; i <= 2; i +=1)
        {
            int x =  21*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 10*TILE_SIZE + HALF_TILE_SIZE;
            MetalPlate metalPlate = new MetalPlate(x,y);
            addObject(metalPlate, x, y);
        }
        for (int i = 0; i <= 7; i +=1)
        {
            int x =  25*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 9*TILE_SIZE + HALF_TILE_SIZE;
            MetalPlate metalPlate = new MetalPlate(x,y);
            addObject(metalPlate, x, y);
        }
        for (int i = 0; i <= 3; i +=1)
        {
            int x =  27*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 8*TILE_SIZE + HALF_TILE_SIZE;
            MetalPlate metalPlate = new MetalPlate(x,y);
            addObject(metalPlate, x, y);
        }
        for (int i = 0; i <= 0; i +=1)
        {
            int x =  34*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 10*TILE_SIZE + HALF_TILE_SIZE;
            MetalPlate metalPlate = new MetalPlate(x,y);
            addObject(metalPlate, x, y);
        }
        for (int i = 0; i <= 3; i +=1)
        {
            int x =  34*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 14*TILE_SIZE + HALF_TILE_SIZE;
            MetalPlate metalPlate = new MetalPlate(x,y);
            addObject(metalPlate, x, y);
        }
        for (int i = 0; i <= 3; i +=1)
        {
            int x =  36*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 9*TILE_SIZE + HALF_TILE_SIZE;
            MetalPlate metalPlate = new MetalPlate(x,y);
            addObject(metalPlate, x, y);
        }
        for (int i = 0; i <= 2; i +=1)
        {
            int x =  42*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 8*TILE_SIZE + HALF_TILE_SIZE;
            MetalPlate metalPlate = new MetalPlate(x,y);
            addObject(metalPlate, x, y);
        }
        for (int i = 0; i <= 2; i +=1)
        {
            int x =  47*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 7*TILE_SIZE + HALF_TILE_SIZE;
            MetalPlate metalPlate = new MetalPlate(x,y);
            addObject(metalPlate, x, y);
        }
        for (int i = 0; i <= 0; i +=1)
        {
            int x =  51*TILE_SIZE + HALF_TILE_SIZE + i * TILE_SIZE;
            int y = 5*TILE_SIZE + HALF_TILE_SIZE;
            MetalPlate metalPlate = new MetalPlate(x,y);
            addObject(metalPlate, x, y);
        }
        **/
    }

    /**
     * Add blocks to create the ground to walk on at bottom-left of scrollable world.
     */
    private void addLeftGround()
    {
        // How many tiles will cover the bottom of the initial visible area of screen?
        final int tilesToCreate = getWidth() / TILE_SIZE;
        final int laddersToCreate = getHeight() / TILE_SIZE;
        // Loop to create and add the tile objects
        for (int i = 0; i < tilesToCreate; i += 1)
        {
            // Add ground objects at bottom of screen
            // NOTE: Actors are added based on their centrepoint, so the math is a bit trickier.
            int x = i * TILE_SIZE + TILE_SIZE / 2;
            int y = getHeight() - TILE_SIZE / 2;

            // Create a ground tile
            Ground groundTile = new Ground(x, y);

            // Add the objects
            addObject(groundTile, x, y);
        }
        
        for (int i = 0; i < laddersToCreate; i++)
        {
            int x = 50;
            int y = getHeight() - HALF_TILE_SIZE * i - TILE_SIZE;
            Ladder ladder = new Ladder(x,y);
            addObject(ladder, x, y);
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
     * Add steps made out of metal plates leading to end of world.
     */
    private void addMetalPlateSteps()
    {
        // How many plates total?
        final int COUNT_OF_METAL_PLATES = 40;
        final int PLATES_PER_GROUP = 4;

        // Add groups of plates
        for (int i = 0; i < COUNT_OF_METAL_PLATES / PLATES_PER_GROUP; i += 1)
        {
            // Group of four metal plates all at same y position
            int y = VISIBLE_HEIGHT - TILE_SIZE / 2 * 3 - i * TILE_SIZE;

            // Add the individual plates in a given group
            for (int j = 0; j < PLATES_PER_GROUP; j += 1)
            {
                int x = VISIBLE_WIDTH + TILE_SIZE * 2 + TILE_SIZE * (i + j) + TILE_SIZE * 5 * i;
                MetalPlate plate = new MetalPlate(x, y);
                addObject(plate, x, y);
            }
        }
    }

    /**
     * Add a few clouds for the opening scene.
     */
    private void addClouds()
    {
        Cloud cloud1 = new Cloud(170, 125);
        addObject(cloud1, 170, 125);
        Cloud cloud2 = new Cloud(450, 175);
        addObject(cloud2, 450, 175);
        Cloud cloud3 = new Cloud(775, 50);
        addObject(cloud3, 775, 50);
    }

    /**
     * Act
     * 
     * This method is called approximately 60 times per second.
     */
    public void act()
    {
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
        final int COUNT_OF_GROUND = 8;
        final int GROUND_BELOW_COLUMNS = COUNT_OF_GROUND;
        final int GROUND_BELOW_ROWS = 6;
        final int COUNT_OF_GROUND_BELOW = GROUND_BELOW_COLUMNS * GROUND_BELOW_ROWS;

        // 1. Make ground at end of level (top layer)
        for (int i = 0; i < COUNT_OF_GROUND; i += 1)
        {
            // Position in wider scrollable world
            int x = SCROLLABLE_WIDTH - TILE_SIZE / 2 - i * TILE_SIZE;
            int y = VISIBLE_HEIGHT / 2 + TILE_SIZE;

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

    /**
     * Set game over
     */
    public void setGameOver()
    {
        isGameOver = true;
    }
}

