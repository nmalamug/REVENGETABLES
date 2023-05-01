#include "monster.h"
#include <cmath>
#include <jni.h>
#include <memory>
// Monster class constructor
Monster::Monster(float x, float y, float speed, int windowWidth, int windowHeight, int movementType, int ktype, int theDifficulty)
        : x(x), y(y), speed(speed), windowWidth(windowWidth), windowHeight(windowHeight), movementType(movementType), hopState(false) {
    collisionCounter = 0;
    initialDirectionX = 1.0;
    initialDirectionY = 1.0;
    reachedCastle = 0;
    kicked = 0;
    knightType = ktype;
    difficulty = theDifficulty;
}

void Monster::doBomberAbility(){
    frame = 2;
    collisionCounter = 20;
    directionX = 0;
    directionY = 1;
    collisionSpeed = 300;
}

// Update the monster's position
void Monster::update(float objectiveX, float objectiveY, float knightX, float knightY, float knightRadius, float knightSpeed, int knightAbility) {
    timeAlive++;
    switch(knightType){
        case(1):
            if(knightAbility > 1 && sqrt(pow(knightX - x, 2) + pow(knightY - y, 2))<200){
                doBomberAbility();
            }
            break;
        case(2):
            if(knightAbility > 1 && sqrt(pow(knightX - x, 2) + pow(knightY - y, 2))<400){
                doBomberAbility();
            }
            break;
        default:
            break;
    }
    if (colliding(knightX, knightY, knightRadius) && knightAbility<1) {
        frame = 2;
        //Get the x and y directions of collision
        directionX = knightX - x;
        directionY = knightY - y;
        collisionSpeed = knightSpeed + 5;
        collisionCounter = 20;
        doCollision();
        return;
    }
    if (collisionCounter > 1) {
        doCollision();
        collisionCounter -= 1;
    } else {
        if(timeAlive%25==0){
            if(frame == walkingFramesUpTo+1){
                frame = 0;
            }else if(frame == walkingFramesUpTo){
                frame = 0;
            }else{
                frame++;
            }
        }
        switch (movementType) {
            case 0:
                //black
                moveToObjective(objectiveX, objectiveY);
                break;
            case 1:
                //blue
                hop(objectiveX, objectiveY);
                break;
            case 2:
                // red
                // Set the initial direction for diagonal movement
                diagonal();
        }
    }
}
int Monster::getMovementType() const {
    return movementType;
}

bool Monster::colliding(float kx, float ky, int rad) {
    float dist = sqrt(pow(kx - x, 2) + pow(ky - y, 2));
    if (dist < (rad + monsterRadius)) {
        return true;
    }
    return false;
}

void Monster::doCollision() {
    //If about to bounce off a wall move focal point to switch x direction
    if (x < monsterRadius) {
        x = monsterRadius;
        directionX = -directionX;
    } else if (x > (windowWidth - monsterRadius)) {
        x = windowWidth - monsterRadius - 1;
        directionX = -directionX;
    }

    float distance = std::sqrt(directionX * directionX + directionY * directionY);
    float normalized_x = directionX / distance;
    float normalized_y = directionY / distance;

    x -= normalized_x * collisionSpeed;
    y -= normalized_y * collisionSpeed;
    collisionSpeed = collisionSpeed * .90;
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

void Monster::hop(float objective_x, float objective_y) {
    hopCounter++;
    float direction_x = objective_x - x;
    float direction_y = objective_y - y;
    float distance = std::sqrt(direction_x * direction_x + direction_y * direction_y);
    float normalized_x = direction_x / distance;
    float normalized_y = direction_y / distance;

    float bounceHeight = 10.0f;
    float bounceFrequency = 0.1f;
    int pauseDuration = 30; // Adjust this value to change the pause duration
    int pauseStart = pauseDuration / 2;

    if (hopCounter % (pauseDuration * 2) < pauseDuration) {
        x += normalized_x * speed;
        y += normalized_y * speed + bounceHeight * sin(hopCounter * bounceFrequency);
    } else if (hopCounter % (pauseDuration * 2) >= pauseDuration + pauseStart) {
        x += normalized_x * speed;
        y += normalized_y * speed;
    }
}
    void Monster::diagonal() {
        x += initialDirectionX * speed;
        y += initialDirectionY * speed;

        if (x < monsterRadius) {
            x = monsterRadius;
            initialDirectionX = -initialDirectionX; // Reverse the direction
        } else if (x > (windowWidth - monsterRadius)) {
            x = windowWidth - monsterRadius - 1;
            initialDirectionX = -initialDirectionX; // Reverse the direction
        }
        if (y < monsterRadius) {
            y = monsterRadius;
            initialDirectionY = -initialDirectionY; // Reverse the direction
        } else if (y > (windowHeight - monsterRadius)) {
            y = windowHeight - monsterRadius - 1;
            initialDirectionY = -initialDirectionY; // Reverse the direction
        }
    }


int Monster::inObjective(float obj_x, float obj_y) {
    float xBound1 = 0, xBound2 = obj_x * 2;
    if (getX() < xBound2 && getX() > xBound1 && getY() > obj_y) {
        reachedCastle++;
    }
    return reachedCastle;
}
/**
 * Counts how many monsters with y-position < 0
 * @return The number of monsters that were "kicked out" by knight
 */
int Monster::kickedOut() {
    if (getY() < 0) {
        kicked++;
    }
    return kicked;
}

int Monster::getMonsterFrame(){
    return frame;
}
