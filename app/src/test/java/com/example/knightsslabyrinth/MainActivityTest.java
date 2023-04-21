package com.example.knightsslabyrinth;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static com.example.knightsslabyrinth.MainActivity.amIPositive;
import org.junit.Test;


import static org.junit.Assert.*;

public class MainActivityTest {
    @Test
    public void testAmIPositive() {
        assertTrue(amIPositive(1));
        assertTrue(amIPositive(1L));
        assertTrue(amIPositive(1.0));
        assertTrue(amIPositive(1.0f));

        assertFalse(amIPositive(-1));
        assertFalse(amIPositive(-1L));
        assertFalse(amIPositive(-1.0));
        assertFalse(amIPositive(-1.0f));

        assertFalse(amIPositive(0));
        assertFalse(amIPositive(0L));
        assertFalse(amIPositive(0.0));
        assertFalse(amIPositive(0.0f));
    }
}

