package com.example.knightslabyrinth;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UnitTests {

    @Test
    public void testUpdateScore() {
        assertTrue(LifeView.updateScore(12000000L));
        assertTrue(LifeView.updateScore(1000080L));
        assertTrue(LifeView.updateScore(150000040L));

        assertFalse(LifeView.updateScore(13000050L));
        assertFalse(LifeView.updateScore(33000067L));
        assertFalse(LifeView.updateScore(260400543L));
    }

}
