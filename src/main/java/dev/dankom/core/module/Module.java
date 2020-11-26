package dev.dankom.core.module;

import com.comphenix.protocol.error.ReportType;
import dev.dankom.Start;
import dev.dankom.core.Core;
import dev.dankom.logger.LogLevel;
import dev.dankom.logger.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

public class Module {
    private final String name;
    private final String PREFIX;
    private final String[] dependencies;

    public Module(String name, String prefix) {
        this.name = name;
        this.PREFIX = prefix;
        this.dependencies = null;
    }

    public Module(String name, String prefix, String... dependencies) {
        this.name = name;
        this.PREFIX = prefix;
        this.dependencies = dependencies;
    }

    public void onEnable() {}

    public void onDisable() {}

    public String getName() {
        return name;
    }

    public String[] getDependencies() {
        return dependencies;
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

    public String prefix() {
        return PREFIX;
    }

    public boolean canLoad() {
        boolean areDependenciesFound = areDependenciesFound();
        boolean isValid = !getName().isEmpty() && !prefix().isEmpty();

        return areDependenciesFound && isValid;
    }

    public boolean areDependenciesFound() {
        if (!areDependenciesNeeded()) {
            return true;
        }
        boolean dependenciesFound = false;
        for (String d : dependencies) {
            if (ModuleHelper.getModule(d) != null) {
                dependenciesFound = true;
            } else {
                dependenciesFound = false;
            }
            continue;
        }
        return dependenciesFound;
    }

    public boolean areDependenciesNeeded() {
        if (dependencies == null) {
            return false;
        } else {
            return true;
        }
    }
}
