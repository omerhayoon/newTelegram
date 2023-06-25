package Utility;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String getCurrentTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(formatter);
    }
    public static void sleep(int number){
        try {
            Thread.sleep(number);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
