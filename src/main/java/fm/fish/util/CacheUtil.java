package fm.fish.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheUtil {

    public static <K, V> LinkedHashMap<K, V> createCache(final int size) {
        return new LinkedHashMap<>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return this.size() > size;
            }
        };
    }
}
