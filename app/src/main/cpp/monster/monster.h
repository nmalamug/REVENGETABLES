//
// Created by Harman Singh on 4/23/23.
//
#ifndef KNIGHTSLABYRINTH_MONSTER_H
#define KNIGHTSLABYRINTH_MONSTER_H

class Monster {
public:
    Monster(float x, float y, float speed, int windowWidth, int windowHeight);
    void update(float objectiveX, float objectiveY, float knightX, float knightY, float knightRadius, float knightSpeed);
    bool colliding(float kx, float ky, int rad);
    void doCollision();

    float getX() const;
    float getY() const;

    float y;
    float x;
private:
    float speed;
    int type;
    int frame;
    int collisionCounter;
    int collisionFocalX;
    int collisionFocalY;
    const int monsterRadius = 50;
    int collisionSpeed;
    const int windowWidth;
    const int windowHeight;
    float directionX;
    float directionY;
    void moveToObjective(float objective_x, float objective_y);
};



#endif //KNIGHTSLABYRINTH_MONSTER_H

