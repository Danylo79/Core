package dev.dankom.util;

public class Validation {
    public static void notNull(String msg, Object object) {
        throwException(msg, object != null);
    }

    public static void throwCompactException(String msg) {
        throwException(msg, false);
    }

    private static void throwException(String msg, boolean validation) {
        if (!validation) {
            try {
                throw new Exception(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
    }
}
