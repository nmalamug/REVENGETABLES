package com.example.knightslabyrinth;

public class SettingsManager implements SettingsAPI {
    // Constructor initializes the default values for knight, difficulty, and last score
    SettingsManager(){
        knight = 1;
        difficulty = 1;
        lastScore = 0;
    }
    /**
     * Sets the last score.
     *
     * @param theScore The last game score.
     */
    public void setLastScore(int theScore){
        lastScore = theScore;
    }
    /**
     * Retrieves the last score.
     *
     * @return The last game score.
     */
    public int getLastScore(){
        return lastScore;
    }
    /**
     * Sets the knight type.
     *
     * @param theKnight The selected knight type.
     */
    public void setKnight(int theKnight){
        knight = theKnight;
    }
    /**
     * Retrieves the knight type.
     *
     * @return The selected knight type.
     */
    public int getKnight(){
        return knight;
    }
    /**
     * Sets the game difficulty.
     *
     * @param theDifficulty The selected game difficulty.
     */
    public void setDifficulty(int theDifficulty){
        difficulty = theDifficulty;
    }
    /**
     * Retrieves the game difficulty.
     *
     * @return The selected game difficulty.
     */
    public int getDifficulty(){
        return difficulty;
    }
    private int knight; // Variable to store the selected knight type
    private int difficulty; // Variable to store the selected game difficulty
    private int lastScore; // Variable to store the last game score

}
