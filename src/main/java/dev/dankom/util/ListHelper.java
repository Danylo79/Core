package dev.dankom.util;

import java.util.ArrayList;
import java.util.List;

public class ListHelper {
    public static <T> List<T> toList(T... objects) {
        List<T> out = new ArrayList<>();
        for (T t : objects) {
            out.add(t);
        }
        return out;
    }
}
