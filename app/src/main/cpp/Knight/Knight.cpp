//
// Created by Nicomug on 4/23/2023.
//
#include <jni.h>
#include <cmath>
#include "Knight.h"
#include "../Point.h"

Knight::Knight(double theX, double theY){
    pos.x = theX;
    pos.y = theY;
    target.x = theX;
    target.y = theY;
    speed = 0;
}

void Knight::setTargetK(float theX, float theY){
    target.x = theX;
    target.y = theY;
}

void Knight::move(){
    float x = pos.x;
    float y = pos.y;
    float distance = sqrt(pow(target.x-x,2)+pow(target.y-y,2));
    speed = distance/3 + 20;
    float xdist = target.x-x;
    float ydist = target.y-y;
    xdist = xdist/distance*speed;
    ydist = ydist/distance*speed;
    if(distance <= speed){
        pos.x = target.x;
        pos.y = target.y;
    }else{
        pos.x = pos.x + xdist;
        pos.y = pos.y + ydist;
    }
}

void Knight::update(){
    move();
}

float Knight::getX() const {
    return pos.x;
}

float Knight::getY() const {
    return pos.y;
}

float Knight::getSpeed() const{
    return speed;
}

//Knight Controls
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
Java_com_example_knightslabyrinth_KnightWrapper_updateC(JNIEnv *env, jobject, jlong ptr) {
    Knight *obj = (Knight *)ptr;
    obj->update();
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