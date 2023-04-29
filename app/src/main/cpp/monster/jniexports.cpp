//
// Created by Harman Singh on 4/28/23.
//
// JNI functions

#include "monster.cpp"
#include <cmath>
#include <jni.h>
#include <memory>

extern "C"
jlong JNIEXPORT
Java_com_example_knightslabyrinth_MonsterView_createMonster(JNIEnv *env, jobject ,
                                                            jfloat x, jfloat y, jfloat speed,
                                                            jint windowWidth, jint windowHeight, jint movementType) {
    Monster *monster = new Monster(x, y, speed, windowWidth, windowHeight, movementType);
    return reinterpret_cast<jlong>(monster);
}
extern "C"
// Update the monster using its pointer
JNIEXPORT void JNICALL
Java_com_example_knightslabyrinth_MonsterView_updateMonster(JNIEnv *env, jobject ,
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
        Java_com_example_knightslabyrinth_MonsterView_getMovementType(JNIEnv *env, jobject thiz, jlong monster_ptr) {
Monster *monster = reinterpret_cast<Monster *>(monster_ptr);
return monster->getMovementType();
}

extern "C" JNIEXPORT jint JNICALL
Java_com_example_knightslabyrinth_MonsterView_getMonsterFrameC(JNIEnv *env, jobject thiz, jlong monster_ptr) {
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