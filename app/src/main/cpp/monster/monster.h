//
// Created by Harman Singh on 4/23/23.
//
#ifndef KNIGHTSLABYRINTH_MONSTER_H
#define KNIGHTSLABYRINTH_MONSTER_H

class Monster {
public:
    Monster(float x, float y, float speed, int windowWidth, int windowHeight, int movementType, int knightType);
    void update(float objectiveX, float objectiveY, float knightX, float knightY, float knightRadius, float knightSpeed, int knightAbility);
    bool colliding(float kx, float ky, int rad);
    void doCollision();
    int getMovementType() const;
    int inObjective(float obj_x, float obj_y);
    int kickedOut();
    float getX() const;
    float getY() const;
    void hop(float objectiveX, float objectiveY);
    void diagonal();
    int getMonsterFrame();
    void doBomberAbility();
    float y;
    float x;
    float initialDirectionX;
    float initialDirectionY ;
    int knightType;
private:
    int hopCounter=0;
    float speed;
    int type;
    int walkingFramesUpTo = 1;
    int frame = 0;
    int timeAlive = 0;
    int collisionCounter;
    const int monsterRadius = 100;
    int collisionSpeed;
    const int windowWidth;
    const int windowHeight;
    float directionX;
    float directionY;
    int reachedCastle;
    int kicked;
    void moveToObjective(float objective_x, float objective_y);
    int movementType;
    bool hopState;

};



#endif //KNIGHTSLABYRINTH_MONSTER_H

