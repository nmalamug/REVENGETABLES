#include "monster.h"
#include <cmath>
#include <jni.h>
#include <memory>

// Monster class constructor
Monster::Monster(float x, float y, float speed, int windowWidth, int windowHt)
    : x(x), y(y), speed(speed), windowWidth(windowWidth), windowHeight(windowHt)
    {
    collisionCounter = 0;
    reachedCastle = 0;
    kicked = 0;
    }

// Update the monster's position
void Monster::update(float objectiveX, float objectiveY, float knightX, float knightY, float knightRadius, float knightSpeed) {
    if(colliding(knightX, knightY, knightRadius)){
        //Get the x and y directions of collision
        directionX = knightX - x;
        directionY = knightY - y;
        collisionSpeed = knightSpeed + 5;
        collisionCounter = 20;
    }
    if(collisionCounter > 1){
        doCollision();
        collisionCounter -=1;
    }else{
        moveToObjective(objectiveX, objectiveY);
    }
}

bool Monster::colliding(float kx, float ky, int rad){
    float dist = sqrt(pow(kx-x,2)+pow(ky-y,2));
    if(dist< (rad+monsterRadius)){
        return true;
    }
    return false;
}

void Monster::doCollision(){
    //If about to bounce off a wall move focal point to switch x direction
    if(x<monsterRadius ){
        x = 51;
        directionX = -directionX;
    }else if(x>(windowWidth-monsterRadius)){
        x = windowWidth-monsterRadius-1;
        directionX = -directionX;
    }

    float distance = std::sqrt(directionX * directionX + directionY * directionY);
    float normalized_x = directionX / distance;
    float normalized_y = directionY / distance;

    x -= normalized_x * collisionSpeed;
    y -= normalized_y * collisionSpeed;
    collisionSpeed = collisionSpeed*.90;
}

// Getter for the monster's x position
float Monster::getX() const {
    return x;
}

// Getter for the monster's y position
float Monster::getY() const {
    return y;
}

// Move the monster towards the objective coordinates
void Monster::moveToObjective(float objective_x, float objective_y) {
    float direction_x = objective_x - x;
    float direction_y = objective_y - y;

    float distance = std::sqrt(direction_x * direction_x + direction_y * direction_y);
    float normalized_x = direction_x / distance;
    float normalized_y = direction_y / distance;

    x += normalized_x * speed;
    y += normalized_y * speed;
}

// Counters for how many monsters reached objective or got kicked out
int Monster::inObjective(float obj_x, float obj_y) {
    float xBound1 = 0, xBound2 = obj_x * 2;
    if (getX() < xBound2 && getX() > xBound1 && getY() > obj_y) {
        reachedCastle++;
    }
    return reachedCastle;
}

int Monster::kickedOut() {
    if (getY() < 0) {
        kicked++;
    }
    return kicked;
}

// JNI functions
extern "C"
// Create a new Monster object and return its pointer
JNIEXPORT jlong JNICALL
Java_com_example_knightslabyrinth_MonsterView_createMonster(JNIEnv *env, jobject thiz,
                                                                   jfloat x, jfloat y, jfloat speed,
                                                                   jint windowWidth, jint windowHeight) {
    Monster *monster = new Monster(x, y, speed, windowWidth, windowHeight);
    return reinterpret_cast<jlong>(monster);
}
extern "C"
// Update the monster using its pointer
JNIEXPORT void JNICALL
Java_com_example_knightslabyrinth_MonsterView_updateMonster(JNIEnv *env, jobject thiz,
                                                                   jlong monster_ptr,
                                                                   jfloat objective_x,
                                                                   jfloat objective_y,
                                                                   jfloat knightX,
                                                                   jfloat knightY,
                                                                   jint radius,
                                                                   jfloat knightSpeed) {
    Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
    monster->update(objective_x, objective_y, knightX, knightY, radius, knightSpeed);
}

extern "C"
// Get the monster's x position using its pointer
JNIEXPORT jfloat JNICALL
Java_com_example_knightslabyrinth_MonsterView_getMonsterX(JNIEnv *env, jobject thiz,
                                                                 jlong monster_ptr) {
    Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
    return monster->getX();
}

// Delete the monster
extern "C"
JNIEXPORT void JNICALL
Java_com_example_knightslabyrinth_MonsterView_deleteC(JNIEnv *env, jobject,jlong ptr){
    delete (Monster *)(ptr);
}

// Get the monster's y position using its pointer
extern "C" JNIEXPORT jfloat JNICALL
Java_com_example_knightslabyrinth_MonsterView_getMonsterY(JNIEnv *env, jobject thiz,
                                                                 jlong monster_ptr) {
    Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
    return monster->getY();
}

// Get the monster's x position using its pointer for KnightView
extern "C" JNIEXPORT jfloat JNICALL
Java_com_example_knightslabyrinth_KnightView_getMonsterX(JNIEnv *env, jobject thiz, jlong monster_ptr) {
    Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
    return static_cast<jfloat>(monster->x);
}

// Get the monster's y position using its pointer for KnightView
extern "C" JNIEXPORT jfloat JNICALL
Java_com_example_knightslabyrinth_KnightView_getMonsterY(JNIEnv *env, jobject thiz, jlong monster_ptr) {
    Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
    return static_cast<jfloat>(monster->y);
}


extern "C"
JNIEXPORT jint JNICALL
Java_com_example_knightslabyrinth_MonsterView_inObj(JNIEnv *env, jobject thiz, jfloat obj_x,
                                                    jfloat obj_y, jlong monster_ptr) {
    // TODO: implement inObj()
    Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
    return monster -> inObjective(obj_x, obj_y);
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_knightslabyrinth_MonsterView_kick(JNIEnv *env, jobject thiz, jlong monster_ptr) {
    Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
    return monster -> kickedOut();
}