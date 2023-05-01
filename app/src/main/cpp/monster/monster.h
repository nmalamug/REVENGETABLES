//
// Created by Harman Singh on 4/23/23.
//
#ifndef KNIGHTSLABYRINTH_MONSTER_H
#define KNIGHTSLABYRINTH_MONSTER_H
/**
 * Represents a Monster in the game.
 */
class Monster {

public:
    /**
      * Constructs a new Monster object.
      *
      * @param x The initial x-coordinate of the monster.
      * @param y The initial y-coordinate of the monster.
      * @param speed The speed of the monster.
      * @param windowWidth The width of the game window.
      * @param windowHeight The height of the game window.
      * @param movementType The movement behavior of the monster.
      * @param knightType The type of the knight the monster is interacting with.
      * @param difficulty The game difficulty level.
      */
    Monster(float x, float y, float speed, int windowWidth, int windowHeight, int movementType, int knightType, int difficulty);


    /**
     * Updates the state of the Monster.
     *
     * @param objectiveX The x-coordinate of the objective the monster is targeting.
     * @param objectiveY The y-coordinate of the objective the monster is targeting.
     * @param knightX The x-coordinate of the knight.
     * @param knightY The y-coordinate of the knight.
     * @param knightRadius The radius of the knight.
     * @param knightSpeed The speed of the knight.
     * @param knightAbility The ability type of the knight.
     */
    void update(float objectiveX, float objectiveY, float knightX, float knightY, float knightRadius, float knightSpeed, int knightAbility);


    /**
     * Checks if the Monster is colliding with a knight.
     *
     * @param kx The x-coordinate of the knight.
     * @param ky The y-coordinate of the knight.
     * @param rad The radius of the knight.
     * @return true if colliding, false otherwise.
     */
    bool colliding(float kx, float ky, int rad);


    /**
     * Handles the collision between the Monster and a knight.
     */
    void doCollision();


    /**
     * Gets the movement behavior of the monster.
     *
     * @return The movement type as an integer.
     */
    int getMovementType() const;


    /**
     * Counts how many monsters reached the bottom of the screen
     * @param obj_x The x-coordinate of the objective
     * @param obj_y The y-coordinate of the objective
     * @return The number of monsters that reached the objective
     */
    int inObjective(float obj_x, float obj_y);



    /**
     * Checks if the monster has been kicked out of the objective.
     *
     * @return increments if kicked out
     */
    int kickedOut();


    /**
     * Gets the x-coordinate of the monster.
     *
     * @return The x-coordinate as a float.
     */
    float getX() const;


    /**
     * Gets the y-coordinate of the monster.
     *
     * @return The y-coordinate as a float.
     */
    float getY() const;


    /**
     * Performs the hop action for the monster.
     *
     * @param objectiveX The x-coordinate of the objective.
     * @param objectiveY The y-coordinate of the objective.
     */
    void hop(float objectiveX, float objectiveY);


    /**
     * Performs the diagonal movement for the monster.
     */
    void diagonal();


    /**
    * animation frame as an integer.
    */
    int getMonsterFrame();


    /**
     * Performs the bomber ability for the monster.
     */
    void doBomberAbility();

    // The y-coordinate of the monster.
    float y;

    // The x-coordinate of the monster.
    float x;

    // The initial x-direction of the monster's movement.
    float initialDirectionX;

    // The initial y-direction of the monster's movement.
    float initialDirectionY ;

    // The type of the knight the monster is interacting with.
    int knightType;

    private:
    // Counter for the number of hops the monster has performed.
    int hopCounter=0;

    // The speed of the monster.
    float speed;

    // The type of movement behavior for the monster.
    int type;

    // The number of walking animation frames for the monster.
    int walkingFramesUpTo = 1;

    // The current frame of the monster's animation.
    int frame = 0;

    // The amount of time the monster has been alive.
    int timeAlive = 0;

    // Counter for tracking the number of collisions.
    int collisionCounter;

    // The radius of the monster.
    const int monsterRadius = 100;

    // The speed of the monster during a collision.
    int collisionSpeed;

    // The width of the game window.
    const int windowWidth;

    // The height of the game window.
    const int windowHeight;

    // The x-direction of the monster's movement.
    float directionX;

    // The y-direction of the monster's movement.
    float directionY;

    // Counter for tracking the number of times the monster reached the castle.
    int reachedCastle;

    // Counter for tracking the number of times the monster has been kicked out of the objective.
    int kicked;
    /**
     * Moves the monster towards the objective coordinates.
     *
     * @param objective_x The x-coordinate of the objective.
     * @param objective_y The y-coordinate of the objective.
     */
    void moveToObjective(float objective_x, float objective_y);

    // The movement behavior type of the monster.
    int movementType;

    // The difficulty level of the game.
    int difficulty;

    // The current state of the hop action (true if the monster is hopping, false otherwise).
    bool hopState;

    };

#endif //KNIGHTSLABYRINTH_MONSTER_H

