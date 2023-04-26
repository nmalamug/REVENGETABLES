#include "monster.h"
#include <cmath>
#include <jni.h>
#include <memory>

// Monster class constructor
Monster::Monster(float x, float y, float speed) : x(x), y(y), speed(speed) {}

// Update the monster's position
void Monster::update(float deltaTime, float objectiveX, float objectiveY) {
    moveToObjective(objectiveX, objectiveY);
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

// JNI functions
extern "C"
// Create a new Monster object and return its pointer
JNIEXPORT jlong JNICALL
Java_com_example_knightslabyrinth_GameScreenFragment_createMonster(JNIEnv *env, jobject thiz,
                                                                   jfloat x, jfloat y, jfloat speed) {
    Monster *monster = new Monster(x, y, speed);
    return reinterpret_cast<jlong>(monster);
}
extern "C"
// Update the monster using its pointer
JNIEXPORT void JNICALL
Java_com_example_knightslabyrinth_GameScreenFragment_updateMonster(JNIEnv *env, jobject thiz,
                                                                   jlong monster_ptr, jfloat delta_time,
                                                                   jfloat objective_x,
                                                                   jfloat objective_y) {
    Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
    monster->update(delta_time, objective_x, objective_y);
}
extern "C"
// Get the monster's x position using its pointer
JNIEXPORT jfloat JNICALL
Java_com_example_knightslabyrinth_GameScreenFragment_getMonsterX(JNIEnv *env, jobject thiz,
                                                                 jlong monster_ptr) {
    Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
    return monster->getX();
}

// Get the monster's y position using its pointer
extern "C" JNIEXPORT jfloat JNICALL
Java_com_example_knightslabyrinth_GameScreenFragment_getMonsterY(JNIEnv *env, jobject thiz,
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

