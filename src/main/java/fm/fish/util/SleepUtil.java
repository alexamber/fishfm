package fm.fish.util;

import java.time.Duration;

public class SleepUtil {

    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
