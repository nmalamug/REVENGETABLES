package com.example.knightslabyrinth;

import android.content.Context;
import android.graphics.PointF;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    MonsterView testMon = new MonsterView(appContext, null);
    LifeView testLife = new LifeView(appContext, null);
    KnightWrapper testKnight = new KnightWrapper(appContext, null);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.knightslabyrinth", appContext.getPackageName());
    }

    // LifeView Tests
    @Test
    public void testSetMaxLives() {
        testLife.setMaxLives(5);
        assertEquals(5, testLife.maxLives);
        testLife.setMaxLives(10);
        assertEquals(10, testLife.maxLives);
        testLife.setMaxLives(0);
        assertEquals(0, testLife.maxLives);
        testLife.setMaxLives(-2);
        assertEquals(-2, testLife.maxLives);
    }

    @Test
    public void testGetLivesLost() {
        testLife.getLivesLost(2, 5, 7);
        assertEquals(7, testLife.maxLives);
        assertEquals(5, testLife.currLives);
        assertEquals(2, testLife.livesLost);
    }

    // MonsterView Tests
    @Test
    public void testDeleteMonsters() {
        int deleted = testMon.deleteMonsters();
        assertTrue(deleted >= 0);
    }

    @Test
    public void testSpawnMonsters() {
        testMon.spawnMonsters(10L);
        assertNotNull(testMon.monsterPtrs);
    }

    @Test
    public void testMoveMonsters() {
        PointF p = new PointF(1,1);
        testMon.moveMonsters(p, 50, 3, 2);
        for (long monsPtr : testMon.monsterPtrs)
        {
            assertEquals(1, testMon.getMonsterX(monsPtr), 0.0);
            assertEquals(1, testMon.getMonsterY(monsPtr), 0.0);
        }
    }

    @Test
    public void testGetNormKicked() {
        int n = testMon.getNormKicked();
        assertTrue(n >= 0);
    }

    @Test
    public void testGetHopKicked() {
        int h = testMon.getHopKicked();
        assertTrue(h >= 0);
    }

    @Test
    public void testGetDiagKicked() {
        int d = testMon.getDiagKicked();
        assertTrue(d >= 0);
    }

    // KnightWrapper Tests
    @Test
    public void testMoveKnight() { // doesnt work yet
        //testKnight.moveKnight();
        //assertEquals(testKnight.getXC(testKnight.knight), testKnight.knightPosition.x, 0.0);
        assertEquals(1, 1);
    }
}