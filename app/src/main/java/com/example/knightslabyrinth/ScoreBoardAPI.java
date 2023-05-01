package com.example.knightslabyrinth;
import java.util.List;

/**
 * The ScoreBoardAPI interface provides a contract for managing the scoreboard,
 * allowing the user to save and retrieve high scores.
 */
public interface ScoreBoardAPI {

    /**
     * Saves a score for a specific player to the scoreboard.
     *
     * @param playerName The name of the player.
     * @param score      The score achieved by the player.
     */
    void saveScore(String playerName, int score);

    /**
     * Retrieves a list of high scores from the scoreboard, sorted in descending order.
     *
     * @return A list of formatted high score strings in the format "PlayerName Rank: Score".
     */
    List<String> getHighScores();
}

