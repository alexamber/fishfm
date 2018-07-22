package fm.fish.util;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {
    public static final ThreadLocalRandom R = ThreadLocalRandom.current();

    public static <T> T dice(final List<T> l) {
        return l.get(R.nextInt(l.size()));
    }
}
