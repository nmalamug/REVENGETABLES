package com.example.knightslabyrinth;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ScoreBoard {
    private static final String PREFERENCES_KEY = "ScoreBoard_preferences";
    private static final int MAX_HIGH_SCORES = 10;
    private SharedPreferences sharedPreferences;

    public ScoreBoard(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    // Method to save a score for a specific player
    public void saveScore(String playerName, int score) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> highScores = new TreeSet<>(sharedPreferences.getStringSet("high_scores", new TreeSet<>()));

        if (highScores.size() < MAX_HIGH_SCORES) {
            highScores.add(score + "_" + playerName);
        } else {
            String lowestScoreEntry = Collections.min(highScores, Comparator.comparingInt(s -> Integer.parseInt(s.split("_")[0])));
            int lowestScore = Integer.parseInt(lowestScoreEntry.split("_")[0]);
            if (score > lowestScore) {
                highScores.remove(lowestScoreEntry);
                highScores.add(score + "_" + playerName);
            }
        }

        editor.putStringSet("high_scores", highScores);
        editor.apply();
    }


    // Method to retrieve all high scores from the scoreboard
    public List<String> getHighScores() {
        // Get the high scores set from SharedPreferences
        Set<String> highScores = sharedPreferences.getStringSet("high_scores", new TreeSet<>());

        List<String> sortedHighScores = new ArrayList<>(highScores);
        Collections.sort(sortedHighScores, (o1, o2) -> {
            int score1 = Integer.parseInt(o1.split("_")[0]);
            int score2 = Integer.parseInt(o2.split("_")[0]);
            return Integer.compare(score2, score1);
        });

        // Convert the high scores set to a more readable format
        List<String> highScoreList = new ArrayList<>();
        for (String highScore : sortedHighScores) {
            String[] parts = highScore.split("_");
            int score = Integer.parseInt(parts[0]);
            String playerName = parts[1];
            highScoreList.add(playerName + ": " + score);
        }
        return highScoreList;
    }
}
