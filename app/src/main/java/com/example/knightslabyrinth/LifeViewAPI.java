package com.example.knightslabyrinth;

import android.graphics.Canvas;

/**
 * The LifeViewAPI interface provides a contract for managing and displaying
 * the lives of the knight character in the game.
 */
public interface LifeViewAPI {

    /**
     * Sets the GameScreenFragment instance.
     *
     * @param gameScreenFragment A reference to the GameScreenFragment.
     */
    void setGameScreenFragment(GameScreenFragment gameScreenFragment);

    /**
     * Updates the lives lost, current lives, and maximum lives values.
     * @param lost The number of lives lost.
     * @param curr The current number of lives.
     * @param max  The maximum number of lives.
     */
    void getLivesLost(int lost, int curr, int max);

    /**
     * Sets the maximum number of lives.
     * @param lives The maximum number of lives.
     */
    void setMaxLives(int lives);

    /**
     * Draws the lives on the top-right corner of the screen.
     * @param canvas The Canvas object to draw the lives on.
     */
    void drawLives(Canvas canvas);

    /**
     * Updates the score based on the game tick.
     * @param tick The game tick.
     * @return True if the score should be updated, false otherwise.
     */
    boolean updateScore(long tick);
}
