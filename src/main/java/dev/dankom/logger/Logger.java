package dev.dankom.logger;

import dev.dankom.core.Main;
import org.bukkit.Bukkit;

public class Logger {

    public static void log(LogLevel logLevel, String msg) {
        log(logLevel.getColor() + Main.PREFIX + " " + logLevel.getName() + " " + msg);
    }

    public static void log(String msg) {
        LogLevel logLevel = LogLevel.INFO;
        log(logLevel.getColor() + Main.PREFIX + " " + logLevel.getName() + " " + msg);
    }

    private static void log(Object msg) {
        Bukkit.getConsoleSender().sendMessage("" + msg);
    }
}
