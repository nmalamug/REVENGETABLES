//
// Created by Nicomug on 4/23/2023.
//
#include "../Point.h"
#ifndef KNIGHTSLABYRINTH_KNIGHT_H
#define KNIGHTSLABYRINTH_KNIGHT_H

class Knight {
public:
    /**
     * Creates a Knight with the specified position
     * @param theX the starting x position
     * @param theX the starting y position
     */
    Knight(double theX, double theY);

    /**
     * Gives the knight the input position of the player
     * @param theX the x position of input
     * @param theY the y position of input
     */
    void setTargetK(float theX, float theY);

    /**
     * Moves the knight by an amount based on the knight's position,
     * @example if knight position if (0,0) and target is (90,0), knight will move to position (50,0)
     */
    void move();

    /**
     * Calls the function that update knight state and position based on the knight selected in settings.
     * @param moveOpt An integer, represents the type knight the player selected.
     */
    void update(int moveOpt);

    /**
     * Move option for the bomber. Similar to the other move options, but also checks for the ability.
     */
    void moveBomber();

    /**
     * Checks to see if player intends to activate bomber ability, by using current speed, and checks cooldown.
     * @return If the ability should be activated or not.
     */
    bool bomberAbilityReady();

    /**
     * Sets a cooldown and activates the bomber ability for 3 ticks
     */
    void doBomberAbility();

    /**
     * Move option for the dasher. Faster than the bomber.
     */
    void moveDasher();

    /**
     * Checks to see if a player intends to use the bomber ability, and checks the cooldown.
     * @return
     */
    bool dasherAbilityReady();

    /**
     * Sets a cooldown and activates the dasher ability for 10 ticks.
     */
    void doDasherAbility();

    /**
     * Getter for the x postion of the knight
     * @return the x position of the knight
     */
    float getX()const ;

    /**
     * Getter for the y position of the knight
     * @return the y position of the knight
     */
    float getY()const ;

    /**
     * Getter for the speed of the knight, calculated in move.
     * @return the speed of the knight
     */
    float getSpeed() const;

    /**
     * Getter for the ability timer
     * @return the ability active timer.
     */
    int getAbilityActive() const;

    /**
     * Getter for the cooldown
     * @return the number of ticks until the ability is ready again
     */
    int getCooldown()const;

    /**
     * Represents the number of ticks until the ability is ready
     */
    int cooldown;

    /**
     * How far the dash ability should go per tick in the x and y directions
     */
    float dashStepX;
    float dashStepY;

    /**
     * Timer that counts down from start of abilty use
     */
    int abilityActive = 0;

    /**
     * Point variable for position of the knight
     */
    Point pos;

    /**
     * Point variable for the player input
     */
    Point target;

    /**
     * Variable for the distance between the knight and the player input
     */
    float distance;

    /**
     * Variable for the speed of the knight
     */
    float speed;
};


#endif //KNIGHTSLABYRINTH_KNIGHT_H
