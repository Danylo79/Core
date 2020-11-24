package dev.dankom.util;

public class Percentage {
    public static int getPercent(int num1, float perc) {
        return Math.round((perc * num1) / 100f);
    }
}
