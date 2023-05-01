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
    cooldown = 0;
}

void Knight::setTargetK(float theX, float theY){
    target.x = theX;
    target.y = theY;
}

void Knight::move(){
    float x = pos.x;
    float y = pos.y;
    distance = sqrt(pow(target.x-x,2)+pow(target.y-y,2));
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

void Knight::moveBomber(){
    float x = pos.x;
    float y = pos.y;
    if(abilityActive>0){
        abilityActive--;
    }
    distance = sqrt(pow(target.x-x,2)+pow(target.y-y,2));
    if(bomberAbilityReady()){
        doBomberAbility();
        return;
    }
    if(cooldown>0){
        cooldown--;
    }
    speed = distance/3;
    if(speed>50){
        speed = 50;
    }
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

bool Knight::bomberAbilityReady(){
    if(cooldown == 0 && distance>200+speed*50){
        return true;
    }else{
        return false;
    }
}

void Knight::doBomberAbility(){
    pos.x = target.x;
    pos.y = target.y;
    cooldown = 250;
    abilityActive = 3;
}

void Knight::moveDasher(){
    float x = pos.x;
    float y = pos.y;
    if(abilityActive>0){
        abilityActive--;
        doDasherAbility();
    }
    distance = sqrt(pow(target.x-x,2)+pow(target.y-y,2));
    if(dasherAbilityReady()){
        abilityActive = 10;
        cooldown = 250;
        dashStepX = (target.x-pos.x)/10;
        dashStepY = (target.y-pos.y)/10;
        doDasherAbility();
        return;
    }
    if(cooldown>0){
        cooldown--;
    }
    speed = distance/3+10;
    if(speed>75){
        speed = 75;
    }
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

bool Knight::dasherAbilityReady(){
    if(cooldown == 0 && distance>400+speed*20){
        return true;
    }else{
        return false;
    }
}

void Knight::doDasherAbility(){
    pos.x += dashStepX;
    pos.y += dashStepY;
}

void Knight::update(int moveop){

    switch(moveop) {
        case (2):
            moveBomber();
            break;
        case (1):
            moveDasher();
            break;
        default:
            move();
            break;
    }
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

int Knight::getAbilityActive() const{
    return abilityActive;
}

int Knight::getCooldown()const{
    return cooldown;
}


