package fm.fish.util;

public class StringUtils {

    public static String spawn(String tmp, int times) {
        return new String(new char[times]).replace("\0", tmp);
    }
}
