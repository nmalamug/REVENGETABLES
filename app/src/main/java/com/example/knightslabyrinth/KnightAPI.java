package com.example.knightslabyrinth;

import android.graphics.PointF;

/**
 * The KnightWrapperAPI interface provides a contract for managing and displaying
 * the knight character in the game.
 */
public interface KnightAPI {

    /**
     * Returns the radius of the knight.
     *
     * @return The radius of the knight.
     */
    int getRadius();

    /**
     * Returns the current position of the knight.
     *
     * @return A PointF representing the current position of the knight.
     */
    PointF getKnightPosition();

    /**
     * Returns the speed of the knight.
     *
     * @return The speed of the knight.
     */
    float getSpeed();

    /**
     * Returns the state of the knight's ability.
     *
     * @return 1 if the ability is active, 0 otherwise.
     */
    int getAbilityActive();

    /**
     * Moves the knight by updating its position.
     */
    void moveKnight();

    /**
     * Sets the target position for the knight to move towards.
     *
     * @param x The target x-coordinate.
     * @param y The target y-coordinate.
     */
    void setTarget(float x, float y);

}
