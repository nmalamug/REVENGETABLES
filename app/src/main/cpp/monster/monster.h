//
// Created by Harman Singh on 4/23/23.
//
#ifndef KNIGHTSLABYRINTH_MONSTER_H
#define KNIGHTSLABYRINTH_MONSTER_H

class Monster {
public:
    Monster(float x, float y, float speed);
    void update(float deltaTime, float objectiveX, float objectiveY);

    float getX() const;
    float getY() const;

    float y;
    float x;
private:
    float speed;

    void moveToObjective(float objective_x, float objective_y);
};



#endif //KNIGHTSLABYRINTH_MONSTER_H

