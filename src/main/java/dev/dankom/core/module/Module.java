package dev.dankom.core.module;

import dev.dankom.Start;
import dev.dankom.logger.LogLevel;
import dev.dankom.logger.Logger;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

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

    public void registerListener(Listener listener) {
        Start.getInstance().getServer().getPluginManager().registerEvents(listener, Start.getInstance());
    }

    public void setCommandExecutor(String command, CommandExecutor commandExecutor) {
        Start.getInstance().getServer().getPluginCommand(command).setExecutor(commandExecutor);
    }
}
