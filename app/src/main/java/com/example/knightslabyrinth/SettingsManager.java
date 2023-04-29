package com.example.knightslabyrinth;

public class SettingsManager {
    SettingsManager(){
        knight = 1;
        difficulty = 1;
        lastScore = 0;
    }
    public void setLastScore(int theScore){
        lastScore = theScore;
    }
    public int getLastScore(){
        return lastScore;
    }

    public void setKnight(int theKnight){
        knight = theKnight;
    }

    public int getKnight(){
        return knight;
    }

    public void setDifficulty(int theDifficulty){
        difficulty = theDifficulty;
    }

    public int getDifficulty(){
        return difficulty;
    }
    private int knight;
    private int difficulty;
    private int lastScore;
}
