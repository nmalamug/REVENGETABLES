//
// Created by Nicomug on 4/23/2023.
//

#ifndef KNIGHTSLABYRINTH_POINT_H
#define KNIGHTSLABYRINTH_POINT_H


class Point {
public:
    Point(){
        x = 0;
        y = 0;
    }
    Point(double theX, double theY){
        x = theX;
        y = theY;
    }
    Point set(Point val){
        Point out;
        out.x = val.x;
        out.y = val.y;
        return out;
    }

    Point set(float theX, float theY){
        Point out;
        out.x = theX;
        out.y = theY;
        return out;
    }

    double x,y;
};


#endif //KNIGHTSLABYRINTH_POINT_H

