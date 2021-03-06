
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * This is the class for the "main character" in the action.
 * 
 * @author R. Gordon
 * @version May 8, 2019
 */
public class Hero extends Actor
{
    /**
     * Instance variables
     * 
     * These are available for use in any method below.
     */    
    //referece from the world
    SideScrollingWorld world = (SideScrollingWorld) getWorld(); 
    
    private boolean touchingLeft = false;
    private boolean touchingRight = false;
    // Horizontal speed (change in horizontal position, or delta X)
    private int deltaX = 4;

    // Vertical speed (change in vertical position, or delta Y)
    private int deltaY = 4;
    
    //Vertical change
    private int changeY = world.VISIBLE_HEIGHT;
    //private int changeY = world.VISIBLE_HEIGHT - world.TILE_SIZE *2;
    
    private int moveUpTimes = 1;
    // Acceleration for falls
    private int acceleration = 1;

    // Strength of a jump
    private int jumpStrength = -18;

    // Track current theoretical position in wider "scrollable" world
    private int currentScrollableWorldXPosition;
    // Track current theoretical position in taller "scrollable" world
    private int currentScrollableWorldYPosition;
    // Track whether game is over or not
    private boolean isGameOver;
    
    //check if megaman touched the portal
    private boolean checkTouchingPortal = false;
    // Constants to track vertical direction
    private static final String JUMPING_UP = "up";
    private static final String JUMPING_DOWN = "down";
    private String verticalDirection;

    // Constants to track horizontal direction
    public static final String FACING_RIGHT = "right";
    public static final String FACING_LEFT = "left";
    private String horizontalDirection;

    // For walking animation
    private GreenfootImage walkingRightImages[];
    private GreenfootImage walkingLeftImages[];
    private static final int WALK_ANIMATION_DELAY = 8;
    private static final int COUNT_OF_WALKING_IMAGES = 2;
    private int walkingFrames;
    
    //crouching image
    private GreenfootImage crouching = new GreenfootImage("megaman-crouching.png");
    //check if megaman stopped
    private boolean ifStop;
    
    //check if megaman is crouching
    private boolean ifCrouch;
    
    //keep tracking the time 60*frames = one second
    private int frames;
    
    //Megaman's life
    private int life = 150;

    /**
     * Constructor
     * 
     * This runs once when the Hero object is created.
     */
    Hero(int startingX, int startingY)
    {
        // Set where hero begins horizontally
        currentScrollableWorldXPosition = startingX;
        // Set where hero begins vertically
        currentScrollableWorldYPosition = startingY;

        // Game on
        isGameOver = false;
        ifStop = true;
        ifCrouch = false;
        // First jump will be in 'down' direction
        verticalDirection = JUMPING_DOWN;

        // Facing right to start
        horizontalDirection = FACING_RIGHT;

        // Set image
        setImage("megaman-jump-down-right.png");

        // Initialize the 'walking' arrays
        walkingRightImages = new GreenfootImage[COUNT_OF_WALKING_IMAGES];
        walkingLeftImages = new GreenfootImage[COUNT_OF_WALKING_IMAGES];

        // Load walking images from disk
        for (int i = 0; i < walkingRightImages.length; i++)
        {
            walkingRightImages[i] = new GreenfootImage("megaman-walk-right-" + i + ".png");

            // Create left-facing images by mirroring horizontally
            walkingLeftImages[i] = new GreenfootImage(walkingRightImages[i]);
            walkingLeftImages[i].mirrorHorizontally();
        }

        // Track animation frames for walking
        walkingFrames = 0;
    }

    /**
     * Act - do whatever the Hero wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //show life of hero
        SideScrollingWorld world = (SideScrollingWorld) getWorld(); 
        //Show life
        int offScreenVerticalPosition = (world.getHeight() + this.getImage().getHeight() / 2);
        if (life > 0 && this.getY() < offScreenVerticalPosition)
        {
            world.showText("Life: "+life +"", 100,20); 
        }
        //check if meagaman should get damage from pacman     
        damageByPacman();
        //check if magaman has obstacle on his left
        touchPlatformLeft();
        //check if megaman has obstacle on his right
        touchPlatformRight();
        //check if megaman should fall
        checkFall();
        //make megaman not be able to move when he's crouching
        if (ifCrouch == false)
        {
            checkKeys();
        }
        //check if megaman is touching heart
        checkHeart();
        //check if player wants megaman to crouch
        checkCrouch();
        //check if player wants megaman to shoot
        checkIfShoot();
        //check if megaman is going to move up to "upper world"
        checkMoveUp();
        //check if megaman is on protal
        checkIfOnPortal();
        //chekc if megaman is touching portal
        checkPortal();
        //check if game is over
        if (!isGameOver)
        {
            checkGameOver();
        }
        //add frames
        frames++;
    }
    /**
     * Check if he is on portal
     */
    private void checkIfOnPortal()
    {
        Actor directlyUnder = getOneObjectAtOffset(0, getImage().getHeight() / 2, Portal.class);
        Actor frontUnder = getOneObjectAtOffset(getImage().getWidth() / 3, getImage().getHeight() / 2, Portal.class);
        Actor rearUnder = getOneObjectAtOffset(0 - getImage().getWidth() / 3, getImage().getHeight() / 2, Portal.class);
        if (directlyUnder == null && frontUnder == null && rearUnder == null)
        {
               // Not on a portal
        }
        
        else 
        {
            checkTouchingPortal = true;
        }
    }
    /**
     * Check if he is touching portal
     */
    private void checkPortal()
    {
        //add animation
        if (checkTouchingPortal == true && frames % 10 == 0 && moveUpTimes <=5)
        {
                moveUp(); 
                //stop objects to move down (stop moveUp())
                if (moveUpTimes == 5)
                {
                    checkTouchingPortal = false;
                    
                }
        }
        //change the image of megaman when it's moving up
        if (checkTouchingPortal == true)
        {
            if (frames % 2 == 0)
            {
                setImage("Empty.png");
            }
            else 
            {
                setImage("Item.png");
            }
        }
    }
    /**
     * Check if he got Heart
     */
    private void checkHeart()
    {
        if (isTouching(Heart.class))
        {
            life = life + 4;
        }
    }
    /**
     * Check if he got shot
     */
    private void checkHit()
    {
        if (isTouching(EnemyBullet.class))
        {
            life --;
        }
    }
    /**
     * Check if he crouched
     */
    private void checkCrouch()
    {   
      
            if (Greenfoot.isKeyDown("down"))
            {
                if (checkFacingRight())
                {
                    setImage("megaman-crouching.png");
                }
                else if (!checkFacingRight())
                {
                    setImage("megaman-crouching-left.png");
                }
                ifCrouch = true;
           }
           if (!Greenfoot.isKeyDown("down"))
           {
               ifCrouch = false;
           }
        
        
    }
    /**
     * Respond to keyboard action from the user.
     */
    private void checkKeys()
    {
        //shoot
        
        // Walking keys
        if (Greenfoot.isKeyDown("left") && !isGameOver && touchingLeft == false)
        {
            
            moveLeft();
            
        }
        else if (Greenfoot.isKeyDown("right") && !isGameOver && touchingRight == false)
        {
            
            moveRight();
            
        }     
        
        else 
        {
            // Standing still; reset walking animation
            walkingFrames = 0;
            //checkIfShoot();
        }
        
        // Jumping
        if (Greenfoot.isKeyDown("up") && !isGameOver && checkTouchingPortal == false)
        {
            // Only able to jump when on a solid object
            if (onPlatform())
            {
                jump();
            }
        }
    }
    
    /**
     * Should the hero be falling right now?
     */
    
    public boolean checkFacingRight()
    {
        if (horizontalDirection == FACING_RIGHT)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void checkIfShoot()
    {
        if (frames % 10 == 0)
        {
        
        
            if ( Greenfoot.isKeyDown("space") && !isGameOver)
            {
               if (checkFacingRight())
                {
                    setImage("megaman-gun-right.png");
                    Bullet newBullet = new Bullet(checkFacingRight());
                    getWorld().addObject(newBullet, getX()+32, getY()-3);
                    Greenfoot.playSound("Sample 5.wav");
                }
                else
                {
                    setImage("megaman-gun-left.png");
                    Bullet newBullet = new Bullet(checkFacingRight());
                    getWorld().addObject(newBullet, getX()-32, getY()-3);
                    Greenfoot.playSound("Sample 5.wav");
                }  
            }
           }
        
        
    }
    /**
     * check if megaman needs to fall
     */
    public void checkFall()
    {
        if (onPlatform() || checkTouchingPortal == true)
        {
            // Stop falling
            deltaY = 0;
            
            // Set image
            if (horizontalDirection == FACING_RIGHT && Greenfoot.isKeyDown("right") == false && Greenfoot.isKeyDown("space") == false && Greenfoot.isKeyDown("down") == false)
            {
                setImage("megaman-right.png");
                
            }
            else if (horizontalDirection == FACING_LEFT && Greenfoot.isKeyDown("left") == false && Greenfoot.isKeyDown("space") ==false && Greenfoot.isKeyDown("down") == false)
            {
                setImage("megaman-left.png");
                
            }

            // Get a reference to any object that's created from a subclass of Platform,
            // that is below (or just below in front, or just below behind) the hero
            Actor directlyUnder = getOneObjectAtOffset(0, getImage().getHeight() / 2, Platform.class);
            Actor frontUnder = getOneObjectAtOffset(getImage().getWidth() / 3, getImage().getHeight() / 2, Platform.class);
            Actor rearUnder = getOneObjectAtOffset(0 - getImage().getWidth() / 3, getImage().getHeight() / 2, Platform.class);

            // Bump the hero back up so that they are not "submerged" in a platform object
            
            if (directlyUnder != null)
            {
                int correctedYPosition = directlyUnder.getY() - directlyUnder.getImage().getHeight() / 2 - this.getImage().getHeight() / 2;
                setLocation(getX(), correctedYPosition);
            }
            if (frontUnder != null)
            {
                int correctedYPosition = frontUnder.getY() - frontUnder.getImage().getHeight() / 2 - this.getImage().getHeight() / 2;
                setLocation(getX(), correctedYPosition);
            }
            if (rearUnder != null)
            {
                int correctedYPosition = rearUnder.getY() - rearUnder.getImage().getHeight() / 2 - this.getImage().getHeight() / 2;
                setLocation(getX(), correctedYPosition);
            }
        }
        
        else
        {
            fall();
        }
    }

    /**
     * Is the hero currently touching a solid object? (any subclass of Platform)
     */
    public boolean onPlatform()
    {
        // Get an reference to a solid object (subclass of Platform) below the hero, if one exists
        Actor directlyUnder = getOneObjectAtOffset(0, getImage().getHeight() / 2, Platform.class);
        Actor frontUnder = getOneObjectAtOffset(getImage().getWidth() / 3, getImage().getHeight() / 2, Platform.class);
        Actor rearUnder = getOneObjectAtOffset(0 - getImage().getWidth() / 3, getImage().getHeight() / 2, Platform.class);

        // If there is no solid object below (or slightly in front of or behind) the hero...
        
        if (directlyUnder == null && frontUnder == null && rearUnder == null)
        {
            return false;   // Not on a solid object
        }
        
        else 
        {
            return true;
        }
        
    }
    /**
     * 
     */
    public void touchPlatformLeft()
    {
        Actor left = getOneObjectAtOffset(getImage().getWidth()/2 - 32, 0, Platform.class);
        
        if (left == null )
        {
              touchingLeft = false;// Not on a solid object
        }
        
        else 
        {
            touchingLeft = true;
        }
    }
    /**
     * check if megaman is facing the obstacle in the right
     */
    public void touchPlatformRight()
    {
        Actor right = getOneObjectAtOffset(getImage().getWidth()/2, 0, Platform.class);
        
        if (right == null )
        {
              touchingRight = false;// Not on a solid object
        }
        
        else 
        {
            touchingRight = true;
        }
    }
    

    /**
     * Make the hero jump.
     */
    public void jump()
    {
        // Track vertical direction
        verticalDirection = JUMPING_UP;

        // Set image
        if (horizontalDirection == FACING_RIGHT)
        {
            setImage("megaman-jump-up-right.png");
        }
        else
        {
            setImage("megaman-jump-up-left.png");
        }

        // Change the vertical speed to the power of the jump
        deltaY = jumpStrength;

        // Make the character move vertically 
        fall();
    }

    /**
     * Make the hero fall.
     */
    public void fall()
    {
        // See if direction has changed
        if (deltaY > 0)
        {
            verticalDirection = JUMPING_DOWN;

            // Set image
            if (Greenfoot.isKeyDown("space"))
            {
                if (horizontalDirection == FACING_RIGHT)
                {
                    setImage("megaman-gun-right.png");
                }
                else
                {
                    setImage("megaman-gun-left.png");
                }
            }
            else
            {
                if (horizontalDirection == FACING_RIGHT)
                {
                    setImage("megaman-jump-down-right.png");
                }
                else
                {
                    setImage("megaman-jump-down-left.png");
                }
            }
            
            
        }

        // Fall (move vertically)
        int newVisibleWorldYPosition = getY() + deltaY;
        setLocation(getX(), newVisibleWorldYPosition );

        // Accelerate (fall faster next time)
        deltaY = deltaY + acceleration;
        
    }

    /**
     * Animate walking
     */
    private void animateWalk(String direction)
    {
        // Track walking animation frames
        walkingFrames += 1;

        // Get current animation stage
        int stage = walkingFrames / WALK_ANIMATION_DELAY;

        // Animate
        if (stage < walkingRightImages.length)
        {
            // Set image for this stage of the animation
            if (direction == FACING_RIGHT)
            {
                setImage(walkingRightImages[stage]);
            }
            else
            {
                setImage(walkingLeftImages[stage]);
            }
        }
        else
        {
            // Start animation loop from beginning
            walkingFrames = 0;
        }
    }

    /**
     * Move the hero to the right.
     */
    public void moveRight()
    {
        // Track direction
        horizontalDirection = FACING_RIGHT;
       
        // Set image 
        if (onPlatform())
        {
            animateWalk(horizontalDirection);
        }
        else 
        {
            // Set appropriate jumping image
            if (verticalDirection == JUMPING_UP)
            {
                setImage("megaman-jump-up-right.png");
            }
            else
            {
                setImage("megaman-jump-down-right.png");
            }
        }

        // Get object reference to world
        SideScrollingWorld world = (SideScrollingWorld) getWorld(); 
        // Decide whether to actually move, or make world's tiles move
        if (currentScrollableWorldXPosition < world.HALF_VISIBLE_WIDTH)
        {
            // HERO IS WITHIN EXTREME LEFT PORTION OF SCROLLABLE WORLD
            // So... actually move the actor within the visible world.

            // Move to right in visible world
            int newVisibleWorldXPosition = getX() + deltaX;
            setLocation(newVisibleWorldXPosition, getY());

            // Track position in wider scrolling world
            currentScrollableWorldXPosition = getX();
        }
        else if (currentScrollableWorldXPosition + deltaX * 2 > world.SCROLLABLE_WIDTH - world.HALF_VISIBLE_WIDTH)
        {
            // HERO IS WITHIN EXTREME RIGHT PORTION OF SCROLLABLE WORLD
            // So... actually move the actor within the visible world.

            // Allow movement only when not at edge of world
            if (currentScrollableWorldXPosition < world.SCROLLABLE_WIDTH - this.getImage().getWidth() / 2)
            {
                // Move to right in visible world
                int newVisibleWorldXPosition = getX() + deltaX;
                setLocation(newVisibleWorldXPosition, getY());

                // Track position in wider scrolling world
                currentScrollableWorldXPosition += deltaX;
            }
        }
        else
        {
            // HERO IS BETWEEN LEFT AND RIGHT PORTIONS OF SCROLLABLE WORLD
            // So... we move the other objects to create illusion of hero moving

            // Track position in wider scrolling world
            currentScrollableWorldXPosition += deltaX;
            //List Pacman
            //List spaceships
            List<Pacman> pacmans = world.getObjects(Pacman.class);
            
            for (Pacman pacman : pacmans)
            {
                pacman.moveLeft(deltaX);
            }
            //List spaceships
            List<Spaceship> spaceships = world.getObjects(Spaceship.class);
            
            for (Spaceship spaceship : spaceships)
            {
                spaceship.moveLeft(deltaX);
            }
            //Get a list of all enemies
            List<Enemy> enemies = world.getObjects(Enemy.class);
            for (Enemy enemy : enemies)
            {
                enemy.moveLeft(deltaX);
            }
            
            // Get a list of all platforms (objects that need to move
            // to make hero look like they are moving)
            List<Platform> platforms = world.getObjects(Platform.class);
            
            // Move all the platform objects to make it look like hero is moving
            for (Platform platform : platforms)
            {
                // Platforms move left to make hero appear to move right
                platform.moveLeft(deltaX);
            }

            // Get a list of all decorations (objects that need to move
            // to make hero look like they are moving)
            List<Decoration> decorations = world.getObjects(Decoration.class);

            // Move all the decoration objects to make it look like hero is moving
            for (Decoration decoration: decorations)
            {
                // Platforms move left to make hero appear to move right
                decoration.moveLeft(deltaX);
            }

            // Get a list of all farAwayItems (objects that need to move
            // to make hero look like they are moving)
            List<FarAwayItem> farAwayItems = world.getObjects(FarAwayItem.class);

            // Move all the tile objects to make it look like hero is moving
            for (FarAwayItem farAwayItem : farAwayItems)
            {
                // FarAwayItems move left to make hero appear to move right
                farAwayItem.moveLeft(deltaX / 4);
            }
            //get list of all Items
            List<Item> items = world.getObjects(Item.class);
            //move all the Item objects to make it look like hero is moving
            for (Item item : items)
            {
                item.moveLeft(deltaX);
            }

        }   
        //horizontalDirection = FACING_RIGHT;
    }

    /**
     * Move the hero to the left.
     */
    public void moveLeft()
    {
        // Track direction
        horizontalDirection = FACING_LEFT;

        // Set image 
        if (onPlatform())
        {
            animateWalk(horizontalDirection);
        }
        else
        {
            // Set appropriate jumping image
            if (verticalDirection == JUMPING_UP)
            {
                setImage("megaman-jump-up-left.png");
            }
            else
            {
                setImage("megaman-jump-down-left.png");
            }
        }

        // Get object reference to world
        SideScrollingWorld world = (SideScrollingWorld) getWorld(); 

        // Decide whether to actually move, or make world's tiles move
        if (currentScrollableWorldXPosition - deltaX < world.HALF_VISIBLE_WIDTH)
        {
            // HERO IS WITHIN EXTREME LEFT PORTION OF SCROLLABLE WORLD
            // So... actually move the actor within the visible world.

            // Don't let hero go off left edge of scrollable world 
            // (Allow movement only when not at left edge)
            if (currentScrollableWorldXPosition > 0)
            {
                // Move left in visible world
                int newVisibleWorldXPosition = getX() - deltaX;
                setLocation(newVisibleWorldXPosition, getY());

                // Track position in wider scrolling world
                currentScrollableWorldXPosition = getX();
            }            
        }
        else if (currentScrollableWorldXPosition + deltaX * 2 > world.SCROLLABLE_WIDTH - world.HALF_VISIBLE_WIDTH)
        {
            // HERO IS WITHIN EXTREME RIGHT PORTION OF SCROLLABLE WORLD
            // So... actually move the actor within the visible world.

            // Move left in visible world
            int newVisibleWorldXPosition = getX() - deltaX;
            setLocation(newVisibleWorldXPosition, getY());

            // Track position in wider scrolling world
            currentScrollableWorldXPosition -= deltaX;
        }        
        else
        {
            // HERO IS BETWEEN LEFT AND RIGHT PORTIONS OF SCROLLABLE WORLD
            // So... we move the other objects to create illusion of hero moving

            // Track position in wider scrolling world
            currentScrollableWorldXPosition -= deltaX;
            //List spaceships
            List<Pacman> pacmans = world.getObjects(Pacman.class);
            
            for (Pacman pacman : pacmans)
            {
                pacman.moveRight(deltaX);
            }
            //List spaceships
            List<Spaceship> spaceships = world.getObjects(Spaceship.class);
            
            for (Spaceship spaceship : spaceships)
            {
                spaceship.moveRight(deltaX);
            }
            
            //List enemies
            List<Enemy> enemies = world.getObjects(Enemy.class);
            
            for (Enemy enemy : enemies)
            {
                enemy.moveRight(deltaX);
            }
            // Get a list of all platforms (objects that need to move
            // to make hero look like they are moving)
            List<Platform> platforms = world.getObjects(Platform.class);

            // Move all the platform objects at same speed as hero
            for (Platform platform : platforms)
            {
                // Platforms move right to make hero appear to move left
                platform.moveRight(deltaX);
            }

            // Get a list of all decorations (objects that need to move
            // to make hero look like they are moving)
            List<Decoration> decorations = world.getObjects(Decoration.class);

            // Move all the decoration objects to make it look like hero is moving
            for (Decoration decoration: decorations)
            {
                // Platforms move right to make hero appear to move left
                decoration.moveRight(deltaX);
            }

            // Get a list of all items that are in the distance (far away items)
            List<FarAwayItem> farAwayItems = world.getObjects(FarAwayItem.class);

            // Move all the FarAwayItem objects at one quarter speed as hero to create depth illusion
            for (FarAwayItem farAwayItem : farAwayItems)
            {
                // FarAwayItems move right to make hero appear to move left
                farAwayItem.moveRight(deltaX / 4);
            }
            
            //get list of all Items
            List<Item> items = world.getObjects(Item.class);
            //move all the Item objects to make it look like hero is moving
            for (Item item : items)
            {
                item.moveRight(deltaX);
            }

        } 
        //horizontalDirection = FACING_LEFT;
    }
    /**
     * check if megaman should move up
     */
    private void checkMoveUp()
    {
        if (getY() == 49 && isTouching(Portal.class))
        {
            checkTouchingPortal = true;
            removeTouching(Portal.class);
        }
        
    }
    /**
     * Make other objects to go down, so it looks like megaman going up
     */
    private void moveUp()
    {
        SideScrollingWorld world = (SideScrollingWorld) getWorld();
    
        
        // Track position in wider scrolling world
        currentScrollableWorldYPosition -= changeY/4;
        //List spaceships
        List<Pacman> pacmans = world.getObjects(Pacman.class);
        
        for (Pacman pacman : pacmans)
        {
            pacman.moveDown(changeY/4);
        }
        //List spaceships
        List<Spaceship> spaceships = world.getObjects(Spaceship.class);
        
        for (Spaceship spaceship : spaceships)
        {
            spaceship.moveDown(changeY/4);
        }
        //List enemies
        List<Enemy> enemies = world.getObjects(Enemy.class);
        
        for (Enemy enemy : enemies)
        {
            enemy.moveDown(changeY/4);
        }
        // Get a list of all platforms (objects that need to move
        // to make hero look like they are moving)
        List<Platform> platforms = world.getObjects(Platform.class);

        // Move all the platform objects at same speed as hero
        for (Platform platform : platforms)
        {
            // Platforms move right to make hero appear to move left
            platform.moveDown(changeY/4);
        }

        // Get a list of all decorations (objects that need to move
        // to make hero look like they are moving)
        List<Decoration> decorations = world.getObjects(Decoration.class);

        // Move all the decoration objects to make it look like hero is moving
        for (Decoration decoration: decorations)
        {
            // Platforms move right to make hero appear to move left
            decoration.moveDown(changeY/4);
        }

        
        
        //get list of all Items
        List<Item> items = world.getObjects(Item.class);
        //move all the Item objects to make it look like hero is moving
        for (Item item : items)
        {
            item.moveDown(changeY/4);
        }

        
        moveUpTimes++;
        
    }
    
    
    /**
     * When the hero falls off the bottom of the screen,
     * game is over. We must remove them.
     */
    public void checkGameOver()
    {
        // Get object reference to world
        SideScrollingWorld world = (SideScrollingWorld) getWorld(); 
        
        // Vertical position where hero no longer visible
        int offScreenVerticalPosition = (world.getHeight() + this.getImage().getHeight() / 2);
    
        // Off bottom of screen?
        if (this.getY() > offScreenVerticalPosition)
        {
            // Remove the hero
            isGameOver = true;
            world.setGameOver();
            world.removeObject(this);
            world.sound().stop();
            
            world.sound1().play();
            // Tell the user game is over
            world.addObject(new Fail(), world.getWidth() / 2, world.getHeight() / 2);
            world.showText("Life: "+ 0 +"", 100,20);
            Greenfoot.stop();
        }
        //life is 0?
        if (life <= 0)
        {
            isGameOver = true;
            //remove the hero
            world.setGameOver();
            world.removeObject(this);
            //change sound
            world.sound().stop();
            world.sound1().play();
            // Tell the user game is over
            world.addObject(new Fail(), world.getWidth() / 2, world.getHeight() / 2);
            world.showText("Life: "+ 0 +"", 100,20); 
            Greenfoot.stop();
        }
    }
    
    /**
     * Decrease lives
     */
    public void decreaseLives()
    {
        life--;
    }
    /**
     * decrease life by FireBall
     */
    public void decreaseLivesFireBall()
    {
        life-=3;
    }
    /**
     * check if it's touching pacman and decrease the life
     */
    private void damageByPacman()
    {
        if(isTouching(Pacman.class))
        {
            life -= 5;
        }
    }
}

