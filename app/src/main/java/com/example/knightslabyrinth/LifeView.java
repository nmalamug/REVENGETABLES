package com.example.knightslabyrinth;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class LifeView extends View implements LifeViewAPI {
    private Paint paint;
    private float xx;// X position of the life view on the screen
    public int livesLost; // Number of lives lost
    public int maxLives = 7; // Maximum number of lives available
    public int currLives = maxLives;// Current number of lives
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


    public void setGameScreenFragment(GameScreenFragment gameScreenFragment) {
        this.gameScreenFragment = gameScreenFragment;
    }

    public void getLivesLost(int lost, int curr, int max) {
        livesLost = lost;
        currLives = curr;
        maxLives = max;
        if (livesLost != 0) {
            invalidate();
        }
    }


    @Override
    public void onDraw(Canvas canvas) {
        drawLives(canvas);
        super.onDraw(canvas);
    }


    public void setMaxLives(int lives) {
        maxLives = lives;
        currLives = maxLives;
    }

    public void drawLives(Canvas canvas) {
        Paint lifePaint = new Paint();
        Paint noLife = new Paint();
        lifePaint.setColor(Color.MAGENTA);
        noLife.setColor(Color.GRAY);
        Bitmap red = BitmapFactory.decodeResource(getResources(),R.drawable.smaller_heart);
        Bitmap grey = BitmapFactory.decodeResource(getResources(),R.drawable.smallgrey5);

        float x = getWidth() - 100;
        float y = 100;
        float radius = 23;
        for (int i = 1; i <= maxLives; i++) {
            if (i > currLives) {
                canvas.drawCircle(x, y, radius, noLife);
                canvas.drawBitmap(grey, x-55, y-40, noLife);
            } else {
                canvas.drawCircle(x, y, radius, lifePaint);
                canvas.drawBitmap(red, x-55, y-55, lifePaint);
            }
            x -= 100;
        }
    }


    public boolean updateScore(long tick) {
        if (tick%40 == 0) { return true; }
        else { return false; }
    }
}
