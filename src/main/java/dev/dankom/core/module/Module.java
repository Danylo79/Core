package dev.dankom.core.module;

import dev.dankom.logger.LogLevel;
import dev.dankom.logger.Logger;

public class Module {
    private final String name;
    public static String PREFIX;

    public Module(String name, String prefix) {
        this.name = name;
        this.PREFIX = prefix;
    }

    public void onEnable() {}

    public void onDisable() {}

    public String getName() {
        return name;
    }

    public void log(LogLevel logLevel, String msg) {
        Logger.log(logLevel, PREFIX + msg);
    }
}
