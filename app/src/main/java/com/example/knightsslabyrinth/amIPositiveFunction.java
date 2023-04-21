package com.example.knightsslabyrinth;

public class amIPositiveFunction {
    public static <T extends Number> boolean amIPositive(T num) {
        return num.doubleValue() > 0;
    }

}
