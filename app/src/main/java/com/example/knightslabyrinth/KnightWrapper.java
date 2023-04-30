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
    private int radius = 100;
    private int moveOption;

    public int getRadius(){
        return radius;
    }
    public PointF getKnightPosition() {
        return knightPosition;
    }

    public float getSpeed(){
        return getSpeedC(knight);
    }
    public int getAbilityActive(){
        return getAbilityActiveC(knight);
    }
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
        knightPosition = new PointF(0, 0);
        //Code for creating c++ knight object
        knight = mkNew();
        moveOption = MainActivity.settings.getKnight();
        if(moveOption == 1){
            paint.setColor(0xFFFF0000); // Red color
        }else if(moveOption == 2){
            paint.setColor(0xffffff00); // Yellow color
        }else{
            paint.setColor(0xff0000ff ); // Blue Color
        }
    }

    public void killKnight(){
        deleteC(knight);
        knight = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(getCooldownC(knight) == 0){
            paint.setColor(0xFFFF0000);
        }else{
            paint.setColor(0xffffff00); // Yellow color
        }
        canvas.drawCircle(knightPosition.x, knightPosition.y, radius, paint);
    }
    public void moveKnight(){
        updateC(knight, moveOption);
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
    public native void updateC(long knight, int move);
    public native float getXC(long knight);
    public native float getYC(long knight);
    public native float getSpeedC(long knight);
    public native int getAbilityActiveC(long knight);
    public native int getCooldownC(long knight);
}
