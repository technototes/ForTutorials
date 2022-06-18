package org.firstinspires.ftc.teachcode.util;

public class SleepHelper {
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }
    }
}
