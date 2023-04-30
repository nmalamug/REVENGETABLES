package com.example.knightslabyrinth;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.view.View;
/**
 * Interface for interacting with the MonsterView.
 */
public interface MonsterAPI {
    /**
     * Sets the GameScreenFragment reference.
     *
     * @param gameScreenFragment A reference to the GameScreenFragment object.
     */
    void setGameScreenFragment(GameScreenFragment gameScreenFragment);


    /**
     * Sets the window width.
     *
     * @param width The window width.
     */
    void setWindowWidth(int width);


    /**
     * Sets the window height.
     *
     * @param height The window height.
     */
    void setWindowHeight(int height);


    /**
     * Sets the knight's position.
     *
     * @param position A PointF object representing the knight's position.
     */
    void setKnightPosition(PointF position);


    /**
     * Moves the monsters based on the knight's position, radius, speed, and ability.
     *
     * @param knightPosition The knight's position as a PointF object.
     * @param knightRadius   The knight's radius.
     * @param knightSpeed    The knight's speed.
     * @param knightAbility  The knight's ability.
     */
    void moveMonsters(PointF knightPosition, int knightRadius, float knightSpeed, int knightAbility);


    /**
     * Spawns monsters based on the tick number.
     *
     * @param ticknum The tick number.
     */
    void spawnMonsters(long ticknum);


    /**
     * Deletes monsters that have reached the castle.
     *
     * @return The number of monsters deleted.
     */
    int deleteMonsters();


    /**
     * Returns the number of normal monsters kicked out.
     *
     * @return The number of normal monsters kicked out.
     */
    int getNormKicked();


    /**
     * Returns the number of hop monsters kicked out.
     *
     * @return The number of hop monsters kicked out.
     */
    int getHopKicked();


    /**
     * Returns the number of diagonal monsters kicked out.
     *
     * @return The number of diagonal monsters kicked out.
     */
    int getDiagKicked();
}

