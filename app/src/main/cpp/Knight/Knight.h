//
// Created by Nicomug on 4/23/2023.
//
#include "../Point.h"
#ifndef KNIGHTSLABYRINTH_KNIGHT_H
#define KNIGHTSLABYRINTH_KNIGHT_H

class Knight {
public:
    Knight(double theX, double theY);
    void setTargetK(float theX, float theY);
    void move();
    void update(int moveOpt);
    float getX()const ;
    float getY()const ;
    float getSpeed() const;
    Point pos;
    Point target;
    float speed;
};


#endif //KNIGHTSLABYRINTH_KNIGHT_H
