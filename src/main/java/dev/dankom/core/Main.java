package dev.dankom.core;

import dev.dankom.logger.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static String PREFIX = "&d[&bCore&d]";

    @Override
    public void onEnable() {
        Logger.log("Starting Core");
    }

    @Override
    public void onDisable() {
        Logger.log("Stopping Core");
    }
}
