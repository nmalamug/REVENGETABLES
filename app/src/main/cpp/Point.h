//
// Created by Nicomug on 4/23/2023.
//

#ifndef KNIGHTSLABYRINTH_POINT_H
#define KNIGHTSLABYRINTH_POINT_H

/**
 * The Point class represents a point in a two-dimensional coordinate system
 * with double precision x and y values.
 */
class Point {
public:
    /**
     * Default constructor. Initializes both x and y values to 0.
     */
    Point(){
        x = 0;
        y = 0;
    }

    /**
     * Constructor that initializes the point with the given x and y values.
     *
     * @param theX The x-coordinate.
     * @param theY The y-coordinate.
     */
    Point(double theX, double theY){
        x = theX;
        y = theY;
    }

    /**
     * Sets the current point's x and y values to the values of the given point.
     *
     * @param val The point with the x and y values to set.
     * @return The updated point.
     */
    Point set(Point val){
        Point out;
        out.x = val.x;
        out.y = val.y;
        return out;
    }

    /**
     * Sets the current point's x and y values to the given x and y values.
     *
     * @param theX The x-coordinate to set.
     * @param theY The y-coordinate to set.
     * @return The updated point.
     */
    Point set(float theX, float theY){
        Point out;
        out.x = theX;
        out.y = theY;
        return out;
    }

    //x and y cords
    double x,y;
};


#endif //KNIGHTSLABYRINTH_POINT_H

