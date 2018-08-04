package fm.fish.util;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtils {
    public static String spawn(String tmp, int times) {
        return new String(new char[times]).replace("\0", tmp);
    }

    public static String joinComma(Object... objs) {
        return Stream.of(objs).map(Object::toString).collect(Collectors.joining(","));
    }
}
