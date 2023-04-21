package com.example.knightslabyrinth;

public class amIPositiveFunction {
    public static <T extends Number> boolean amIPositive(T num) {
        return num.doubleValue() > 0;
    }
}