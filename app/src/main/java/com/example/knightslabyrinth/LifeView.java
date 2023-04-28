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
    public int currLives = 7;
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
    public void onDraw(Canvas canvas)
    {
        drawLives(canvas);
        super.onDraw(canvas);
    }

    // draw lives on top right of screen
    public void drawLives(Canvas canvas) {
        Paint lifePaint = new Paint();
        Paint noLife = new Paint();
        lifePaint.setColor(Color.MAGENTA);
        noLife.setColor(Color.GREEN);

        float x = getWidth() / 2 + 20;
        float y = 100;
        float radius = 23;
        for (int i = 1; i <= maxLives; i++)
        {
            if (i>currLives) {
                canvas.drawCircle(x, y, radius, noLife);
            } else {
                canvas.drawCircle(x, y, radius, lifePaint);
            }
            x += 100;
        }
    }
/*
    // lose 1 life every time 1 monster reaches bottom portion of screen
    public void loseLife(Canvas canvas) {

        Paint noLife = new Paint();
        noLife.setColor(Color.GREEN);

        float yy = 100;
        float radius = 25; // bigger than size of "heart"

        if (livesLost > 0)
        {
            for (int i = 1; i <= livesLost; i++) {
                canvas.drawCircle(xx, yy, radius, noLife);
                xx += 100;
            }
        }


        invalidate();
    }*/
}
