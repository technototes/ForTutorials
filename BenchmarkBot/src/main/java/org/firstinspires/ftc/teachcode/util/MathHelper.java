package org.firstinspires.ftc.teachcode.util;

public class MathHelper {
    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(value, max));
    }

    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(value, max));
    }
}
