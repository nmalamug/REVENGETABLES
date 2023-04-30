//
// Created by Nicomug on 4/23/2023.
//
#include "../Point.h"
#ifndef KNIGHTSLABYRINTH_KNIGHT_H
#define KNIGHTSLABYRINTH_KNIGHT_H

class Knight {
public:
    Knight(double theX, double theY);
    void setTargetK(float theX, float theY);
    void move();
    void update(int moveOpt);
    void moveBomber();
    bool bomberAbilityReady();
    void doBomberAbility();
    void moveDasher();
    bool dasherAbilityReady();
    void doDasherAbility();
    float getX()const ;
    float getY()const ;
    float getSpeed() const;
    int getAbilityActive() const;
    int getCooldown()const;
    int cooldown;
    float dashStepX;
    float dashStepY;

    int abilityActive = 0;
    Point pos;
    Point target;
    float distance;
    float speed;
};


#endif //KNIGHTSLABYRINTH_KNIGHT_H
