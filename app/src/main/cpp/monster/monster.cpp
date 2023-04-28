#include "monster.h"
#include <cmath>
#include <jni.h>
#include <memory>
// Monster class constructor
Monster::Monster(float x, float y, float speed, int windowWidth, int windowHeight, int color, int movementType)
        : x(x), y(y), speed(speed), windowWidth(windowWidth), windowHeight(windowHeight), color(color), movementType(movementType), hopState(false) {
    collisionCounter = 0;
    initialDirectionX = 1.0;
    initialDirectionY = 1.0;
}


// Update the monster's position
void Monster::update(float objectiveX, float objectiveY, float knightX, float knightY, float knightRadius, float knightSpeed) {
    if (colliding(knightX, knightY, knightRadius)) {
        //Get the x and y directions of collision
        directionX = knightX - x;
        directionY = knightY - y;
        collisionSpeed = knightSpeed + 5;
        collisionCounter = 20;
    }
    if (collisionCounter > 1) {
        doCollision();
        collisionCounter -= 1;
    } else {
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
            x = 51;
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
        float direction_x = objective_x - x;
        float direction_y = objective_y - y;
        float distance = std::sqrt(direction_x * direction_x + direction_y * direction_y);
        float normalized_x = direction_x / distance;
        float normalized_y = direction_y / distance;
        int hop_distance = 10;
        if (hopState) {
            x += normalized_x * hop_distance;
            y += normalized_y * hop_distance;
            hopState = !hopState;
        } else {
            x += normalized_x * speed;
            y += normalized_y * speed;

            if (distance <= speed) {
                hopState = !hopState;
            }
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




