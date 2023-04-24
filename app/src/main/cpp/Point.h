//
// Created by Nicomug on 4/23/2023.
//

#ifndef KNIGHTSLABYRINTH_POINT_H
#define KNIGHTSLABYRINTH_POINT_H
class Point {
public:
    Point(){
        x=0;
        y=0;
    }
    Point(double theX, double theY) {
        x = theX;
        y = theY;
    }
    double x,y;
};

#endif //KNIGHTSLABYRINTH_POINT_H

