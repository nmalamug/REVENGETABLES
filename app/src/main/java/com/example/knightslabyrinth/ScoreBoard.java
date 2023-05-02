package com.example.knightslabyrinth;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ScoreBoard implements ScoreBoardAPI {
    private static final String PREFERENCES_KEY = "ScoreBoard_preferences"; // Key for the shared preferences used to store score data
    private static final int MAX_HIGH_SCORES = 10;// Maximum number of high scores to be saved
    private SharedPreferences sharedPreferences;// SharedPreferences object for storing and retrieving score data

    // Constructor
    public ScoreBoard(Context context) {
        // Initialize sharedPreferences with the provided context
        sharedPreferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    // Save a score for a specific player
    public void saveScore(String playerName, int score) {
        // Create an editor to modify sharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Retrieve high scores from sharedPreferences
        Set<String> highScores = new TreeSet<>(sharedPreferences.getStringSet("high_scores", new TreeSet<>()));
        // Check if there is room for a new high score
        if (highScores.size() < MAX_HIGH_SCORES) {
            // Add the new score to highScores
            highScores.add(score + "_" + playerName);
        } else {
            // Find the lowest score entry in highScores
            String lowestScoreEntry = Collections.min(highScores, Comparator.comparingInt(s -> Integer.parseInt(s.split("_")[0])));
            int lowestScore = Integer.parseInt(lowestScoreEntry.split("_")[0]);
            // Check if the new score is higher than the lowest score
            if (score > lowestScore) {
                // Remove the lowest score entry and add the new score entry
                highScores.remove(lowestScoreEntry);
                highScores.add(score + "_" + playerName);
            }
        }

        // Save the updated highScores to sharedPreferences
        editor.putStringSet("high_scores", highScores);
        editor.apply();
    }

    // Retrieve all high scores
    public List<String> getHighScores() {
        // Get high scores from sharedPreferences
        Set<String> highScores = sharedPreferences.getStringSet("high_scores", new TreeSet<>());

        // Sort high scores in descending order
        List<String> sortedHighScores = new ArrayList<>(highScores);
        Collections.sort(sortedHighScores, (string1, string2) -> {
            int score1 = Integer.parseInt(string1.split("_")[0]);
            int score2 = Integer.parseInt(string2.split("_")[0]);
            return Integer.compare(score2, score1);
        });


        int playNum = 1;
        // Convert high scores to a readable format
        List<String> highScoreList = new ArrayList<>();


        for (String highScore : sortedHighScores) {
            //if(playNum>5)break;
            String[] parts = highScore.split("_");
            int score = Integer.parseInt(parts[0]);
            String playerName = parts[1];
            highScoreList.add(playerName + " " + playNum + ": " + score);
            playNum++;
        }

        // Return the formatted high scores list
        return highScoreList;
    }
}
