//
// Created by Harman Singh on 4/23/23.
//
#include <cmath>
#include "../Point.h"
#ifndef KNIGHTSLABYRINTH_MONSTER_H
#define KNIGHTSLABYRINTH_MONSTER_H
class Monster {
public:
    // Constructor
    Monster(float x, float y, float speed);

    // Spawning method
    void spawn(float spawn_x, float spawn_y);

    // Move to objective method
    void moveToObjective(float objective_x, float objective_y);

    // Update method
    void update(float deltaTime, float objectiveX, float objectiveY);

private:
    float x;
    float y;
    float speed;
};

#endif //KNIGHTSLABYRINTH_MONSTER_H

