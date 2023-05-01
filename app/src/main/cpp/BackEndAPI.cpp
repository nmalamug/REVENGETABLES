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


extern "C"
// Update the monster using its pointer
JNIEXPORT void JNICALL
Java_com_example_knightslabyrinth_MonsterView_updateMonster(JNIEnv *env, jobject , jlong monster_ptr, jfloat objective_x,
                                                            jfloat objective_y,jfloat knightX,
                                                            jfloat knightY, jint radius, jfloat knightSpeed,
                                                            jint knightAbility) {
Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
monster->update(objective_x, objective_y, knightX,
                knightY, radius, knightSpeed, knightAbility);
}


extern "C"
// Get the monster's x position using its pointer
JNIEXPORT jfloat JNICALL
        Java_com_example_knightslabyrinth_MonsterView_getMonsterX(JNIEnv *env, jobject ,
                                                                  jlong monster_ptr) {
Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
return monster->getX();
}


// Get the monster's y position using its pointer
extern "C" JNIEXPORT jfloat JNICALL
        Java_com_example_knightslabyrinth_MonsterView_getMonsterY(JNIEnv *env, jobject,
                                                                  jlong monster_ptr) {
Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
return monster->getY();
}


// Get the monster's x position using its pointer for KnightView
extern "C" JNIEXPORT jfloat JNICALL
        Java_com_example_knightslabyrinth_KnightView_getMonsterX(JNIEnv *env, jobject, jlong monster_ptr) {
Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
return static_cast<jfloat>(monster->x);
}


// Get the monster's y position using its pointer for KnightView
extern "C" JNIEXPORT jfloat JNICALL
        Java_com_example_knightslabyrinth_KnightView_getMonsterY(JNIEnv *env, jobject , jlong monster_ptr) {
Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
return static_cast<jfloat>(monster->y);
}


extern "C" JNIEXPORT jint JNICALL
        Java_com_example_knightslabyrinth_MonsterView_getMovementType(JNIEnv *env, jobject , jlong monster_ptr) {
Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
return monster->getMovementType();
}


extern "C" JNIEXPORT jint JNICALL
Java_com_example_knightslabyrinth_MonsterView_getMonsterFrameC(JNIEnv *env, jobject , jlong monster_ptr) {
    Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
    return monster->getMonsterFrame();
}


// Delete the monster
extern "C"
JNIEXPORT void JNICALL
Java_com_example_knightslabyrinth_MonsterView_deleteC(JNIEnv *env, jobject,jlong ptr) {
    delete (Monster *) (ptr);
}


extern "C"
JNIEXPORT jint JNICALL
Java_com_example_knightslabyrinth_MonsterView_inObj(JNIEnv *env, jobject , jfloat obj_x,
                                                    jfloat obj_y, jlong monster_ptr) {
    Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
    return monster -> inObjective(obj_x, obj_y);
}


extern "C"
JNIEXPORT jint JNICALL
Java_com_example_knightslabyrinth_MonsterView_kick(JNIEnv *env, jobject , jlong monster_ptr) {
    Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
    return monster -> kickedOut();
}





//Knight Controls
extern "C"
JNIEXPORT void JNICALL
Java_com_example_knightslabyrinth_GameScreenFragment_getNewTick(JNIEnv *env, jobject thisObject)
{
}


extern "C"
JNIEXPORT jlong JNICALL
Java_com_example_knightslabyrinth_KnightWrapper_mkNew(JNIEnv *env, jobject){
    return (long)(new Knight(500,800));
}


extern "C"
JNIEXPORT void JNICALL
Java_com_example_knightslabyrinth_KnightWrapper_deleteC(JNIEnv *env, jobject,jlong ptr){
    delete (Knight *)(ptr);
}


extern "C"
JNIEXPORT void JNICALL
Java_com_example_knightslabyrinth_KnightWrapper_setTargetC(JNIEnv *env, jobject, jlong ptr, float x, float y){
    Knight *obj = (Knight *)ptr;
    obj->setTargetK(x,y);
}


extern"C"
JNIEXPORT void JNICALL
Java_com_example_knightslabyrinth_KnightWrapper_updateC(JNIEnv *env, jobject, jlong ptr, jint moveopt) {
    Knight *obj = (Knight *)ptr;
    obj->update(moveopt);
}


extern"C"
JNIEXPORT jfloat JNICALL
Java_com_example_knightslabyrinth_KnightWrapper_getXC(JNIEnv *env, jobject, jlong ptr) {
    Knight *obj = (Knight *)ptr;
    return obj->getX();
}


extern"C"
JNIEXPORT jfloat JNICALL
Java_com_example_knightslabyrinth_KnightWrapper_getYC(JNIEnv *env, jobject, jlong ptr) {
    Knight *obj = (Knight *)ptr;
    return obj->getY();
}


extern"C"
JNIEXPORT jfloat JNICALL
Java_com_example_knightslabyrinth_KnightWrapper_getSpeedC(JNIEnv *env, jobject, jlong ptr) {
    Knight *obj = (Knight *)ptr;
    return obj->getSpeed();
}


extern"C"
JNIEXPORT jint JNICALL
Java_com_example_knightslabyrinth_KnightWrapper_getAbilityActiveC(JNIEnv *env, jobject, jlong ptr) {
    Knight *obj = (Knight *)ptr;
    return obj->getAbilityActive();
}


extern"C"
JNIEXPORT jint JNICALL
Java_com_example_knightslabyrinth_KnightWrapper_getCooldownC(JNIEnv *env, jobject, jlong ptr) {
    Knight *obj = (Knight *)ptr;
    return obj->getCooldown();
}