package com.example.knightslabyrinth;

import android.graphics.Canvas;

import java.util.List;

/**
 * The HighScoreViewAPI interface provides a contract for displaying the high scores
 * in a custom view, allowing the user to visualize high scores on the screen.
 */
public interface HighScoreViewAPI {

    /**
     * Sets the high scores to be displayed in the view.
     *
     * @param highScores A list of high scores to be displayed.
     */
    void setHighScores(List<String> highScores);

}
