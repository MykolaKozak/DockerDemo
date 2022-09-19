package utils;

public class TimeUtils {
    public static boolean isTimeExpired(long startTime, long endTimeInSec) {
        return !((System.currentTimeMillis() - startTime) <= endTimeInSec * 1000);
    }

    public static void waitForSec(int seconds) {
        seconds = seconds * 1000;
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
