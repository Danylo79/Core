package dev.dankom.logger;

import dev.dankom.core.module.ModuleHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Logger {

    public static void log(LogLevel logLevel, String msg) {
        log(logLevel.getColor() + ModuleHelper.getModule("Core").prefix() + " [" + logLevel.getName() + "] " + msg);
    }

    private static void log(Object msg) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "" + msg));
    }
}
