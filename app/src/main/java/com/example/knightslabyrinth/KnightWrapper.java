package com.example.knightslabyrinth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

public class KnightWrapper extends View{
    private Paint paint;
    private PointF knightPosition;
    private long knight;

    public KnightWrapper(Context context) {
        super(context);
        init();
    }

    public KnightWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KnightWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(0xFFFF0000); // Red color
        knightPosition = new PointF(0, 0);
        //Code for creating c++ knight object
        knight = mkNew();
    }


    public void killKnight(){
        deleteC(knight);
        knight = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(knightPosition.x, knightPosition.y, 50, paint);
    }
    public void moveKnight(){
        updateC(knight);
        knightPosition.x = getXC(knight);
        knightPosition.y = getYC(knight);
        invalidate();
    }

    //Code for knight begins here
    public void setTarget(float x, float y){
        setTargetC(knight,x,y);
    }

    //Function headers for ndk
    public native long mkNew();
    public native void deleteC(long knight);
    public native void setTargetC(long knight, float x,float y);
    public native void updateC(long knight);
    public native float getXC(long knight);
    public native float getYC(long knight);
}
