package software.ulpgc.kata4.application;

import software.ulpgc.kata4.model.Movie;

public class MovieDeserializer {
    public static Movie from(String str) {
        return from(str.split("\t"));
    }

    private static Movie from(String[] split) {
        return new Movie(split[2], toInt(split[5]),toInt(split[7]));
    }

    private static int toInt(String str) {
        if (isVoid(str)) return -1;
        return Integer.parseInt(str);
    }

    private static boolean isVoid(String str) {
        return str.equals("\\N");
    }

}
