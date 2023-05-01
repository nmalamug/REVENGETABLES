package com.example.knightslabyrinth;

public interface SettingsAPI {

    /**
     * Sets the last score.
     *
     * @param theScore The last score to set.
     */
    void setLastScore(int theScore);

    /**
     * Gets the last score.
     *
     * @return The last score.
     */
    int getLastScore();

    /**
     * Sets the knight.
     *
     * @param theKnight The knight to set.
     */
    void setKnight(int theKnight);

    /**
     * Gets the knight.
     *
     * @return The knight.
     */
    int getKnight();

    /**
     * Sets the difficulty.
     *
     * @param theDifficulty The difficulty to set.
     */
    void setDifficulty(int theDifficulty);

    /**
     * Gets the difficulty.
     *
     * @return The difficulty.
     */
    int getDifficulty();
}
