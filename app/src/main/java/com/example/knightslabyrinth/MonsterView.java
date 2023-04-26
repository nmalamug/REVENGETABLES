package com.example.knightslabyrinth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public class MonsterView extends View {
    private Paint paint;
    private List<Long> monsterPtrs;
    private GameScreenFragment gameScreenFragment;

    // Constructors
    public MonsterView(Context context, AttributeSet attrs, GameScreenFragment gameScreenFragment) {
        super(context, attrs);
        this.gameScreenFragment = gameScreenFragment;
        init();
    }

    public MonsterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MonsterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    // Initialize the paint object for drawing monsters
    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
    }

    // Set the monster pointers list and invalidate the view to trigger a redraw
    public void setMonsterPtrs(List<Long> monsterPtrs) {
        this.monsterPtrs = monsterPtrs;
        invalidate();
    }

    // Override the onDraw method to draw the monsters on the canvas
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (monsterPtrs != null) {
            drawMonsters(canvas);
        }
    }

    // Set the GameScreenFragment reference
    public void setGameScreenFragment(GameScreenFragment gameScreenFragment) {
        this.gameScreenFragment = gameScreenFragment;
    }

    // Draw monsters on the canvas using the list of monster pointers
    private void drawMonsters(Canvas canvas) {
        Paint monsterPaint = new Paint();
        monsterPaint.setColor(Color.BLUE);

        for (long monsterPtr : monsterPtrs) {
            float x = gameScreenFragment.getMonsterX(monsterPtr);
            float y = gameScreenFragment.getMonsterY(monsterPtr);
            float radius = 20; // Adjust the size of the monster as needed
            canvas.drawCircle(x, y, radius, monsterPaint);
        }
    }
}
