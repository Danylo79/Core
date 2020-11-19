package dev.dankom.logger;

public enum LogLevel {

    INFO("INFO", "&e"),
    ERROR("ERROR", "&c"),
    FAIL("FAIL", "&c"),
    FATAL("FATAL", "&5"),
    IMPORTANT("IMPORTANT", "&2");

    private final String name;
    private final String color;
    private final boolean throttle;

    LogLevel(String name, String color, boolean throttle) {
        this.name = name;
        this.color = color;
        this.throttle = throttle;
    }

    LogLevel(String name, String color) {
        this.name = name;
        this.color = color;
        this.throttle = false;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public boolean isThrottle() {
        return throttle;
    }
}
