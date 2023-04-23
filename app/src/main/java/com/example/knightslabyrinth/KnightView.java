package com.example.knightslabyrinth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

public class KnightView extends View {
    private Paint paint;
    private PointF knightPosition;

    public KnightView(Context context) {
        super(context);
        init();
    }

    public KnightView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KnightView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(0xFFFF0000); // Red color
        knightPosition = new PointF(0, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(knightPosition.x, knightPosition.y, 50, paint);
    }

    public void moveKnight(float x, float y) {
        knightPosition.set(x, y);
        invalidate();
    }
}
