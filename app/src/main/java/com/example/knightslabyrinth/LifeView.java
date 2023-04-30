package com.example.knightslabyrinth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class LifeView extends View {
    private Paint paint;
    private float xx;
    public int livesLost;
    public int maxLives = 7;
    public int currLives = maxLives;
    public GameScreenFragment gameScreenFragment;

    public LifeView(Context context, AttributeSet attrs, GameScreenFragment gameScreenFragment) {
        super(context, attrs);
        this.gameScreenFragment = gameScreenFragment;
        xx = getWidth() / 2 + 20;
        init();
    }

    public LifeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LifeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        paint = new Paint();
        paint.setColor(Color.MAGENTA);
    }

    /**
     * Sets reference to GameScreenFragment
     * @param gameScreenFragment The game screen
     */
    public void setGameScreenFragment(GameScreenFragment gameScreenFragment) {
        this.gameScreenFragment = gameScreenFragment;
    }

    /**
     * Assigns values to current lives, lost lives, and max lives when lives are lost
     * @param lost Number of lives lost while playing the game
     * @param curr Number of current lives
     * @param max Number of maximum lives depending on game difficulty
     */
    public void getLivesLost(int lost, int curr, int max) {
        livesLost = lost;
        currLives = curr;
        maxLives = max;
        if (livesLost != 0) {
            invalidate();
        }
    }

    /**
     * Draws the lives on the game screen
     * @param canvas The canvas to draw lives on
     */
    @Override
    public void onDraw(Canvas canvas) {
        drawLives(canvas);
        super.onDraw(canvas);
    }

    /**
     * Sets values for both maximum and current lives
     * @param lives Maximum amount of lives depending on game difficulty
     */
    public void setMaxLives(int lives) {
        maxLives = lives;
        currLives = maxLives;
    }

    /**
     * Draws the lives or deaths on game screen depending on current lives
     * @param canvas The canvas to draw lives on
     */
    public void drawLives(Canvas canvas) {
        Paint lifePaint = new Paint();
        Paint noLife = new Paint();
        lifePaint.setColor(Color.MAGENTA);
        noLife.setColor(Color.GRAY);

        float x = getWidth() - 100;
        float y = 100;
        float radius = 23;
        for (int i = 1; i <= maxLives; i++) {
            if (i > currLives) {
                canvas.drawCircle(x, y, radius, noLife);
            } else {
                canvas.drawCircle(x, y, radius, lifePaint);
            }
            x -= 100;
        }
    }

    /**
     * Updates the score while user stays in game
     * @param tick The signal for the game to continue as a long
     * @return true only on the 40th tick, 80th tick, etc
     */
    public boolean updateScore(long tick) {
        if (tick % 40 == 0) { return true; }
        else { return false; }
    }
}
