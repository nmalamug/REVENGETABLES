package com.example.knightslabyrinth;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public class HighScoreView extends View {
    private Paint paint;
    private List<String> highScores;

    public HighScoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HighScoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(40);
    }

    public void setHighScores(List<String> highScores) {
        this.highScores = highScores;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (highScores != null) {
            drawHighScores(canvas);
        }
    }

    private void drawHighScores(Canvas canvas) {
        float x = getWidth() / 2;
        float y = 100;

        for (String highScore : highScores) {
            canvas.drawText(highScore, x, y, paint);
            y += 50;
        }
    }
}
