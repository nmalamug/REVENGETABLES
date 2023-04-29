package com.example.knightslabyrinth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.graphics.PointF;

public class MonsterView extends View {
    private Paint paint;
    //private List<Long> monsterPtrs;
    private List<Long> monsterPtrs = new ArrayList<>();
    private List<Long> killedMonsters = new ArrayList<>();

    private GameScreenFragment gameScreenFragment;
    private int windowWidth, windowHeight;
    private Random random = new Random();
    private PointF knightPosition;

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

    //NDK Functions
    public native long createMonster(float x, float y, float speed, int windowWidth, int windowHeight, int movementType);
    public native void updateMonster(long monsterPtr, float objectiveX, float objectiveY, float knightX, float knightY, int knightRad, float knightSpeed);
    public native float getMonsterX(long monsterPtr);
    public native float getMonsterY(long monsterPtr);
    public native int getMovementType(long monsterPtr);
    public native int inObj(float obj_x, float obj_y, long monsterPtr);
    public native int kick(long monsterPtr);
    public native void deleteC(long monsterPtr);
    public native int getMonsterFrameC(long monsterPtr);
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

    public void moveMonsters(PointF knightPosition, int knightRadius, float knightSpeed){
        // Update monsters
        float objectiveX = windowWidth / 2;
        float objectiveY = windowHeight-100;
        for (long monsterPtr : monsterPtrs) {
            updateMonster(monsterPtr, objectiveX, objectiveY, knightPosition.x, knightPosition.y, knightRadius, knightSpeed);
        }
        // Use getMonsterX(monsterPtr) and getMonsterY(monsterPtr) to get the updated monster positions
        // and update the monster positions in the UI
        invalidate();
    }
    public void spawnMonsters() {
        if (random.nextInt(100) < 1) { // 1% chance to spawn a monster each tick
            float x = random.nextFloat() * windowWidth;
            float y = 0;
            float speed = 5; //random.nextFloat() * 10 + 5; // Random speed between 5 and 15

            int movementType = random.nextInt(3);
            long monsterPtr = createMonster(x, y, speed, windowWidth, windowHeight, movementType);
            monsterPtrs.add(monsterPtr);
        }
    }

    // Draw monsters on the canvas using the list of monster pointers
    private void drawMonsters(Canvas canvas) {
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
                    switch(monsterFrame){
                        case(0):
                            monsterPaint.setColor(Color.BLACK);
                            break;
                        case(1):
                            monsterPaint.setColor(Color.BLUE);
                            break;
                        case(2):
                            monsterPaint.setColor(Color.RED);
                            break;
                    }
                    break;
                case(1):
                    switch(monsterFrame){
                        case(0):
                            monsterPaint.setColor(Color.GRAY);
                            break;
                        case(1):
                            monsterPaint.setColor(Color.DKGRAY);
                            break;
                        case(2):
                            monsterPaint.setColor(Color.LTGRAY);
                            break;
                    }
                    break;
                case(2):
                    switch(monsterFrame){
                        case(0):
                            monsterPaint.setColor(Color.MAGENTA);
                            break;
                        case(1):
                            monsterPaint.setColor(Color.GREEN);
                            break;
                        case(2):
                            monsterPaint.setColor(Color.YELLOW);
                            break;
                    }
                    break;
            }
            float radius = 20; // Adjust the size of the monster as needed
            canvas.drawCircle(x, y, radius, monsterPaint);
        }
    }

    // Delete monsters from array when they reach castle
    public int deleteMonsters() {
        List<Long> deadMonsters = new ArrayList<>();
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
}
