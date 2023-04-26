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
    void update();
    float getX()const ;
    float getY()const ;

    Point pos;
    Point target;

};


#endif //KNIGHTSLABYRINTH_KNIGHT_H
