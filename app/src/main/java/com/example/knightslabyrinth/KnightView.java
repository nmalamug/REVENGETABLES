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
    private PointF knightTarget;

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
        knightTarget = new PointF(0, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(knightPosition.x, knightPosition.y, 50, paint);
    }

    public native void setTarget(float x, float y);
/*
    public void setTarget(float x, float y){
        knightTarget.set(x,y);
    }
    public void moveKnight() {
        float speed = 10;
        float x = knightPosition.x;
        float y = knightPosition.y;
        float tx = knightTarget.x;
        float ty = knightTarget.y;
        float dist = (float) Math.sqrt(Math.pow(ty - y, 2) + Math.pow(tx - x, 2));
        float ang;
        if(tx>x){
            ang = (float) Math.atan((ty-y)/(tx-x));
        }else{
            ang = (float) (3.141592 + Math.atan((ty-y)/(tx-x)));
        }
        if(dist <= speed){
            knightPosition.set(knightTarget);
        } else{
            
            knightPosition.set((float)(x + speed*Math.cos(ang)), (float)(y + speed*Math.sin(ang)));
        }
        invalidate();
    }
*/
}
