package com.example.knightslabyrinth;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.graphics.PointF;

public class MonsterView extends View implements MonsterAPI{
    private static int pic;
    private Paint paint;
    public List<Long> monsterPtrs = new ArrayList<>();// List of pointers to the monsters

    private int windowWidth, windowHeight;
    private Random random = new Random(); //Random object for generating random values
    private PointF knightPosition;// Position of the knight on the screen
    private int knightType = MainActivity.settings.getKnight();// Knight type from settings
    private int difficulty = MainActivity.settings.getDifficulty();// Difficulty level from settings

    //Set the window width and height here
    public void setWindowWidth (int width){
        windowWidth = width;
    }
    public void setWindowHeight (int height){
        windowHeight = height;
    }
    public void setKnightPosition(PointF position){
        knightPosition = position;
    }

    // Constructors
    public MonsterView(Context context, AttributeSet attrs, GameScreenFragment gameScreenFragment) {
        super(context, attrs);
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


    // Override the onDraw method to draw the monsters on the canvas
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (monsterPtrs != null) {
            drawMonsters(canvas, getContext());
        }
    }

    // Set the GameScreenFragment reference
    public void setGameScreenFragment(GameScreenFragment gameScreenFragment) {}

    public void moveMonsters(PointF knightPosition, int knightRadius, float knightSpeed, int knightAbility){
        // Update monsters
        float objectiveY = windowHeight;
        for (long monsterPtr : monsterPtrs) {
            float objectiveX = random.nextFloat()*windowWidth;
            updateMonster(monsterPtr, objectiveX, objectiveY, knightPosition.x, knightPosition.y, knightRadius, knightSpeed, knightAbility);
        }
        // Use getMonsterX(monsterPtr) and getMonsterY(monsterPtr) to get the updated monster positions
        // and update the monster positions in the UI
        invalidate();
    }
    public void spawnMonsters(long ticknum) {
        switch(difficulty){
            case 0:
                ticknum = ticknum/2;
                break;
            case 1:
                ticknum = ticknum + 5000;
                break;
            default:
                ticknum = ticknum*2 + 10000;
                break;
        }
        if (random.nextInt(100) < (float)ticknum/100000+1) { // 1% chance to spawn a monster each tick
            float x = random.nextFloat() * windowWidth;
            float y = 0;
            float speed = random.nextFloat() * (4+(float)ticknum/80000) + 3*(float)ticknum/5000; // Random speed between 5 and 15
            int movementType = random.nextInt(3);
            long monsterPtr = createMonster(x, y, speed, windowWidth, windowHeight, movementType, knightType, difficulty);
            monsterPtrs.add(monsterPtr);
        }
    }

    // Draw monsters on the canvas using the list of monster pointers
    private void drawMonsters(Canvas canvas, Context current) {
        int offsetx = 0;
        int offsety = 0;
        Context context = current;
        Paint monsterPaint = new Paint();
        monsterPaint.setColor(Color.RED);

        for (long monsterPtr : monsterPtrs) {
            float x = getMonsterX(monsterPtr);
            float y = getMonsterY(monsterPtr);
            // Get the monster's movement type
            int movementType = getMovementType(monsterPtr);
            // Get the monster's frame
            int monsterFrame = getMonsterFrameC(monsterPtr);
            switch(movementType){
                case(0):
                    offsetx = -100;
                    offsety = -125;
                    switch(monsterFrame){
                        case(0):
                            pic = R.drawable.potato;
                            monsterPaint.setColor(Color.BLACK);
                            break;
                        case(1):
                            pic = R.drawable.potato_m;
                            monsterPaint.setColor(Color.BLUE);
                            break;
                        case(2):
                            pic = R.drawable.potato_dead;
                            monsterPaint.setColor(Color.RED);
                            break;
                    }
                    break;
                case(1):
                    offsetx = -140;
                    offsety = -100;
                    switch(monsterFrame){
                        case(0):
                            pic = R.drawable.tomato;
                            monsterPaint.setColor(Color.GRAY);
                            break;
                        case(1):
                            pic = R.drawable.tomato_m;
                            monsterPaint.setColor(Color.DKGRAY);
                            break;
                        case(2):
                            pic = R.drawable.tomato_dead;
                            monsterPaint.setColor(Color.LTGRAY);
                            break;
                    }
                    break;
                case(2):
                    offsetx = -100;
                    offsety = -150;
                    switch(monsterFrame){
                        case(0):
                            pic = R.drawable.carrot;
                            monsterPaint.setColor(Color.MAGENTA);
                            break;
                        case(1):
                            pic = R.drawable.carrot_m;
                            monsterPaint.setColor(Color.GREEN);
                            break;
                        case(2):
                            pic = R.drawable.carrot_dead;
                            monsterPaint.setColor(Color.YELLOW);
                            break;
                    }
                    break;
            }
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),pic);

            float radius = 100; // Adjust the size of the monster as needed
            canvas.drawBitmap(bitmap, x+offsetx, y+offsety, monsterPaint);
            //canvas.drawCircle((float)x,(float)y,(float)radius,monsterPaint);
        }
    }

    // Delete monsters from array when they reach castle
    public int deleteMonsters() {
        List<Long> deadMonsters = new ArrayList<>();
        List<Long> killedMonsters = new ArrayList<>();
        for (long monsterPtr : monsterPtrs) {
            if (inObj(windowWidth/2, windowHeight - 200, monsterPtr) > 0) {
                deadMonsters.add(monsterPtr);
                deleteC(monsterPtr);
            } else if (kick(monsterPtr) > 0) {
                killedMonsters.add(monsterPtr);
                deleteC(monsterPtr);
            }
        }

        for (long monsterPtr : deadMonsters) {
            monsterPtrs.remove(monsterPtr);
        }
        for (long monsterPtr : killedMonsters) {
            monsterPtrs.remove(monsterPtr);
        }
        return deadMonsters.size();
    }

    public int getNormKicked() { // normal monster
        int k = 0;
        for (long monsterPtr : monsterPtrs) {
            if (kick(monsterPtr) > 0 && getMovementType(monsterPtr) == 0) { k++; }
        }
        return k;
    }
    public int getHopKicked() { // hop monster
        int k = 0;
        for (long monsterPtr : monsterPtrs) {
            if (kick(monsterPtr) > 0 && getMovementType(monsterPtr) == 1) { k++; }
        }
        return k;
    }
    public int getDiagKicked() { // diagonal monster
        int k = 0;
        for (long monsterPtr : monsterPtrs) {
            if (kick(monsterPtr) > 0 && getMovementType(monsterPtr) == 2) { k++; }
        }
        return k;
    }

    //NDK Functions documented in BACKENDAPI
    public native long createMonster(float x, float y, float speed, int windowWidth, int windowHeight, int movementType, int knightType, int difficulty);
    public native void updateMonster(long monsterPtr, float objectiveX, float objectiveY, float knightX, float knightY, int knightRad, float knightSpeed, int knightAbilityActive);
    public native float getMonsterX(long monsterPtr);
    public native float getMonsterY(long monsterPtr);
    public native int getMovementType(long monsterPtr);
    public native int inObj(float obj_x, float obj_y, long monsterPtr);
    public native int kick(long monsterPtr);
    public native void deleteC(long monsterPtr);
    public native int getMonsterFrameC(long monsterPtr);
}
