#include "monster.h"

// Constructor
Monster::Monster(float x, float y, float speed) : x(x), y(y), speed(speed) {}

// Spawning method
void Monster::spawn(float spawn_x, float spawn_y) {
    x = spawn_x;
    y = spawn_y;
}

// Move to objective method
void Monster::moveToObjective(float objective_x, float objective_y) {
    // Calculate the direction vector
    float direction_x = objective_x - x;
    float direction_y = objective_y - y;

    // Normalize the direction vector
    float distance = std::sqrt(direction_x * direction_x + direction_y * direction_y);
    float normalized_x = direction_x / distance;
    float normalized_y = direction_y / distance;

    // Move the monster in the direction of the objective
    x += normalized_x * speed;
    y += normalized_y * speed;
}

// Update method
void Monster::update(float deltaTime, float objectiveX, float objectiveY) {
    // Move the monster towards the objective
    moveToObjective(objectiveX, objectiveY);
    // You can add additional logic for updating the monster's state, such as
    // checking for collisions or updating animations
}
#include <jni.h>
#include <vector>
#include "monster.h"

std::vector<Monster> monsters;

extern "C"
JNIEXPORT void JNICALL
Java_com_example_knightslabyrinth_GameScreenFragment_spawnMonster(JNIEnv *env, jobject thiz, jfloat spawnX, jfloat spawnY) {
    Monster monster(spawnX, spawnY, 0.1f);
    monsters.push_back(monster);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_knightslabyrinth_GameScreenFragment_updateMonsters(JNIEnv *env, jobject thiz, jfloat deltaTime, jfloat objectiveX, jfloat objectiveY) {
    for (Monster &monster : monsters) {
        monster.update(deltaTime, objectiveX, objectiveY);
    }
}

