//
// Created by Harman Singh on 4/28/23.
//
// JNI functions

#include "monster/monster.cpp"
#include <cmath>
#include <jni.h>
#include <memory>
#include "Knight/Knight.h"


//Monster Controls

/**
 * @ Creates a new Monster object and returns its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @param x Initial x-coordinate of the monster
 * @param y Initial y-coordinate of the monster
 * @param speed Speed of the monster
 * @param windowWidth Width of the game window
 * @param windowHeight Height of the game window
 * @param movementType Movement Type of the monster
 * @param knightType Type of the knight the monster is interacting with
 * @param difficulty Game difficulty level
 * @return jlong Pointer to the created Monster object
 */
extern "C"
jlong JNIEXPORT
Java_com_example_knightslabyrinth_MonsterView_createMonster(JNIEnv *env, jobject ,
                            jfloat x, jfloat y, jfloat speed,
                            jint windowWidth, jint windowHeight,
                            jint movementType,jint knightType,
                            jint difficulty) {
    Monster *monster = new Monster(x, y, speed, windowWidth, windowHeight, movementType, knightType, difficulty);
    return reinterpret_cast<jlong>(monster);
}


/**
 * @ Updates the state of the Monster object using its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @param monster_ptr Pointer to the Monster object
 * @param objective_x X-coordinate of the objective the monster is targeting
 * @param objective_y Y-coordinate of the objective the monster is targeting
 * @param knightX X-coordinate of the knight
 * @param knightY Y-coordinate of the knight
 * @param radius Radius of the knight
 * @param knightSpeed Speed of the knight
 * @param knightAbility Ability type of the knight
 */
 extern "C"
JNIEXPORT void JNICALL
Java_com_example_knightslabyrinth_MonsterView_updateMonster(JNIEnv *env, jobject ,
                            jlong monster_ptr, jfloat objective_x,
                            jfloat objective_y,jfloat knightX,
                            jfloat knightY, jint radius, jfloat knightSpeed,
                            jint knightAbility) {
Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
monster->update(objective_x, objective_y, knightX,
                knightY, radius, knightSpeed, knightAbility);
}

/**
 * Gets the x-coordinate of the Monster object using its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @param monster_ptr Pointer to the Monster object
 * @return jfloat X-coordinate of the Monster object
 */
 extern "C"
JNIEXPORT jfloat JNICALL
        Java_com_example_knightslabyrinth_MonsterView_getMonsterX(JNIEnv *env, jobject ,
                                  jlong monster_ptr) {
Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
return monster->getX();
}

/**
 * @ Gets the y-coordinate of the Monster object using its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @param monster_ptr Pointer to the Monster object
 * @return jfloat Y-coordinate of the Monster object
 */
 extern "C" JNIEXPORT jfloat JNICALL
        Java_com_example_knightslabyrinth_MonsterView_getMonsterY(JNIEnv *env, jobject,
                                  jlong monster_ptr) {
Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
return monster->getY();
}


/**
 * @ Gets the x-coordinate of the Monster object for the KnightView using its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @param monster_ptr Pointer to the Monster object
 * @return jfloat X-coordinate of the Monster object
 */
 extern "C" JNIEXPORT jfloat JNICALL
        Java_com_example_knightslabyrinth_KnightView_getMonsterX(JNIEnv *env, jobject, jlong monster_ptr) {
Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
return static_cast<jfloat>(monster->x);
}

/**
 * @ Gets the y-coordinate of the Monster object for the KnightView using its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @param monster_ptr Pointer to the Monster object
 * @return jfloat Y-coordinate of the Monster object
 */
 extern "C" JNIEXPORT jfloat JNICALL
        Java_com_example_knightslabyrinth_KnightView_getMonsterY(JNIEnv *env, jobject , jlong monster_ptr) {
Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
return static_cast<jfloat>(monster->y);
}

/**
 * @ Gets the movement behavior type of the Monster object using its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @param monster_ptr Pointer to the Monster object
 * @return jint Movement Type of the Monster object
 */
extern "C" JNIEXPORT jint JNICALL
        Java_com_example_knightslabyrinth_MonsterView_getMovementType(JNIEnv *env, jobject , jlong monster_ptr) {
Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
return monster->getMovementType();
}

/**
 * @ Gets the current animation frame of the Monster object using its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @param monster_ptr Pointer to the Monster object
 * @return jint Current animation frame of the Monster object
 */
extern "C" JNIEXPORT jint JNICALL
Java_com_example_knightslabyrinth_MonsterView_getMonsterFrameC(JNIEnv *env, jobject ,
                                           jlong monster_ptr) {
    Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
    return monster->getMonsterFrame();
}

/**
 * @ Deletes the Monster object using its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @param ptr Pointer to the Monster object
 */extern "C"
JNIEXPORT void JNICALL
Java_com_example_knightslabyrinth_MonsterView_deleteC(JNIEnv *env, jobject,jlong ptr) {
    delete (Monster *) (ptr);
}

/**
 * @ Determines the number of times the Monster object has entered the objective using its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @param obj_x X-coordinate of the objective
 * @param obj_y Y-coordinate of the objective
 * @param monster_ptr Pointer to the Monster object
 * @return jint Number of times the Monster object has entered the objective
 */
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_knightslabyrinth_MonsterView_inObj(JNIEnv *env, jobject , jfloat obj_x,
                                            jfloat obj_y,
                                            jlong monster_ptr) {
    Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
    return monster -> inObjective(obj_x, obj_y);
}

/**
 * @ Determines the number of times the Monster object has been kicked out of the screen using its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @param monster_ptr Pointer to the Monster object
 * @return jint Number of times the Monster object has been kicked out of the game screen
 */
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_knightslabyrinth_MonsterView_kick(JNIEnv *env, jobject , jlong monster_ptr) {
    Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
    return monster -> kickedOut();
}



//Knight Controls


/**
 * @ Gets a new tick for the GameScreenFragment.
 * @param env JNIEnv pointer
 * @param thisObject Java object
 */
extern "C"
JNIEXPORT void JNICALL
Java_com_example_knightslabyrinth_GameScreenFragment_getNewTick(JNIEnv *env, jobject thisObject)
{
}

/**
 * @ Creates a new Knight object and returns its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @return jlong Pointer to the created Knight object
 */
extern "C"
JNIEXPORT jlong JNICALL
Java_com_example_knightslabyrinth_KnightWrapper_mkNew(JNIEnv *env, jobject){
    return (long)(new Knight(500,800));
}

/**
 * @ Deletes the Knight object using its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @param ptr Pointer to the Knight object
 */
extern "C"
JNIEXPORT void JNICALL
Java_com_example_knightslabyrinth_KnightWrapper_deleteC(JNIEnv *env, jobject,
                                                        jlong ptr){
    delete (Knight *)(ptr);
}

/**
 * @ Sets the target coordinates for the Knight object using its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @param ptr Pointer to the Knight object
 * @param x Target x-coordinate
 * @param y Target y-coordinate
 */
extern "C"
JNIEXPORT void JNICALL
Java_com_example_knightslabyrinth_KnightWrapper_setTargetC(JNIEnv *env, jobject, jlong ptr,
                                                   float x, float y){
    Knight *obj = (Knight *)ptr;
    obj->setTargetK(x,y);
}

/**
 * @ Updates the state of the Knight object using its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @param ptr Pointer to the Knight object
 * @param moveopt Movement option for the Knight
 */
extern"C"
JNIEXPORT void JNICALL
Java_com_example_knightslabyrinth_KnightWrapper_updateC(JNIEnv *env, jobject, jlong ptr, jint moveopt) {
    Knight *obj = (Knight *)ptr;
    obj->update(moveopt);
}

/**
 * @ Gets the x-coordinate of the Knight object using its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @param ptr Pointer to the Knight object
 * @return jfloat X-coordinate of the Knight object
 */
extern"C"
JNIEXPORT jfloat JNICALL
Java_com_example_knightslabyrinth_KnightWrapper_getXC(JNIEnv *env, jobject, jlong ptr) {
    Knight *obj = (Knight *)ptr;
    return obj->getX();
}

/**
 * @ Gets the y-coordinate of the Knight object using its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @param ptr Pointer to the Knight object
 * @return jfloat Y-coordinate of the Knight object
 */
extern"C"
JNIEXPORT jfloat JNICALL
Java_com_example_knightslabyrinth_KnightWrapper_getYC(JNIEnv *env, jobject, jlong ptr) {
    Knight *obj = (Knight *)ptr;
    return obj->getY();
}

/**
 * @ Gets the speed of the Knight object using its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @param ptr Pointer to the Knight object
 * @return jfloat Speed of the Knight object
 */
extern"C"
JNIEXPORT jfloat JNICALL
Java_com_example_knightslabyrinth_KnightWrapper_getSpeedC(JNIEnv *env, jobject, jlong ptr) {
    Knight *obj = (Knight *)ptr;
    return obj->getSpeed();
}

/**
 * @ Gets the active ability status of the Knight object using its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @param ptr Pointer to the Knight object
 * @return jint Active ability status of the Knight object
 */
extern"C"
JNIEXPORT jint JNICALL
Java_com_example_knightslabyrinth_KnightWrapper_getAbilityActiveC(JNIEnv *env, jobject, jlong ptr) {
    Knight *obj = (Knight *)ptr;
    return obj->getAbilityActive();
}

/**
 * @ Gets the cooldown status of the Knight object using its pointer.
 * @param env JNIEnv pointer
 * @param jobject Java object
 * @param ptr Pointer to the Knight object
 * @return jint Cooldown status of the Knight object
 */
extern"C"
JNIEXPORT jint JNICALL
Java_com_example_knightslabyrinth_KnightWrapper_getCooldownC(JNIEnv *env, jobject, jlong ptr) {
    Knight *obj = (Knight *)ptr;
    return obj->getCooldown();
}