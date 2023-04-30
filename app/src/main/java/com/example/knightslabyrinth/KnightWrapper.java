package com.example.knightslabyrinth;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

public class KnightWrapper extends View{
    private static int pic;
    private Paint paint;
    private PointF knightPosition;
    private long knight;
    private int radius = 110;
    private int moveOption;
    private int offsetx = 0;
    private int offsety = 0;

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
    }

    public void killKnight(){
        deleteC(knight);
        knight = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //Context context = current;
        super.onDraw(canvas);
        //Farmer 1 and farmer 2
        if(moveOption == 1){
            offsetx = 200;
            offsety = 170;
            //Ability on cool-down or ability ready
            if(getCooldownC(knight) == 0){
                pic = R.drawable.dasher;
                paint.setColor(0xFFFF0000);
            }else{
                pic = R.drawable.dasher_blue;
                paint.setColor(0xffffff00); // Yellow color
            }
        }else if(moveOption == 2){
            offsetx = 150;
            offsety = 170;
            //Ability on cool-down or ability ready
            if(getCooldownC(knight) == 0){
                pic = R.drawable.real_bomber;
                paint.setColor(0xFFFF0000);
            }else{
                pic = R.drawable.real_bomber_blue;
                paint.setColor(0xffffff00); // Yellow color
            }
        }else {
            paint.setColor(0xff0000ff); // Blue Color
        }
        if(moveOption == 2)
        {
            if(getAbilityActive() != 0)
            {
                Bitmap explode = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.explo);
                canvas.drawBitmap(explode, knightPosition.x-850, knightPosition.y-425, paint);
            }
        }
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(),pic);
        canvas.drawBitmap(bitmap, knightPosition.x-offsetx, knightPosition.y-offsety, paint);
        //canvas.drawCircle(knightPosition.x, knightPosition.y, radius, paint);
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
